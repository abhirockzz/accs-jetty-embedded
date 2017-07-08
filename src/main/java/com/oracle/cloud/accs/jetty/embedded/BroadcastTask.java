package com.oracle.cloud.accs.jetty.embedded;

import static com.oracle.cloud.accs.jetty.embedded.RealTimeStockTicker.CLIENTS;
import java.util.Date;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.websocket.SendHandler;
import javax.websocket.SendResult;
import javax.websocket.Session;
import javax.ws.rs.client.ClientBuilder;

public class BroadcastTask implements Runnable {
    @Override
    public void run() {

        Logger.getLogger(BroadcastTask.class.getName()).log(Level.INFO, "Timer fired at {0}", new Date());
        /**
         * Invoked asynchronously
         */
        Future<String> tickFuture = ClientBuilder.newClient().
                target("https://www.google.com/finance/info?q=NASDAQ:ORCL").
                request().buildGet().submit(String.class);

        /**
         * Extracting result immediately with a timeout (3 seconds) limit. This
         * is a workaround since we cannot impose timeouts for synchronous
         * invocations
         */
        String tick = null;
        try {
            tick = tickFuture.get(3, TimeUnit.SECONDS);
        } catch (InterruptedException | ExecutionException | TimeoutException ex) {
            Logger.getLogger(BroadcastTask.class.getName()).log(Level.INFO, "GET operation timed out");
            return;
        }

        if (tick != null) {
            /**
             * cleaning the JSON payload
             */
            tick = tick.replace("// [", "");
            tick = tick.replace("]", "");

            Logger.getLogger(RealTimeStockTicker.class.getName()).log(Level.INFO, "Broadcasting price");
            for (final Session s : CLIENTS) {
                if (s != null && s.isOpen()) {
                    /**
                     * Asynchronous push
                     */
                    s.getAsyncRemote().sendText(StockDataParser.parse(tick), new SendHandler() {
                        @Override
                        public void onResult(SendResult result) {
                            if (result.isOK()) {
                                Logger.getLogger(RealTimeStockTicker.class.getName()).log(Level.INFO, "Price sent to client {0}", s.getId());
                            } else {
                                Logger.getLogger(RealTimeStockTicker.class.getName()).log(Level.SEVERE, "Could not send price update to client " + s.getId(),
                                        result.getException());
                            }
                        }
                    });
                }

            }
        }

    }

}
