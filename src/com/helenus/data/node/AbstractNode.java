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
package com.helenus.data.node;

import java.io.Serializable;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.regex.Pattern;


public abstract class AbstractNode implements INode, Serializable, Cloneable {
    /**
     * Generated serial version number.
     */
    private static final long serialVersionUID = -1927694393557428805L;

    /**
     * Simple pattern for matching an <tt>IP</tt> with a <tt>PORT</tt> in the
     * form of <tt>IP:PORT</tt>. The IP has to be a normal IPv4 address.
     */
    private static final Pattern IP_PORT_PATT = Pattern.compile("^(\\d{1,3}\\.){3}(\\d{1,3}){1}[:]{1}\\d{1,5}");

    /**
     * Simple pattern for matching a <tt>HOST</tt>, as in a Linux host, with the
     * defined <tt>PORT</tt> in the form of <tt>HOST:PORT</tt>.
     */
    private static final Pattern HOST_PORT_PATT = Pattern.compile("^([a-zA-Z\\d]){1,64}[:]{1}\\d{1,5}");

    /**
     * Simple pattern to match a <tt>URL</tt> with the defined <tt>PORT</tt> in
     * the form of <tt>URL:PORT</tt>. It should be something like
     * <tt>[http(s)://]www.example.org:80</tt>
     */
    private static final Pattern URL_PORT_PATT = Pattern
            .compile("^(https?[:]{1}[/]{1}[/]{1})?([a-zA-Z\\d]{1,}\\.{1}){1,}([a-zA-Z\\d]{1,}){1}[:]{1}\\d{1,5}");

    /**
     * Checks whatever an address is in the form of <tt>IP:PORT</tt>.
     * <p>
     * The IP address has to be a valid IP address, no check is performed on its
     * validity, so if you pass <tt>999.999.999.999:80</tt>, it will be
     * accepted.
     * 
     * @param address
     *            the address to check
     * @return <tt>true</tt> if the check matches
     */
    public static boolean matchIp(final String address) {
        return IP_PORT_PATT.matcher(address).matches();
    }

    /**
     * Checks whatever an address is in the form of <tt>HOST:PORT</tt>.
     * <p>
     * The HOST has to be a valid hostname obeying to the Linux hostname
     * convention. It has also to be reachable and resolvable through the
     * network.
     * 
     * @param address
     *            the address to check
     * @return <tt>true</tt> if the check matches
     */
    public static boolean matchHost(final String address) {
        return HOST_PORT_PATT.matcher(address).matches();
    }

    /**
     * Checks whatever an address is in the form of <tt>URL:PORT</tt>.
     * <p>
     * The URL has to be a valid HTTP-form URL address.
     * 
     * @param address
     *            the address to check
     * @return <tt>true</tt> if the check matches
     */
    public static boolean matchUrl(final String address) {
        return URL_PORT_PATT.matcher(address).matches();
    }

    /**
     * Split an address string in two parts: the real address one, and the port.
     * 
     * @param address
     *            the address to split
     * @return an array of length two, where the element at position <tt>0</tt>
     *         is the address, and the element at position <tt>1</tt> is the
     *         port
     */
    public static String[] splitAddress(final String address) {
        /*
         * General way for splitting a string: since we might have IPs, HOSTs,
         * and URLs, we first split looking for a URL form and split at the '//'
         * point. After, we split where the delimiter for the port is.
         */
        final String[] firstSplit = address.split("//");
        String[] results = new String[2];

        if (firstSplit.length == 1) {
            results = address.split(":");
        } else {
            results = splitAddress(firstSplit[1]);
        }

        return results;
    }

    /**
     * Tries to resolve the provided address, in order to retrieve the values
     * for a node.
     * 
     * @param address
     *            the address to resolve
     * @return the {@link InetAddress} to retrieve the values from
     */
    protected static InetAddress resolveAddress(final String address) {
        InetAddress inet = null;

        try {
            inet = InetAddress.getByName(address);
        } catch (final UnknownHostException ex) {
            // TODO add logger, handle exception
            ex.printStackTrace();
        }

        return inet;
    }
}
