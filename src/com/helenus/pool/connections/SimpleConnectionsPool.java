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
package com.helenus.pool.connections;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.atomic.AtomicLong;

import com.helenus.data.connection.IConnection;

public class SimpleConnectionsPool implements INodesPool {
    /**
     * Maximum number of connections per node.
     */
    private static final int DEFAULT_MAX_CONN = 12;

    /**
     * Shared atomic COUNTER to provide a unique ID number for each pool.
     */
    private static final AtomicLong COUNTER = new AtomicLong(Long.MIN_VALUE);

    private BlockingQueue<IConnection> pool;
    private final String address;
    private final int port;
    private final int maxConnection;

    public SimpleConnectionsPool(final String address, final int port) {
        this(address, port, DEFAULT_MAX_CONN);
    }

    public SimpleConnectionsPool(final String address, final int port, final int maxConnection) {
        this.address = address;
        this.port = port;
        this.maxConnection = maxConnection;
    }

    /**
     * Retrieves a connection from the pool.
     * 
     * @return an instance of an {@link IConnection}
     */
    @Override
    public IConnection get() {
        // TODO Auto-generated method stub
        return null;
    }

    /**
     * Returns a connection to the pool.
     * 
     * @param connection
     *            the {@link IConnection} to return to the pool
     */
    @Override
    public void give(final IConnection connection) {
        // TODO Auto-generated method stub
    }

    /*
     * (non-Javadoc)
     * @see com.helenus.pool.IConnectionPool#getId()
     */
    @Override
    public long getId() {
        return COUNTER.incrementAndGet();
    }
}
