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

/**
 * Enumeration used to define the types of nodes pool available.
 * <p>
 * A type of nodes pool identifies the algorithm with which the nodes will be
 * chosen. The connections will be provided on a simple basis, per each node.
 * 
 * @author Milo Casagrande
 * @since 0.1
 */
public enum NodesPoolType {
    /**
     * Simple nodes pool type.
     * <p>
     * Nodes are provided in a LIFO way.
     */
    SIMPLE,
    /**
     * Nodes pool backed by a round-robin algorithm.
     * <p>
     * Nodes are provided in a round-robin way from the pool of nodes.
     */
    ROUNDROBIN;
}
