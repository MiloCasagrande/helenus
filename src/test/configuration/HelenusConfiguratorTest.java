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
package test.configuration;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

import java.io.File;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.HashSet;
import java.util.Set;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.helenus.configuration.Configuration;
import com.helenus.configuration.HelenusConfigurator;

/**
 * @author mcasagr
 */
public class HelenusConfiguratorTest {

    private Configuration configuration;
    private static File configFile;
    private static File wrongConfigFile;
    private Set<String> nodes;

    @BeforeClass
    public static void setUpBeforeClass() {
        configFile = new File("configuration/resources/helenus.yaml");
        wrongConfigFile = new File("configuration/resources/wrong-helenus.yaml");
    }

    @Before
    public void setUpBefore() {
        nodes = new HashSet<String>(4);
        nodes.add("127.0.0.1"); // NOPMD
        nodes.add("127.0.1.1"); // NOPMD
        nodes.add("127.0.1.2:4202"); // NOPMD

        configuration = new Configuration();
        configuration.setRpcPort(4201);
        configuration.setJmxPort(8080);
        configuration.setNodes(nodes);
        configuration.setClusterName("Test cluster");
        configuration.setKeyspace("Keyspace");
        configuration.setLogDir("/var/log/helenus"); // NOPMD
    }

    @After
    public void tearDownAfter() {
        configuration.clear();
        configuration = null; // NOPMD
        nodes.clear();
    }

    /**
     * Kind of useless test.
     * <p>
     * Test if one object equals itself.
     */
    @Test
    public void sameObjectEqualsTest() {
        assertEquals("The configuration objects are not the same!", configuration, configuration);
    }

    /**
     * Read the provided Helenus configuration file, and test the obtained configuration with the one created here.
     */
    @Test
    public void readHelenusFile() {
        final HelenusConfigurator conf = new HelenusConfigurator(configFile);
        final Configuration readConfiguration = conf.read().config().get();
        assertEquals("The configuration objects are not the same!", configuration, readConfiguration);
    }

    /**
     * Read the provided wrong Helenus configuration file, and test the obtained configuration with the one created
     * here.
     */
    @Test
    public void readWrongHelenusFile() {
        final HelenusConfigurator conf = new HelenusConfigurator(wrongConfigFile);
        final Configuration readConfiguration = conf.read().config().get();
        assertFalse("The configuration objects are the same!", configuration.equals(readConfiguration));
    }

    /**
     * Read the provided wrong Helenus configuration file, add two new nodes and test the obtained configuration with
     * the one created here.
     * 
     * @throws UnknownHostException
     */
    @Test
    public void addNewNodeTest() throws UnknownHostException {
        final HelenusConfigurator conf = new HelenusConfigurator(wrongConfigFile);
        final Configuration readConfiguration = conf.read().config().get();
        readConfiguration.addNode("127.0.0.1");
        configuration.addNode(InetAddress.getLocalHost().getHostAddress());
        readConfiguration.addNode("127.0.1.1");
        readConfiguration.addNode("127.0.1.2:4202");
        assertEquals("The configuration objects are not the same!", configuration, readConfiguration);
    }

    /**
     * Clone the configuration and test if it equals the one create here.
     */
    @Test
    public void cloneTest() {
        final Configuration actual = configuration.clone();
        assertEquals("The configuration objects are not the same!", configuration, actual);
    }

    /**
     * Clone the configuration create here and test the clear method.
     */
    @Test
    public void clearTest() {
        final Configuration actual = configuration.clone();
        actual.clear();
        assertFalse("The configuration objects are the same!", configuration.equals(actual));
    }

    /**
     * Test the hashCode function, cloning the configuration and checking the value
     */
    @Test
    public void testHashCode() {
        final Configuration actual = configuration.clone();
        assertEquals("The hash codes are not the same!", configuration.hashCode(), actual.hashCode());
    }
}
