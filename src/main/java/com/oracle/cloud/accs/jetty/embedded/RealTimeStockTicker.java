package com.oracle.cloud.accs.jetty.embedded;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.websocket.OnClose;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

@ServerEndpoint("/rt/stocks")
public class RealTimeStockTicker {

    //stores Session (s) a.k.a connected clients
    static final List<Session> CLIENTS = new ArrayList<>();

    @OnOpen
    public synchronized void open(Session s) {
        CLIENTS.add(s);
        Logger.getLogger(RealTimeStockTicker.class.getName()).log(Level.INFO, "Client connected -- {0}", s.getId());
    }

    @OnClose
    public synchronized void close(Session s) {
        CLIENTS.remove(s);
        Logger.getLogger(RealTimeStockTicker.class.getName()).log(Level.INFO, "Client discconnected -- {0}", s.getId());
    }

}
