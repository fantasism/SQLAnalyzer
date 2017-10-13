/*
 * Copyright (C) 2017 FANTASISM All Rights Reserved.
 */

package org.fantasism.eclipse.plugin.resource.core;

import java.io.File;
import java.nio.file.PathMatcher;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.regex.Pattern;

import org.eclipse.core.resources.IContainer;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;

/**
 * TODO クラスの概要
 * <p>
 * TODO クラスの説明
 * </p>
 * @author Takahide Ohsuka, FANTASISM.
 */
public class ResourceFinder {

    private Pattern filePattern;

    private Pattern pathPattern;

    public Collection<IResource> find(IProject project, String fileKeyword) {

        return find(project, fileKeyword);

    }

    public Collection<IResource> find(IProject project, String fileKeyword, String pathKeyword) {

        List<IResource> resources = new ArrayList<>();

        try {
            filePattern = Pattern.compile(((fileKeyword == null || fileKeyword.trim().isEmpty()) ? ".*" : fileKeyword));
            pathPattern = Pattern.compile(((pathKeyword == null || pathKeyword.trim().isEmpty()) ? ".*" : pathKeyword));

            find(project, resources);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return resources;

    }

    public Collection<IResource> find(String fileKeyword) {

        return find(fileKeyword, null);

    }

    public Collection<IResource> find(String fileKeyword, String pathKeyword) {

        List<IResource> resources = new ArrayList<>();

        try {
            filePattern = Pattern.compile(((fileKeyword == null || fileKeyword.trim().isEmpty()) ? ".*" : fileKeyword));
            pathPattern = Pattern.compile(((pathKeyword == null || pathKeyword.trim().isEmpty()) ? ".*" : pathKeyword));

            IProject[] projects = ResourcesPlugin.getWorkspace().getRoot().getProjects();

            for (IProject project : projects) {
                find(project, resources);
            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return resources;

    }

    private void find(IContainer container, Collection<IResource> resources) throws CoreException {

        for (IResource resource : container.members()) {

            if (resource.isAccessible()) {
            } else {
                continue;
            }

            if (resource instanceof IContainer) {
                find((IContainer) resource, resources);
            } else {
                File file = resource.getLocation().toFile();

                if (filePattern.matcher(file.getName()).matches()) {
                } else {
                    continue;
                }

                if (pathPattern.matcher(file.getParent()).matches()) {
                } else {
                    continue;
                }
            }

        }

    }

}
