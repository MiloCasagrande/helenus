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

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.helenus.data.node.AbstractNode;
import com.helenus.data.node.Node;

/**
 * @author Milo Casagrande
 * @since 0.1
 */
public class NodeTest {
    private static final String URL = "www.example.com";
    private static final String URL_PORT = "www.example.com:80";
    private static final String IP_ADDRESS = "192.0.43.10"; // NOPMD
    private static final String IP_ADDRESS_PORT = "192.0.43.10:80";
    private static final int PORT = 80;
    private static final String[] EXPECTED_VALUES_1 = new String[] { "192.0.43.10", "80" }; // NOPMD
    private static final String[] EXPECTED_VALUES_2 = new String[] { "www.example.com", "80" };

    /**
     * Test the regex with the IP address and port
     */
    @Test
    public final void regexIpTest() {
        assertTrue("Regular expression is not correct", AbstractNode.matchIp("192.168.1.1:12345"));
        assertFalse("Regular expression is not correct", AbstractNode.matchIp("192.168.1.1:123456"));
        assertFalse("Regular expression is not correct", AbstractNode.matchIp("192.168.1:123456"));
    }

    /**
     * Test the regex with host name and port
     */
    @Test
    public final void regexHostTest() {
        assertTrue("Regular expression is not correct", AbstractNode.matchHost("example:8081"));
        assertFalse("Regular expression is not correct", AbstractNode.matchHost("e_xample:8080"));
        assertFalse("Regular expression is not correct", AbstractNode.matchHost("example:100001"));
    }

    /**
     * Test the regex with the full host name and the port
     */
    @Test
    public final void regexUrlTest() {
        assertTrue("Regular expression is not correct", AbstractNode.matchUrl("www.example.com:80"));
        assertTrue("Regular expression is not correct", AbstractNode.matchUrl("http://www.example.com:80"));
        assertTrue("Regular expression is not correct", AbstractNode.matchUrl("https://www.example.com:80"));
        assertTrue("Regular expression is not correct", AbstractNode.matchUrl("www.example.com:80"));
        assertTrue("Regular expression is not correct", AbstractNode.matchUrl("wWw.examplE.com:80"));
        assertTrue("Regular expression is not correct", AbstractNode.matchUrl("wWW.EXample2.3xample.Com:80"));
        assertFalse("Regular expression is not correct", AbstractNode.matchUrl("https//www.mymed.org:80"));
        assertFalse("Regular expression is not correct", AbstractNode.matchUrl("http://www.mymed.org"));
    }

    /**
     * Test the split address function
     */
    @Test
    public final void splitAddressTest1() {
        final String[] actual = AbstractNode.splitAddress("192.0.43.10:80");
        assertEquals("The values are not the same!", EXPECTED_VALUES_1[0], actual[0]);
        assertEquals("The values are not the same!", EXPECTED_VALUES_1[1], actual[1]);
    }

    /**
     * Test the split address function
     */
    @Test
    public final void splitAddressTest2() {
        final String[] actual = AbstractNode.splitAddress("http://192.0.43.10:80");
        assertEquals("The values are not the same!", EXPECTED_VALUES_1[0], actual[0]);
        assertEquals("The values are not the same!", EXPECTED_VALUES_1[1], actual[1]);
    }

    /**
     * Test the split address function
     */
    @Test
    public final void splitAddressTest3() {
        final String[] actual = AbstractNode.splitAddress("http://www.example.com:80");
        assertEquals("The values are not the same!", EXPECTED_VALUES_2[0], actual[0]);
        assertEquals("The values are not the same!", EXPECTED_VALUES_2[1], actual[1]);
    }

    /**
     * Test the split address function
     */
    @Test
    public final void splitAddressTest4() {
        final String[] actual = AbstractNode.splitAddress("www.example.com:80");
        assertEquals("The values are not the same!", EXPECTED_VALUES_2[0], actual[0]);
        assertEquals("The values are not the same!", EXPECTED_VALUES_2[1], actual[1]);
    }

    /**
     * Create a node with a normal URL
     */
    @Test
    public final void testWithUrl() {
        final Node node = new Node(URL);

        assertEquals("The IP addresses are not the same!", IP_ADDRESS, node.getIpAddress());
        assertFalse("The node is valid!", node.isValid());
    }

    /**
     * Create a node with an URL plus port
     */
    @Test
    public final void testWithUrlAndPort() {
        final Node node = new Node(URL_PORT);

        assertEquals("The IP addresses are not the same!", PORT, node.getPort());
        assertEquals("The IP addresses are not the same!", IP_ADDRESS, node.getIpAddress());
        assertTrue("The node is not valid!", node.isValid());
    }

    /**
     * Create a node with the IP address
     */
    @Test
    public final void testWithIp() {
        final Node node = new Node(IP_ADDRESS);

        assertEquals("The IP addresses are not the same!", IP_ADDRESS, node.getIpAddress());
        assertFalse("The node is valid!", node.isValid());
    }

    /**
     * Create a node with IP address plus node
     */
    @Test
    public final void testWithIpAndPort() {
        final Node node = new Node(IP_ADDRESS_PORT);

        assertEquals("The IP addresses are not the same!", PORT, node.getPort());
        assertEquals("The IP addresses are not the same!", IP_ADDRESS, node.getIpAddress());
        assertTrue("The node is not valid!", node.isValid());
    }

    /**
     * Test that two nodes are not equals
     */
    @Test
    public final void testNotEquals() {
        final Node node1 = new Node(IP_ADDRESS_PORT);
        final Node node2 = new Node("192.168.2.255", 81); // NOPMD

        assertFalse("The nodes are the same!", node1.equals(node2));
    }
}
