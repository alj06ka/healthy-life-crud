package com.phones.plugins;

import java.io.File;

import java.util.ArrayList;

public class PluginLoader {
    public ArrayList<PluginOptions> loadPlugins(String pluginsFolderDirectory) {
        ArrayList<PluginOptions> plugins = new ArrayList<>();
        File pluginsFolder = new File(pluginsFolderDirectory);
        File [] pluginsJarList = pluginsFolder.listFiles(file -> file.isFile() && file.getName().endsWith(".jar"));
        if (pluginsJarList != null) {
            for (File file : pluginsJarList) {
                try {
                    plugins.add(new PluginOptions(file));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        return plugins;
    }

}

