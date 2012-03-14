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
package test;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import test.configuration.CassandraConfiguratorTest;
import test.configuration.HelenusConfiguratorTest;
import test.pool.NodeTest;
import test.pool.SimpleConnectionPoolTest;

@RunWith(Suite.class)
@SuiteClasses({ CassandraConfiguratorTest.class, HelenusConfiguratorTest.class, NodeTest.class,
                SimpleConnectionPoolTest.class })
public class AllTestsSuite {
    // NOPMD
}
