/*******************************************************************************
 * Copyright (c) 2018 THALES GLOBAL SERVICES.
 * 
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0
 * 
 * SPDX-License-Identifier: EPL-2.0
 * 
 * Contributors:
 *    Thales - initial API and implementation
 *******************************************************************************/
package org.polarsys.capella.filtering.tools.extract;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.Reader;
import java.io.Writer;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

import org.eclipse.core.resources.IContainer;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IProjectDescription;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.core.runtime.preferences.IEclipsePreferences;
import org.eclipse.core.runtime.preferences.InstanceScope;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.polarsys.capella.core.model.handler.command.CapellaResourceHelper;
import org.polarsys.capella.filtering.tools.FilteringToolsPlugin;
import org.polarsys.capella.filtering.tools.preferences.FilteringPreferencesPage;
import org.polarsys.kitalpha.ad.metadata.helpers.ViewpointMetadata;

/**
 * 
 * 
 */
public class FilteringExtractor {

  private static final String PATTERNS_FILE_EXTENSION = "patterns";
  private static final String MELODYCONNECTOR_FILE_EXTENSION = "melodyconnector";
  private static final NullProgressMonitor __MONITOR = new NullProgressMonitor();
  private static final IEclipsePreferences PREFS = InstanceScope.INSTANCE
      .getNode(FilteringToolsPlugin.getDefault().getPluginId());

  private static String _domainId;
  private static String _newId;

  private IProject _clonedProject;

  /**
   * Constructor
   * 
   * @param clonedProject
   * @param domainId
   */
  public FilteringExtractor(IProject clonedProject, String domainId) {
    _clonedProject = clonedProject;
    _domainId = domainId;
    _newId = EcoreUtil.generateUUID();
  }

  public void cloneProjectNature(IProject currentProject) throws CoreException {
    // the cloned project must have the natures of its parent
    String[] natures = currentProject.getDescription().getNatureIds();
    IProjectDescription description = _clonedProject.getDescription();
    description.setNatureIds(natures);
    _clonedProject.setDescription(description, null);
  }

  /**
   * Copy all files from the given project to the cloned project
   * 
   * @param currentProject
   * @return a Map of old references to new references
   * @throws CoreException
   */
  public Map<String, String> cloneModels(IProject currentProject) throws CoreException {
    Map<String, String> oldReferenceToNewReference = new HashMap<>();

    for (IResource resource : currentProject.members()) {
      String ext = resource.getFileExtension();

      // It is a special melody resource
      if (CapellaResourceHelper.CAPELLA_MODEL_FILE_EXTENSION.equals(ext)
          || CapellaResourceHelper.AIRD_FILE_EXTENSION.equals(ext) || PATTERNS_FILE_EXTENSION.equals(ext)
          || ViewpointMetadata.STORAGE_EXTENSION.equals(ext)) {
        // Calculate newPath changing also the name.
        // For example from /genericProject/genericProject.aird to
        // /specific/specific.aird
        IPath newPath = _clonedProject.getFullPath();
        newPath = newPath.addTrailingSeparator();
        newPath = newPath.append(_clonedProject.getName());
        newPath = newPath.addFileExtension(ext);
        resource.copy(newPath, true, __MONITOR);

        if (CapellaResourceHelper.CAPELLA_MODEL_FILE_EXTENSION.equals(ext)
            || ViewpointMetadata.STORAGE_EXTENSION.equals(ext)) {
          // .capella or .afm file, keep old -> new reference
          oldReferenceToNewReference.put(resource.getName(), newPath.lastSegment());
        }
        // Else if it is any other element
      } else if ((resource instanceof IFolder) || (resource instanceof IFile)) {

        // This condition prevents the ResourceException to copy an
        // already existing file for example ".project" as it was
        // automatically created previously with
        // the clonedProject creation
        // TODO This condition prevents also not to copy the previous
        // melodyconnector file if it exists from the previous model.
        // A better solution could be found for not to create this
        // implicit dependency
        if (!_clonedProject.exists(resource.getProjectRelativePath()) && !MELODYCONNECTOR_FILE_EXTENSION.equals(ext)) {
          // Calculate newPath with the same file name
          IPath newPath = _clonedProject.getFullPath();
          newPath = newPath.addTrailingSeparator();
          newPath = newPath.append(resource.getProjectRelativePath());
          // This copy files and folders with all their content
          resource.copy(newPath, true, __MONITOR);
        }
      }
    }
    return oldReferenceToNewReference;
  }

  /**
   * @param oldReferenceToNewReference
   * @throws CoreException
   * @throws IOException
   */
  public void updateReferences(Map<String, String> oldReferenceToNewReference) throws CoreException, IOException {
    updateReferences(_clonedProject, oldReferenceToNewReference);
  }

  /**
   * @param container
   * @param referenceToReplace
   * @throws CoreException
   * @throws IOException
   */
  protected void updateReferences(IContainer container, Map<String, String> oldReferenceToNewReference)
      throws CoreException, IOException {
    for (IResource resource : container.members()) {
      if (resource instanceof IFile) {
        IFile file = (IFile) resource;
        // Only update references if the file is a melody special file
        // All aird, aird fragments and capella fragments must
        // update its references to the new capella
        if (CapellaResourceHelper.isAirdResource(resource, false)
            || CapellaResourceHelper.isCapellaResource(resource, false)) {
          replace(file, oldReferenceToNewReference);
        }
        // Recursively visit all the container members
      } else if (resource instanceof IContainer) {
        updateReferences((IContainer) resource, oldReferenceToNewReference);
      }
    }
  }

  /**
   * @param iFile
   * @param toReplaceString
   * @param replacementString
   * @throws IOException
   */
  protected void replace(IFile iFile, Map<String, String> oldReferenceToNewReference) throws IOException {
    // Get the file
    File file = iFile.getRawLocation().toFile();
    // Replace the strings taking into account special characters
    Map<String, String> encodedOldReferenceToNewReference = manageSpecialChars(oldReferenceToNewReference);
    replace(file, encodedOldReferenceToNewReference);
  }

  /**
   * Replace a given string in a file with a given replacement. This method goes line by line instead of getting the
   * whole File content in one String
   * 
   * @param file
   * @param toReplaceString
   * @param replacementString
   * @throws IOException
   */
  public static void replace(File file, Map<String, String> oldReferenceToNewReference) throws IOException {
    // Create a temp file to be replaced with the original at the end
    File outputFile = File.createTempFile(file.getName(), "temp", file.getParentFile());
    Reader reader = new InputStreamReader(new FileInputStream(file), StandardCharsets.UTF_8);
    BufferedReader breader = new BufferedReader(reader);
    Writer writer = new OutputStreamWriter(new FileOutputStream(outputFile), StandardCharsets.UTF_8);
    BufferedWriter bwriter = new BufferedWriter(writer);
    // Start replacing
    String line;
    while ((line = breader.readLine()) != null) {
      for (Map.Entry<String, String> entry : oldReferenceToNewReference.entrySet()) {
        // This replace all occurrences and it does not require to
        // compute a regex as replaceAll method
        line = line.replace(entry.getKey(), entry.getValue());
      }
      if (PREFS.getBoolean(FilteringPreferencesPage.APPLICATION_PROJECT_WITH_DIFFERENT_ID, false)) {
        line = line.replace(_domainId, _newId);
      }
      bwriter.write(line);
      bwriter.newLine();
    }
    // Close readers and writers
    breader.close();
    bwriter.close();
    // Replace
    replaceFiles(file, outputFile);
  }

  /**
   * @param originalFile
   * @param newFile
   * @return if the file was successfully replaced
   */
  public static boolean replaceFiles(File originalFile, File newFile) {
    if (!originalFile.isFile()) {
      return false;
    }
    boolean ok = originalFile.delete();
    if (!ok) {
      return false;
    }
    ok = newFile.renameTo(originalFile);
    if (!ok) {
      return false;
    }
    return originalFile.exists();
  }

  /**
   * Manage special chars for all entries in the given Map (key and value).
   */
  public static Map<String, String> manageSpecialChars(Map<String, String> oldReferenceToNewReference) {
    Map<String, String> result = new HashMap<>();
    for (Map.Entry<String, String> entry : oldReferenceToNewReference.entrySet()) {
      result.put(manageSpecialChars(entry.getKey()), manageSpecialChars(entry.getValue()));
    }
    return result;
  }

  /**
   * Manage special chars. For example whitespace to %20. Also utf8 issues with é í etc.
   * 
   * @param string
   * @return
   */
  public static String manageSpecialChars(String string) {
    String managed = new String(string.getBytes(StandardCharsets.UTF_8));
    managed = URI.encodeSegment(managed, true);
    return managed;
  }

}
