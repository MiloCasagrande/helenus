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
package com.helenus.configuration;

import java.io.File;

/**
 * Basic interface for a configurator necessary to Helenus.
 * 
 * @author Milo Casagrande
 * @since 0.1
 */
public interface IConfigurator {
    /**
     * Read the YAML configuration file.
     * 
     * @return the object instance
     */
    IConfigurator read();

    /**
     * Read the YAML configuration file.
     * 
     * @param configFile
     *            the YAML configuration file to read
     * @return the object instance
     */
    IConfigurator read(File configFile);

    /**
     * Set the YAML configuration file to read.
     * 
     * @param configFile
     *            the YAML configuration file to read
     * @return the object instance
     */
    IConfigurator withConfigFile(File configFile);

    /**
     * Fill up the {@link Configuration} object with the necessary information.
     * 
     * @return the object instance
     */
    IConfigurator config();

    /**
     * Get the {@link Configuration} object to use.
     * 
     * @return the associated configuration object
     */
    Configuration get();
}
