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
package com.helenus.pool.nodes;

import com.helenus.data.connection.IConnection;
import com.helenus.data.node.INode;
import com.helenus.pool.IPool;

/**
 * Nodes pool interface extending {@link IPool}.
 * <p>
 * This interface permits to have a more specialized nodes pool class.
 * 
 * @author Milo Casagrande
 * @since 0.1
 */
public interface INodesPool extends IPool<INode> {
    /**
     * Gets a new {@link IConnection} from this node.
     * 
     * @return a connection to this node
     */
    IConnection getConnection();

    /**
     * Returns back a used {@link IConnection}.
     * 
     * @param connection
     *            the connection to give back
     */
    void giveConnection(IConnection connection);
}
