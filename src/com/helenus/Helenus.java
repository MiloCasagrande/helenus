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
package com.helenus;

import com.helenus.configuration.Configuration;
import com.helenus.configuration.IConfigurator;
import com.helenus.pool.connections.INodesPool;
import com.helenus.pool.nodes.NodesPoolType;

/**
 * @author Milo Casagrande
 * @since 0.1
 */
public class Helenus {
    /**
     * The Helenus configuration.
     */
    private Configuration configuration;

    /**
     * The {@link IConfigurator} used to create the {@link Configuration}.
     */
    private IConfigurator configurator;

    /**
     * The {@link INodesPool} used to manage the connection to Cassandra.
     */
    private INodesPool connectionPool;

    /**
     * Get the {@link INodesPool} associated with this instance.
     * 
     * @return the {@link INodesPool}
     */
    public INodesPool getConnectionPool() {
        return connectionPool;
    }

    /**
     * Set the {@link INodesPool} associated with this instance.
     * 
     * @param connectionPool
     *            the {@link INodesPool} to set
     */
    public void setConnecionPool(final INodesPool connectionPool) {
        this.connectionPool = connectionPool;
    }

    /**
     * Get the {@link Configuration} associated with this instance.
     * 
     * @return the {@link Configuration}
     */
    public Configuration getConfiguration() {
        return configuration;
    }

    /**
     * Set the {@link Configuration} associated with this instance.
     * 
     * @param configuration
     *            the {@link Configuration} to set
     */
    public void setConfiguration(final Configuration configuration) {
        this.configuration = configuration;
    }

    /**
     * Get the {@link IConfigurator} associated with this instance.
     * 
     * @return the {@link IConfigurator}
     */
    public IConfigurator getConfigurator() {
        return configurator;
    }

    /**
     * Set the configurator to use with this instance.
     * 
     * @param configurator
     *            the {@link IConfigurator} to set
     */
    public void setConfigurator(final IConfigurator configurator) {
        this.configurator = configurator;
    }

    /**
     * Set the {@link IConfigurator} to be used to create the initial {@link Configuration}.
     * 
     * @param configurator
     *            the {@link IConfigurator} to use
     * @return this {@link Helenus} object
     */
    public Helenus withConfigurator(final IConfigurator configurator) {
        setConfigurator(configurator);
        return this;
    }

    /**
     * Set the {@link Configuration} to use throughout the life of this object.
     * <p>
     * The {@link Configuration} must be a valid Helenus configuration object.
     * 
     * @param configuration
     *            the {@link Configuration} to use
     * @return this {@link Helenus} object
     */
    public Helenus withConfiguration(final Configuration configuration) {
        setConfiguration(configuration);
        return this;
    }

    /**
     * Set the {@link INodesPool} to use with this instance.
     * 
     * @param nodesPool
     *            the {@link INodesPool} to use
     * @return this {@link Helenus} object
     */
    public Helenus withNodesPoolType(final NodesPoolType type) {
        switch (type) {
            case SIMPLE:
                break;
            default:
                break;
        }
        return this;
    }
}
