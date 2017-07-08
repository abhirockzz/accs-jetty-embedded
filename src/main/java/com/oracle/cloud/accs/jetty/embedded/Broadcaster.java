/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.oracle.cloud.accs.jetty.embedded;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 *
 * @author agupgupt
 */
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
