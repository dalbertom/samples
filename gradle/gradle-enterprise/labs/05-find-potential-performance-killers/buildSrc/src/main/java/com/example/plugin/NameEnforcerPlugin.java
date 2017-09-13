package com.example.plugin;

import org.gradle.api.Plugin;
import org.gradle.api.Project;

public class NameEnforcerPlugin implements Plugin<Project> {

    @Override
    public void apply(Project project) {
        // TODO remove this sleep before committing
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
        }

        if (project.getName().equals("badname")) {
            throw new IllegalArgumentException("Projects must not be called badname");
        }
    }

}
