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
 * Create a basic configuration based on a Cassandra YAML configuration file.
 * <p>
 * Values that will be read from the configuration file:
 * <ul>
 * <li>the cluster name</li>
 * <li>the seeds address, that will be used as nodes</li>
 * <li>the RPC (thrift) port</li>
 * </ul>
 * <p>
 * 
 * @author Milo Casagrande
 * @since 0.1
 */
public class CassandraConfigurator extends AbstractConfigurator {

    /**
     * The Cassandra properties file manager.
     */
    private static final IProperties CASSANDRA_PROP = PROP_MANAGER.getManager(PropType.CASSANDRA_CONFIGURATOR);

    /**
     * The 'cluster_name' parameter.
     */
    private static final String CLUSTER_NAME = CASSANDRA_PROP.get("cassandra.cluster.name");

    /**
     * The 'seeds' parameter.
     */
    private static final String SEEDS = CASSANDRA_PROP.get("cassandra.seeds");

    /**
     * The 'rpc_port' parameter.
     */
    private static final String RPC_PORT = CASSANDRA_PROP.get("cassandra.rpc.port");

    /**
     * Creates a new configurator based on a Cassandra YAML configuration file.
     * 
     * @param configFile
     *            the Cassandra YAML configuration file
     */
    public CassandraConfigurator(final File configFile) {
        super();

        this.configFile = configFile;
    }

    /*
     * (non-Javadoc)
     * @see com.helenus.configuration.AbstractConfigurator#config()
     */
    @Override
    public AbstractConfigurator config() {
        if (yamlMap.containsKey(SEEDS)) {
            @SuppressWarnings("unchecked")
            final List<String> nodes = (List<String>) yamlMap.get(SEEDS);

            if (nodes == null) {
                configuration = new Configuration();

                try {
                    final String address = InetAddress.getLocalHost().getHostAddress();
                    configuration.addNode(address);
                } catch (final UnknownHostException ex) {
                    // TODO add logger, handle exception
                    ex.printStackTrace();
                }
            } else {
                configuration = new Configuration(nodes.size());
                configuration.setAllNodes(nodes);
            }
        }

        if (yamlMap.containsKey(CLUSTER_NAME)) {
            final String clusterName = (String) yamlMap.get(CLUSTER_NAME);
            configuration.setClusterName(clusterName);
        }

        if (yamlMap.containsKey(RPC_PORT)) {
            final int rpcPort = ((Integer) yamlMap.get(RPC_PORT)).intValue();
            configuration.setRpcPort(rpcPort);
        }

        return this;
    }
}
