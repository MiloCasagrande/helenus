/*
 * Copyright 2012 Milo Casagrande milo@milo.name
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
*/
package com.helenus.properties;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * @author Milo Casagrande
 * @since 0.1
 */
public final class PropertiesManager {

    // The singleton instance
    private static final PropertiesManager MANAGER = new PropertiesManager();

    /**
     * Default capacity of the cache, increase it in case we have more
     * properties to handle, just look at {@link PropType}.
     */
    private static final int CAPACITY = 4;

    // The cache holding the properties manager
    private final Map<String, IProperties> propManagers;

    // Private constructor for singleton
    private PropertiesManager() {
        propManagers = new HashMap<String, IProperties>(CAPACITY);
    }

    /**
     * Get the instance of the {@link PropertiesManager}.
     * 
     * @return the singleton instance
     */
    public static PropertiesManager getInstance() {
        return MANAGER;
    }

    /**
     * Retrieve the properties handler to get the resource strings from.
     * 
     * @param value
     *            the {@link IPropType} of the desired resource
     * @return the properties handler
     */
    public IProperties getManager(final IPropType value) {
        IProperties manager = null;

        if (propManagers.containsKey(value.getName())) {
            manager = propManagers.get(value.getName());
        } else {
            manager = new PropertiesHandler(value.getFileName());
            propManagers.put(value.getName(), manager);
        }

        return manager;
    }

    /**
     * Inner class to define and provide a handler to the properties file in the
     * file system.
     * <p>
     * The properties file should be accessible through the CLASSPATH.
     * 
     * @author Milo Casagrande
     */
    private static class PropertiesHandler implements IProperties {
        // The package where the properties file are stored.
        private static final String PROPERTIES_PATH = "com/helenus/resources/prop/";
        // Where to store the properties read.
        private final Properties properties;

        /**
         * Create a new properties handler.
         * 
         * @param fileName
         *            the name of the properties file, should be in the
         *            CLASSPATH
         */
        public PropertiesHandler(final String fileName) {
            properties = new Properties();
            InputStream in = null;

            try {
                in = this.getClass().getClassLoader().getResourceAsStream(PROPERTIES_PATH + fileName);
                properties.load(in);
            } catch (final FileNotFoundException ex) {
                // TODO add logger
                ex.printStackTrace();
                // We should never, ever, ever get here!
                // MLogger.getLogger().debug("Impossible to find '{}' properties file in the PATH",
                // fileName, ex);
            } catch (final IOException ex) {
                // TODO add logger
                ex.printStackTrace();
                // Nor here!
                // MLogger.getLogger().debug("Error loading the properties from the '{}' file",
                // fileName, ex);
            } finally {
                try {
                    if (in != null) {
                        in.close();
                    }
                } catch (final IOException ex) {
                    // TODO logger
                    ex.printStackTrace();
                    // MLogger.getLogger().debug("Error closing stream", ex);
                }
            }
        }

        /*
         * (non-Javadoc)
         * @see com.mymed.properties.IProperties#get(java.lang.String)
         */
        @Override
        public String get(final String key) {
            return properties.getProperty(key);
        }
    }
}
