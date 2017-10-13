/*
 * Copyright (C) 2017 FANTASISM All Rights Reserved.
 */

package org.fantasism.eclipse.plugin.resource.core;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.regex.Pattern;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IWorkspace;
import org.eclipse.core.resources.ResourcesPlugin;

/**
 * TODO クラスの概要
 * <p>
 * TODO クラスの説明
 * </p>
 * @author Takahide Ohsuka, FANTASISM.
 */
public class ProjectFinder {

    private Pattern projectPattern;

    public Collection<IProject> find(String projectKeyword) {

        projectPattern = Pattern.compile(((projectKeyword == null || projectKeyword.trim().isEmpty()) ? ".*" : projectKeyword));

        IWorkspace workspace = ResourcesPlugin.getWorkspace();

        IProject[] projects = workspace.getRoot().getProjects();

        List<IProject> finedProjects = new ArrayList<>(projects.length);

        for (IProject project : projects) {
            if (projectPattern.matcher(project.getName()).matches()) {
                finedProjects.add(project);
            } else {
            }
        }

        return finedProjects;
    }

}
