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

/**
 * Implementation of the {@link IPropType} interface.
 * 
 * @author Milo Casagrande
 * @since 0.1
 */
public enum PropType implements IPropType {
    /**
     * Properties name and properties file for the Helenus configuration file.
     */
    HELENUS_CONFIGURATOR("helenus.configurator", "helenus.configurator.properties"), CASSANDRA_CONFIGURATOR(
            "cassandra.configurator", "cassandra.configurator.properties");

    /**
     * The name of the property, has used for the hash map.
     */
    private String name;

    /**
     * The name of the file holding the properties.
     */
    private String fileName;

    /**
     * Set up the enum type.
     * 
     * @param name
     *            the properties name
     * @param fileName
     *            the file name
     */
    private PropType(final String name, final String fileName) {
        this.name = name;
        this.fileName = fileName;
    }

    /*
     * (non-Javadoc)
     * @see com.helenus.properties.IPropType#getName()
     */
    @Override
    public String getName() {
        return name;
    }

    /*
     * (non-Javadoc)
     * @see com.helenus.properties.IPropType#getFileName()
     */
    @Override
    public String getFileName() {
        return fileName;
    }
}
