package org.eclipse.jetty.websocket.io;

import java.io.IOException;
import java.net.InetSocketAddress;

import org.eclipse.jetty.util.Callback;
import org.eclipse.jetty.websocket.api.WebSocketConnection;
import org.eclipse.jetty.websocket.api.WebSocketPolicy;
import org.junit.rules.TestName;

public class LocalWebSocketConnection implements WebSocketConnection
{
    private final String id;

    public LocalWebSocketConnection()
    {
        this("anon");
    }

    public LocalWebSocketConnection(String id)
    {
        this.id = id;
    }

    public LocalWebSocketConnection(TestName testname)
    {
        this.id = testname.getMethodName();
    }

    @Override
    public void close()
    {
    }

    @Override
    public void close(int statusCode, String reason)
    {
    }

    @Override
    public WebSocketPolicy getPolicy()
    {
        return null;
    }

    @Override
    public InetSocketAddress getRemoteAddress()
    {
        return null;
    }

    @Override
    public String getSubProtocol()
    {
        return null;
    }

    @Override
    public boolean isOpen()
    {
        return false;
    }

    @Override
    public <C> void ping(C context, Callback<C> callback, byte[] payload) throws IOException
    {
        // TODO Auto-generated method stub

    }

    @Override
    public String toString()
    {
        return String.format("LocalWebSocketConnection[%s]",id);
    }

    @Override
    public <C> void write(C context, Callback<C> callback, byte[] buf, int offset, int len) throws IOException
    {
    }

    @Override
    public <C> void write(C context, Callback<C> callback, String message) throws IOException
    {
        // TODO Auto-generated method stub

    }
}