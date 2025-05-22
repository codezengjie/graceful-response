package com.feiniaojin.gracefulresponse.metrics;

import io.micrometer.core.instrument.MeterRegistry;
import io.micrometer.core.instrument.Timer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Metrics collector for monitoring response performance
 */
@Component
public class ResponseMetrics {
    
    private final MeterRegistry registry;
    
    private final Timer responseTimer;
    private final AtomicInteger activeRequests;
    private final AtomicInteger errorCount;
    
    @Autowired
    public ResponseMetrics(MeterRegistry registry) {
        this.registry = registry;
        
        this.responseTimer = Timer.builder("graceful.response.duration")
                .description("Response processing duration")
                .register(registry);
                
        this.activeRequests = registry.gauge("graceful.response.active", 
            new AtomicInteger(0));
            
        this.errorCount = registry.gauge("graceful.response.errors",
            new AtomicInteger(0));
    }
    
    public void recordResponseTime(long duration) {
        responseTimer.record(duration, TimeUnit.MILLISECONDS);
    }
    
    public void incrementActiveRequests() {
        activeRequests.incrementAndGet();
    }
    
    public void decrementActiveRequests() {
        activeRequests.decrementAndGet();
    }
    
    public void incrementErrors() {
        errorCount.incrementAndGet();
    }
}
