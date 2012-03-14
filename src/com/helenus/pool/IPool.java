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
package com.helenus.pool;

/**
 * Generic interface for a pool type.
 * 
 * @author Milo Casagrande
 * @since 0.1
 * @param <T>
 *            the type of the elements this pool will handle
 */
public interface IPool<T> {
    /**
     * Retrieves an object of the specified type from the pool.
     * 
     * @return an instance of the specified object
     */
    T get();

    /**
     * Returns an object of the specified type to the pool.
     * 
     * @param object
     *            the object to return
     */
    void give(T object);

    /**
     * Retrieves a generated unique ID for the pool.
     * 
     * @return the id of the pool
     */
    long getId();
}
