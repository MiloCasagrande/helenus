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
package com.helenus.properties;

/**
 * Interface to define a common base of methods for retrieving properties file
 * information.
 * 
 * @author Milo Casagrande
 * @since 0.1
 */
public interface IPropType {
    /**
     * @return the name of the properties holder
     */
    String getName();

    /**
     * @return the name of the file holding the properties
     */
    String getFileName();
}
