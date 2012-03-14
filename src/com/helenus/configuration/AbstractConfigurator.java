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
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Map;

import org.yaml.snakeyaml.Yaml;

import com.helenus.properties.PropertiesManager;

/**
 * Abstract type for the different configurator implementations. A configurator
 * needs only to extend this class.
 * <p>
 * This abstract class provides a set of base methods and attributes that can be
 * accessed by a configurator implementations.
 * <p>
 * A configurator in this context reads a provided YAML configuration file,
 * either using the Helenus configuration file YAML definition, or a user
 * defined one.
 * <p>
 * The implemented methods provided are:
 * <ul>
 * <li>
 * <code>AbstractConfigurator withConfigFile(File)</code><br/>
 * Set the configuration file to use with the configurator</li>
 * <li>
 * <code>AbstractConfigurator read()</code><br/>
 * Read the set configuration file, and store the values in the
 * <code>yamlMap</code> attribute</li>
 * <li>
 * <code>AbstractConfigurator read(File)</code><br/>
 * Read the specified configuration file, and set it, and store the values in
 * the <code>yamlMap</code> attribute</li>
 * <li>
 * <code>Configuration get()</code><br/>
 * Retrieve the {@link Configuration} object created by a configurator</li>
 * </ul>
 * <p>
 * The method that a new configurator needs to implement is:
 * <ul>
 * <code>AbstractConfigurator config()</code><br/>
 * This method should configure the {@link Configuration} attribute, reading the
 * objects store in <code>yamlMap</code>
 * </ul>
 * <p>
 * The attributes accessible by an object extending this class are:
 * <ul>
 * <li>
 * <code>File configFile</code><br/>
 * The YAML configuration file to parse</li>
 * <li>
 * <code>Map<String, Object> yamlMap</code><br/>
 * The {@link Map} that stores the objects read form the configuration file</li>
 * <li>
 * <code>Configuration configuration</code><br/>
 * The {@link Configuration} object created from the configuration file, it has
 * to be instantiated by the extending class</li>
 * </ul>
 * 
 * @author Milo Casagrande
 * @since 0.1
 */
public abstract class AbstractConfigurator implements IConfigurator {
    /**
     * Default properties manager for the configurator.
     */
    protected static final PropertiesManager PROP_MANAGER = PropertiesManager.getInstance();

    /**
     * The configuration file to read.
     */
    protected File configFile;

    /**
     * Where to store the read YAML file.
     */
    protected Map<String, Object> yamlMap;

    /**
     * The {@link Configuration} object.
     */
    protected Configuration configuration;

    /*
     * (non-Javadoc)
     * @see com.helenus.configuration.IConfigurator#withConfigFile(java.io.File)
     */
    @Override
    public AbstractConfigurator withConfigFile(final File configFile) {
        this.configFile = configFile;
        return this;
    }

    /*
     * (non-Javadoc)
     * @see com.helenus.configuration.IConfigurator#read(java.io.File)
     */
    @Override
    public AbstractConfigurator read(final File configFile) {
        this.configFile = configFile;
        return read();
    }

    /*
     * (non-Javadoc)
     * @see com.helenus.configuration.IConfigurator#read()
     */
    @SuppressWarnings("unchecked")
    @Override
    public AbstractConfigurator read() {
        try {
            yamlMap = (Map<String, Object>) new Yaml().load(new FileInputStream(configFile));
        } catch (final FileNotFoundException ex) {
            // TODO Add logger
            ex.printStackTrace();
        }

        return this;
    }

    /*
     * (non-Javadoc)
     * @see com.helenus.configuration.IConfigurator#config()
     */
    @Override
    public abstract AbstractConfigurator config();

    /*
     * (non-Javadoc)
     * @see com.helenus.configuration.IConfigurator#get()
     */
    @Override
    public Configuration get() {
        return configuration;
    }
}
