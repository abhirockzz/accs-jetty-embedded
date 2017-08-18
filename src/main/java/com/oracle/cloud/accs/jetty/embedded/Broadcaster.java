package com.oracle.cloud.accs.jetty.embedded;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class Broadcaster {

    private ScheduledExecutorService ses;
    
    public Broadcaster() {
        ses = Executors.newSingleThreadScheduledExecutor();
    }
    
    
    public void start(){
        ses.scheduleAtFixedRate(new BroadcastTask(), 10, 10, TimeUnit.SECONDS);
    }
    
    public void stop(){
        ses.shutdownNow();
    }
    
}
