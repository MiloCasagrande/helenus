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
package test.pool;

import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.helenus.pool.connections.INodesPool;
import com.helenus.pool.connections.SimpleConnectionsPool;

public class SimpleConnectionPoolTest {

    @BeforeClass
    public static void setUpBeforeClass() {
    }

    @AfterClass
    public static void tearDownAfterClass() {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    @Test
    public final void test() {
        final INodesPool pool1 = new SimpleConnectionsPool("127.0.0.1", 4201);
        final INodesPool pool2 = new SimpleConnectionsPool("127.0.0.1", 4201);
        final INodesPool pool3 = new SimpleConnectionsPool("127.0.0.1", 4201);

        assertTrue(pool1.getId() != pool2.getId());
        assertTrue(pool2.getId() != pool3.getId());
        assertTrue(pool3.getId() != pool1.getId());
    }

}
