//
//  ========================================================================
//  Copyright (c) 1995-2012 Mort Bay Consulting Pty. Ltd.
//  ------------------------------------------------------------------------
//  All rights reserved. This program and the accompanying materials
//  are made available under the terms of the Eclipse Public License v1.0
//  and Apache License v2.0 which accompanies this distribution.
//
//      The Eclipse Public License is available at
//      http://www.eclipse.org/legal/epl-v10.html
//
//      The Apache License v2.0 is available at
//      http://www.opensource.org/licenses/apache2.0.php
//
//  You may elect to redistribute this code under either of these licenses.
//  ========================================================================
//

package org.eclipse.jetty.websocket.common;

import java.io.IOException;
import java.net.InetSocketAddress;

import org.eclipse.jetty.websocket.api.StatusCode;
import org.eclipse.jetty.websocket.api.SuspendToken;
import org.eclipse.jetty.websocket.api.WebSocketPolicy;
import org.eclipse.jetty.websocket.api.extensions.IncomingFrames;
import org.eclipse.jetty.websocket.api.extensions.OutgoingFrames;

public interface LogicalConnection extends OutgoingFrames, SuspendToken
{
    /**
     * Perform a quick check that the connection input is open.
     * 
     * @throws IOException
     *             if the connection input is closed
     */
    void assertInputOpen() throws IOException;

    /**
     * Perform a quick check that the connection output is open.
     * 
     * @throws IOException
     *             if the connection output is closed
     */
    void assertOutputOpen() throws IOException;

    /**
     * Send a websocket Close frame, without a status code or reason.
     * <p>
     * Basic usage: results in an non-blocking async write, then connection close.
     * 
     * @see StatusCode
     * @see #close(int, String)
     */
    public void close();

    /**
     * Send a websocket Close frame, with status code.
     * <p>
     * Advanced usage: results in an non-blocking async write, then connection close.
     * 
     * @param statusCode
     *            the status code
     * @param reason
     *            the (optional) reason. (can be null for no reason)
     * @see StatusCode
     */
    public void close(int statusCode, String reason);

    /**
     * Terminate the connection (no close frame sent)
     */
    void disconnect();

    /**
     * Get the local {@link InetSocketAddress} in use for this connection.
     * <p>
     * Note: Non-physical connections, like during the Mux extensions, or during unit testing can result in a InetSocketAddress on port 0 and/or on localhost.
     * 
     * @return the local address.
     */
    InetSocketAddress getLocalAddress();

    /**
     * The policy that the connection is running under.
     * @return the policy for the connection
     */
    WebSocketPolicy getPolicy();

    /**
     * Get the remote Address in use for this connection.
     * <p>
     * Note: Non-physical connections, like during the Mux extensions, or during unit testing can result in a InetSocketAddress on port 0 and/or on localhost.
     * 
     * @return the remote address.
     */
    InetSocketAddress getRemoteAddress();

    /**
     * Get the Session for this connection
     * 
     * @return the Session for this connection
     */
    WebSocketSession getSession();

    /**
     * Get the WebSocket connection State.
     * 
     * @return the connection state.
     */
    ConnectionState getState();

    /**
     * Test if input is closed (as a result of receiving a close frame)
     * 
     * @return true if input is closed.
     */
    boolean isInputClosed();

    /**
     * Simple test to see if connection is open (and not closed)
     * 
     * @return true if connection still open
     */
    boolean isOpen();

    /**
     * Test if output is closed (as a result of sending a close frame)
     * 
     * @return true if output is closed.
     */
    boolean isOutputClosed();

    /**
     * Tests if the connection is actively reading.
     * 
     * @return true if connection is actively attempting to read.
     */
    boolean isReading();

    /**
     * A close handshake frame has been detected
     * 
     * @param incoming
     *            true if part of an incoming frame, false if part of an outgoing frame.
     * @param close
     *            the close details
     */
    void onCloseHandshake(boolean incoming, CloseInfo close);

    /**
     * Set where the connection should send the incoming frames to.
     * <p>
     * Often this is from the Parser to the start of the extension stack, and eventually on to the session.
     * 
     * @param incoming
     *            the incoming frames handler
     */
    void setNextIncomingFrames(IncomingFrames incoming);

    /**
     * Set the session associated with this connection
     * 
     * @param session
     *            the session
     */
    void setSession(WebSocketSession session);

    /**
     * Suspend a the incoming read events on the connection.
     * 
     * @return
     */
    SuspendToken suspend();
}