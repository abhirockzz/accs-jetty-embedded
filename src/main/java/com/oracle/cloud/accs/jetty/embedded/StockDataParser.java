package com.oracle.cloud.accs.jetty.embedded;

import java.io.StringReader;
import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonReader;

public class StockDataParser {
    
    public static String parse(String data){
        
        JsonReader reader = Json.createReader(new StringReader(data));
                JsonObject priceJsonObj = reader.readObject();
                String name = priceJsonObj.getJsonString("t").getString();
                String price = priceJsonObj.getJsonString("l_cur").getString();
                String time = priceJsonObj.getJsonString("lt_dts").getString();
        

        return (String.format("Price for %s on %s = %s USD", name, time, price));
    }
}
