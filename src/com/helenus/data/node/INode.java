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

import com.helenus.data.connection.IConnection;

/**
 * Basic interface for a node in a Cassandra ring.
 * 
 * @author Milo Casagrande
 * @since 0.1
 */
public interface INode {
    /**
     * A node is valid if its most important attributes have different values than the default ones.
     * <p>
     * The most important attributes are:
     * <ol>
     * <li>its IP address</li>
     * <li>its port</li>
     * </ol>
     * 
     * @return <tt>true</tt> if the node is valid, <tt>false</tt> otherwise
     */
    boolean isValid();

    /**
     * Gets the IP address associated with this node.
     * 
     * @return the IP address of this node
     */
    String getIpAddress();

    /**
     * Gets the port associated with this node.
     * 
     * @return the port to connect to
     */
    int getPort();

    /**
     * Gets the ID associated with this node.
     * <p>
     * The node ID is composed of IP address and port in the form of: <tt>IP:PORT</tt>.
     * 
     * @return the node ID
     */
    String getId();

    /**
     * Gets a connection from this node connections pool.
     * <p>
     * The connections are provided on a simple way from the connections pool.
     * 
     * @return an open connection to this node
     */
    IConnection getConnection();
}
