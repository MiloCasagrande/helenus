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

import java.io.Serializable;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Holds configuration of the Cassandra ring.
 * <p>
 * The default configuration values stored are:
 * <ul>
 * <li>{@link List} of nodes</li>
 * <li>RPC port (default value is <tt>9160</tt>)</li>
 * <li>JMX port (default value is <tt>8080</tt>)</li>
 * </ul>
 * Optional values are:
 * <ul>
 * <li>cluster name</li>
 * </ul>
 * 
 * @author Milo Casagrande
 * @since 0.1
 */
public class Configuration implements Serializable, Cloneable {
    /**
     * Class serial version number.
     */
    private static final long serialVersionUID = -6233815479966805261L;

    /**
     * Default value for the JMX port.
     */
    private static final int JMX_PORT = 8080;

    /**
     * Default initial capacity for the {@link Set} holding the nodes.
     */
    private static final int NODE_CAPACITY = 16;

    /**
     * The list of nodes in the ring.
     */
    private Set<String> nodes;

    /**
     * The RPC port in use.
     */
    private int rpcPort = -1;

    /**
     * The JMX port in use.
     */
    private int jmxPort = JMX_PORT;

    /**
     * The name of the cluster.
     */
    private String clusterName = "";

    /**
     * The name of the keyspace in use.
     */
    private String keyspace = "";

    /**
     * The directory used for storing logs.
     */
    private String logDir = "";

    /**
     * Create a new {@link Configuration}.
     * <p>
     * The new Configuration will have by default the RPC port set to
     * <tt>9160</tt>, and the JMX port set to <tt>8080</tt>.
     */
    public Configuration() {
        this(NODE_CAPACITY);
    }

    /**
     * Create a new {@link Configuration}.
     * <p>
     * The new configuration will have by default the RPC port set to
     * <tt>9160</tt>, and the JMX port set to <tt>8080</tt>.
     * 
     * @param size
     *            how many nodes to initially store
     */
    public Configuration(final int size) {
        super();

        nodes = Collections.synchronizedSet(new HashSet<String>(size));
    }

    /**
     * Copy constructor.
     * <p>
     * Used to provide a clone of the actual object.
     * 
     * @param toClone
     */
    protected Configuration(final Configuration toClone) {
        super();

        nodes = toClone.getNodes();
        rpcPort = toClone.getRpcPort();
        jmxPort = toClone.getJmxPort();
        keyspace = toClone.getKeyspace();
        clusterName = toClone.getClusterName();
        logDir = toClone.getLogDir();
    }

    /*
     * (non-Javadoc)
     * @see java.lang.Object#clone()
     */
    @Override
    public Configuration clone() {
        return new Configuration(this);
    }

    /**
     * Clear the configuration.
     * <p>
     * Set the RPC and JMX ports to their default values, reset the cluster name
     * and the nodes list
     */
    public void clear() {
        jmxPort = JMX_PORT;
        rpcPort = -1;
        clusterName = "";

        if (nodes != null) {
            nodes.clear();
        }
    }

    /**
     * Set the nodes associated with this configuration.
     * 
     * @param nodes
     *            {@link List} of the nodes
     */
    public void setNodes(final Set<String> nodes) {
        this.nodes = nodes;
    }

    /**
     * Adds an entire collection of nodes to this configuration.
     * 
     * @param collection
     *            the set of nodes to be added
     */
    public void setAllNodes(final Collection<String> collection) {
        nodes.addAll(collection);
    }

    /**
     * @return {@link List} of the nodes
     */
    public Set<String> getNodes() {
        return nodes;
    }

    /**
     * Adds a new node address to the configuration.
     * 
     * @param node
     *            the node to add
     * @return <tt>true</tt> if the set of nodes did not contain the specified
     *         element
     */
    public boolean addNode(final String node) {
        return nodes.add(node);
    }

    /**
     * Sets the RPC port used for the connection.
     * 
     * @param rpcPort
     *            the RPC port in use
     */
    public void setRpcPort(final int rpcPort) {
        this.rpcPort = rpcPort;
    }

    /**
     * @return the RPC port
     */
    public int getRpcPort() {
        return rpcPort;
    }

    /**
     * Sets the JMX port used by Cassandra.
     * 
     * @param jmxPort
     *            the JMX port in use
     */
    public void setJmxPort(final int jmxPort) {
        this.jmxPort = jmxPort;
    }

    /**
     * @return the JMX port
     */
    public int getJmxPort() {
        return jmxPort;
    }

    /**
     * Set the cluster name associated with this configuration.
     * 
     * @param clusterName
     *            the cluster name
     */
    public void setClusterName(final String clusterName) {
        this.clusterName = clusterName;
    }

    /**
     * @return the name of the cluster
     */
    public String getClusterName() {
        return clusterName;
    }

    /**
     * @return the keyspace
     */
    public String getKeyspace() {
        return keyspace;
    }

    /**
     * Set the name of the keyspace in use.
     * 
     * @param keyspace
     *            the name of the keyspace
     */
    public void setKeyspace(final String keyspace) {
        this.keyspace = keyspace;
    }

    /**
     * @return the log directory
     */
    public String getLogDir() {
        return logDir;
    }

    /**
     * Set the directory used for storing logs.
     * <p>
     * Needs to be an absolute path.
     * 
     * @param logDir
     *            the path of the directory
     */
    public void setLogDir(final String logDir) {
        this.logDir = logDir;
    }

    /*
     * (non-Javadoc)
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
        int result = 1;
        result = (31 * result) + (nodes == null ? 0 : nodes.hashCode());
        result = (31 * result) + (rpcPort ^ (rpcPort >>> 32));
        result = (31 * result) + (jmxPort ^ (jmxPort >>> 32));
        result = (31 * result) + (clusterName == null ? 0 : clusterName.hashCode());
        result = (31 * result) + (keyspace == null ? 0 : keyspace.hashCode());
        result = (31 * result) + (logDir == null ? 0 : logDir.hashCode());
        return result;
    }

    /*
     * (non-Javadoc)
     * @see java.lang.Object#equals(java.lang.Object)
     */
    @Override
    public boolean equals(final Object obj) {

        boolean equal = false;

        if (this == obj) {
            equal = true;
        } else if (obj instanceof Configuration) {
            final Configuration comparable = (Configuration) obj;

            equal = true;

            if (((nodes == null) && (comparable.getNodes() != null))
                    || ((nodes != null) && (comparable.getNodes() == null))) {
                equal &= false;
            } else if ((nodes != null) && (comparable.getNodes() != null)) {
                equal &= nodes.equals(comparable.getNodes());
            }

            equal &= rpcPort == comparable.getRpcPort();
            equal &= jmxPort == comparable.getJmxPort();

            if (((clusterName == null) && (comparable.getClusterName() != null))
                    || ((clusterName != null) && (comparable.getClusterName() == null))) {
                equal &= false;
            } else if ((clusterName != null) && (comparable.getClusterName() != null)) {
                equal &= clusterName.equals(comparable.getClusterName());
            }

            if (((keyspace == null) && (comparable.getKeyspace() != null))
                    || ((keyspace != null) && (comparable.getKeyspace() == null))) {
                equal &= false;
            } else if ((keyspace != null) && (comparable.getKeyspace() != null)) {
                equal &= keyspace.equals(comparable.getKeyspace());
            }

            if (((logDir == null) && (comparable.getLogDir() != null))
                    || ((logDir != null) && (comparable.getLogDir() == null))) {
                equal &= false;
            } else if ((logDir != null) && (comparable.getLogDir() != null)) {
                equal &= logDir.equals(comparable.getLogDir());
            }
        }

        return equal;
    }

    /*
     * (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        final StringBuffer buffer = new StringBuffer(150);
        buffer.append("Configuration:\n\tCluster Name: ");
        buffer.append(clusterName);
        buffer.append("\n\tKeyspace: ");
        buffer.append(keyspace);
        buffer.append("\n\tJMX Port: ");
        buffer.append(jmxPort);
        buffer.append("\n\tRPC Port: ");
        buffer.append(rpcPort);
        buffer.append("\n\tLog Dir: ");
        buffer.append(logDir);
        if (!nodes.isEmpty()) {
            buffer.append("\n\tNodes:\n");
            for (final String node : nodes) {
                buffer.append("\t\t");
                buffer.append(node);
                buffer.append('\n');
            }
        }

        buffer.trimToSize();
        return buffer.toString();
    }
}
