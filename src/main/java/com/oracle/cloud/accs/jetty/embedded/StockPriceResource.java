package com.oracle.cloud.accs.jetty.embedded;

import java.util.logging.Level;
import java.util.logging.Logger;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.Response;

@Path("stocks")
public class StockPriceResource {

    @GET
    public Response getQuote(@QueryParam("ticker") final String ticker) {
        String tick = null;
        try {
            if(ticker == null){
                return Response.status(400).entity(String.format("Ticker name cannot be null")).build();
            }
            Logger.getLogger(StockPriceResource.class.getName()).log(Level.INFO, "Fetching price for {0}", ticker);

            Response response = ClientBuilder.newClient().
                    target("https://www.google.com/finance/info?q=NASDAQ:" + ticker).
                    request().get();

            if (response.getStatus() != 200) {
                return Response.status(response.getStatus()).entity(String.format("Could not find price for ticker %s", ticker)).build();
            }
            tick = response.readEntity(String.class);
            tick = tick.replace("// [", "");
            tick = tick.replace("]", "");
        } catch (Exception e) {
            return Response.serverError().entity(String.format("Could not find price for ticker %s due to %s", ticker, e.getMessage())).build();
        }

        return Response.ok(StockDataParser.parse(tick)).build();
    }

}
