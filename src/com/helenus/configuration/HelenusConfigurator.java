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
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.List;

import com.helenus.properties.IProperties;
import com.helenus.properties.PropType;

/**
 * Create a basic configuration based on a Helenus YAML configuration file.
 * <p>
 * Values that will be read from the configuration file:
 * <ul>
 * <li>the nodes address</li>
 * <li>the RPC (thrift) port</li>
 * <li>the JMX port</li>
 * </ul>
 * <p>
 * 
 * @author Milo Casagrande
 * @since 0.1
 */
public class HelenusConfigurator extends AbstractConfigurator {
    /**
     * The Helenus properties file manager.
     */
    private static final IProperties HELENUS_PROP = PROP_MANAGER.getManager(PropType.HELENUS_CONFIGURATOR);

    /**
     * The 'jmx_port' parameter.
     */
    private static final String JMX_PORT = HELENUS_PROP.get("helenus.jmx.port");

    /**
     * The 'rpc_port' parameter.
     */
    private static final String RPC_PORT = HELENUS_PROP.get("helenus.rpc.port");

    /**
     * The 'nodes' parameter.
     */
    private static final String NODES = HELENUS_PROP.get("helenus.nodes");

    /**
     * The 'cluster_name' parameter.
     */
    private static final String CLUSTER_NAME = HELENUS_PROP.get("helenus.cluster.name");

    /**
     * The 'keyspace' parameter.
     */
    private static final String KEYSPACE_NAME = HELENUS_PROP.get("helenus.keyspace.name");

    /**
     * The 'log_directory' parameter.
     */
    private static final String LOG_DIR = HELENUS_PROP.get("helenus.directory.log");

    /**
     * The 'nodes_capacity' parameter.
     */
    private static final String NODES_CAPACITY = HELENUS_PROP.get("helenus.nodes.capacity");

    /**
     * Create a new configurator based on an Helenus configuration file.
     * 
     * @param configFile
     *            the Helenus YAML configuration file
     */
    public HelenusConfigurator(final File configFile) {
        super();

        this.configFile = configFile;
    }

    /*
     * (non-Javadoc)
     * @see com.helenus.configuration.AbstractConfigurator#config()
     */
    @SuppressWarnings("unchecked")
    @Override
    public AbstractConfigurator config() {
        if (yamlMap.containsKey(NODES_CAPACITY)) {
            final int nodeCapacity = ((Integer) yamlMap.get(NODES_CAPACITY)).intValue();
            configuration = new Configuration(nodeCapacity);
        } else {
            configuration = new Configuration();
        }

        if (yamlMap.containsKey(JMX_PORT)) {
            final int jmxPort = ((Integer) yamlMap.get(JMX_PORT)).intValue();
            configuration.setJmxPort(jmxPort);
        }

        if (yamlMap.containsKey(RPC_PORT)) {
            final int rpcPort = ((Integer) yamlMap.get(RPC_PORT)).intValue();
            configuration.setRpcPort(rpcPort);
        }

        if (yamlMap.containsKey(NODES)) {
            final List<String> nodes = (List<String>) yamlMap.get(NODES);

            if (nodes == null) {
                try {
                    final String address = InetAddress.getLocalHost().getHostAddress();
                    configuration.addNode(address);
                } catch (final UnknownHostException ex) {
                    // TODO Add logger handle exception
                    ex.printStackTrace();
                }
            } else {
                configuration.setAllNodes(nodes);
            }
        }

        if (yamlMap.containsKey(CLUSTER_NAME)) {
            final String clusterName = (String) yamlMap.get(CLUSTER_NAME);
            configuration.setClusterName(clusterName);
        }

        if (yamlMap.containsKey(KEYSPACE_NAME)) {
            final String keyspace = (String) yamlMap.get(KEYSPACE_NAME);
            configuration.setKeyspace(keyspace);
        }

        if (yamlMap.containsKey(LOG_DIR)) {
            final String logDir = (String) yamlMap.get(LOG_DIR);
            configuration.setLogDir(logDir);
        }

        return this;
    }
}
