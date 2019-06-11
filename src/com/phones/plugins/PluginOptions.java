package com.phones.plugins;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.Properties;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

public class PluginOptions {

    private String extension;
    private Plugin plugin;
    private String pluginName;

    PluginOptions(File file) {
        try {
            Properties properties = getPluginProperties(file);
            if (properties == null) {
                throw new Exception();
            }

            String classProperty = "class";
            String pluginClassName = properties.getProperty(classProperty);
            String nameProperty = "name";
            pluginName = properties.getProperty(nameProperty);
            String extensionProperty = "extension";
            extension = properties.getProperty(extensionProperty);

            if (pluginClassName == null || pluginClassName.isEmpty()) {
                throw new Exception();
            }

            if (pluginName == null) {
                pluginName = pluginClassName;
            }

            if (extension == null || extension.isEmpty()) {
                throw new Exception();
            }

            URL jarURL = file.toURI().toURL();
            URLClassLoader classLoader = new URLClassLoader(new URL[]{jarURL});
            Class pluginClass = classLoader.loadClass(pluginClassName);
            plugin = (Plugin) pluginClass.newInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Plugin getPlugin() {
        return plugin;
    }

    public String getPluginName() {
        return pluginName;
    }

    public String getExtension() {
        return extension;
    }

    private Properties getPluginProperties(File file) throws IOException {
        Properties result = null;
        JarFile jarFile = new JarFile(file);
        JarEnum<JarEntry> entries = new JarEnum<>(jarFile.entries());
        for (JarEntry entry : entries) {
            String configFile = "plugin.config";
            if (entry.getName().equals(configFile)) {
                try (InputStream in = jarFile.getInputStream(entry)) {
                    result = new Properties();
                    result.load(in);
                }
            }
        }
        return result;
    }

    @Override
    public String toString() {
        return extension;
    }
}
