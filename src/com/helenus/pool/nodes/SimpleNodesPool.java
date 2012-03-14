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

import java.util.concurrent.atomic.AtomicLong;

import com.helenus.data.connection.IConnection;
import com.helenus.data.node.INode;

/**
 * @author Milo Casagrande
 * @since 0.1
 */
public class SimpleNodesPool implements INodesPool {
    /**
     * Private counter for an internal ID.
     */
    private static final AtomicLong COUNTER = new AtomicLong(Long.MIN_VALUE);

    @Override
    public INode get() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void give(final INode object) {
        // TODO Auto-generated method stub
    }

    /*
     * (non-Javadoc)
     * @see com.helenus.pool.IPool#getId()
     */
    @Override
    public long getId() {
        return COUNTER.incrementAndGet();
    }

    /*
     * (non-Javadoc)
     * @see com.helenus.pool.nodes.INodesPool#getConnection()
     */
    @Override
    public IConnection getConnection() {
        // TODO Auto-generated method stub
        return null;
    }

    /*
     * (non-Javadoc)
     * @see com.helenus.pool.nodes.INodesPool#giveConnection(com.helenus.data.connection.IConnection)
     */
    @Override
    public void giveConnection(final IConnection connection) {
        // TODO Auto-generated method stub

    }
}
