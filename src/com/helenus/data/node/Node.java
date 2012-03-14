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
package com.helenus.data.node;

import java.net.InetAddress;

import com.helenus.data.connection.IConnection;
import com.helenus.pool.connections.INodesPool;

/**
 * A node is a host in a Cassandra ring.
 * <p>
 * It is identified with an IP address and a port to access it. A node can have multiple open connections, available
 * through a pool of connections.
 * <p>
 * The address used to create a node should be resolved via the normal network, and should be a valid address
 * identifying a machine running Cassandra. Some information about the node will be gathered via the network: if no such
 * information, as IP address and port, are accessible, the node will be considered as not valid, and will not become
 * part of the pool.
 * 
 * @author Milo Casagrande
 * @since 0.1
 */
public class Node extends AbstractNode {
    /**
     * Generated serial version number.
     */
    private static final long serialVersionUID = 6793165454651678042L;

    /**
     * The IP address of the node.
     */
    private String ipAddress;

    /**
     * The short host name, for example the name of the computer.
     */
    private String hostName;

    /**
     * The fully qualified host hame, like www.eample.org.
     */
    private String fullHostName;

    /**
     * The port to connect to on this node.
     * <p>
     * Must be a positive integer between 1 and 65535.
     */
    private int port = -1;

    /**
     * The pool of connections associated with this node.
     */
    private INodesPool connectionPool;

    /**
     * Create a new node based on the provided address.
     * <p>
     * The <code>address</code> in this case can be either:
     * <ul>
     * <li>IP address</i>
     * <li>URL of the server (as <tt>www.example.org</tt>)</li>
     * <li>host name (as <tt>myhost</tt>), as long as it can be resolved</li>
     * </ul>
     * <p>
     * <b>WARN</b>: a node created in this way is <b>not</b> {@link #isValid() valid}, it is necessary to also specify
     * the port.<br/>
     * To make it valid, the <code>address</code> parameter can be specified also as <tt>IP:PORT</tt>, <tt>URL:PORT</tt>
     * , or <tt>HOST:PORT</tt> in order to pass the port.
     * 
     * @param address
     *            the address to resolve
     */
    public Node(final String address) {
        String localAddress = address;

        if (matchIp(address) || matchHost(address) || matchUrl(address)) {
            final String[] values = splitAddress(address);
            localAddress = values[0];
            port = Integer.valueOf(values[1]).intValue();
        }

        final InetAddress inet = resolveAddress(localAddress);
        setValues(inet);
    }

    /**
     * Create a new node based on the provided address and port.
     * <p>
     * Address in this case can be either:
     * <ul>
     * <li>IP address</i>
     * <li>URL of the server (as <tt>www.example.org</tt>)</li>
     * <li>host name (as <tt>myhost</tt>), as long as it can be resolved</li>
     * </ul>
     * 
     * @param address
     *            the address to resolve
     * @param port
     *            the port to connect to
     */
    public Node(final String address, final int port) {
        this.port = port;

        final InetAddress inet = resolveAddress(address);
        setValues(inet);
    }

    /*
     * (non-Javadoc)
     * @see com.helenus.pool.INode#getIpAddress()
     */
    @Override
    public String getIpAddress() {
        return ipAddress;
    }

    /**
     * Sets the IP address of this node.
     * 
     * @param ipAddress
     *            the IP address to set
     * @return this object
     */
    public Node setIpAddress(final String ipAddress) {
        this.ipAddress = ipAddress;
        return this;
    }

    /**
     * Gets the simple host name of the machine.
     * <p>
     * The host name is defined as in Linux form, and usually is the name of the machine, as long as it can be resolved.
     * 
     * @return the host name
     */
    public String getHostName() {
        return hostName;
    }

    /**
     * Sets the host name of this node.
     * 
     * @param hostName
     *            the hostname to set
     * @return this object
     */
    public Node setHostName(final String hostName) {
        this.hostName = hostName;
        return this;
    }

    /**
     * Set the full hostname of this node. If set up correctly, the node will have an hostname (the name of the
     * computer), and a fully hostname that usually is the hostname followed by the domains where it is located.
     * 
     * @param fullHostName
     *            the full host name
     * @return this object
     */
    public Node setFullHostName(final String fullHostName) {
        this.fullHostName = fullHostName;
        return this;
    }

    /**
     * Get the full hostname of this node.
     * 
     * @return the full host name
     */
    public String getFullHostName() {
        return fullHostName;
    }

    /*
     * (non-Javadoc)
     * @see com.helenus.pool.INode#getPort()
     */
    @Override
    public int getPort() {
        return port;
    }

    /**
     * Set the port associated with this node.
     * <p>
     * The port is used in conjunction with the IP address to establish a connection.
     * 
     * @param port
     *            the port to use
     * @return this object
     */
    public Node setPort(final int port) {
        this.port = port;
        return this;
    }

    /*
     * (non-Javadoc)
     * @see com.helenus.pool.INode#isValid()
     */
    @Override
    public boolean isValid() {
        return (port != -1) && (ipAddress != null);
    }

    /*
     * (non-Javadoc)
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
        int result = 1;
        result = (31 * result) + ((ipAddress == null) ? 0 : ipAddress.hashCode());
        result = (31 * result) + port;
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
        } else if (obj instanceof Node) {
            final Node comparable = (Node) obj;

            equal = true;

            equal &= port == comparable.getPort();

            if (((ipAddress == null) && (comparable.getIpAddress() != null))
                            || ((ipAddress != null) && (comparable.getIpAddress() == null))) {
                equal &= false;
            } else if ((ipAddress != null) && (comparable.getIpAddress() != null)) {
                equal &= ipAddress.equals(comparable.getIpAddress());
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
        final StringBuffer nodeBuffer = new StringBuffer(150);
        nodeBuffer.append("IP Address: ");
        nodeBuffer.append(ipAddress);
        nodeBuffer.append("\nPort: ");
        nodeBuffer.append(port);
        nodeBuffer.append("\nHost Name: ");
        nodeBuffer.append(hostName);

        nodeBuffer.trimToSize();

        return nodeBuffer.toString();
    }

    /**
     * Sets the values of this node based on the provided {@link InetAddress}.
     * 
     * @param inet
     *            the {@link InetAddress} to get the values from
     */
    private void setValues(final InetAddress inet) {
        // If we are not able to resolve the address, the node will not be valid
        // and we do not do anything
        if (inet != null) {
            ipAddress = inet.getHostAddress();
            hostName = inet.getHostName();
            fullHostName = inet.getCanonicalHostName();
        }
    }

    /*
     * (non-Javadoc)
     * @see com.helenus.pool.INode#getId()
     */
    @Override
    public String getId() {
        return ipAddress + ":" + port;
    }

    /*
     * (non-Javadoc)
     * @see com.helenus.data.node.INode#getConnection()
     */
    @Override
    public IConnection getConnection() {
        return connectionPool.get();
    }
}
