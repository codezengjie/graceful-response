package com.feiniaojin.gracefulresponse.cache;

import com.feiniaojin.gracefulresponse.data.ResponseStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.ConcurrentHashMap;

/**
 * Cache for frequently used ResponseStatus objects to improve performance
 * 
 * @author qinyujie
 */
public class ResponseStatusCache {
    private static final Logger logger = LoggerFactory.getLogger(ResponseStatusCache.class);
    
    private static final ConcurrentHashMap<String, ResponseStatus> STATUS_CACHE = new ConcurrentHashMap<>();
    
    /**
     * Get ResponseStatus from cache, create if not exists
     */
    public static ResponseStatus getOrCreate(String code, String msg) {
        String cacheKey = code + ":" + msg;
        return STATUS_CACHE.computeIfAbsent(cacheKey, k -> {
            logger.debug("Creating new ResponseStatus for code: {}, msg: {}", code, msg);
            return new DefaultResponseStatus(code, msg);
        });
    }
    
    /**
     * Clear the cache
     */
    public static void clear() {
        STATUS_CACHE.clear();
    }
    
    /**
     * Get cache size
     */
    public static int size() {
        return STATUS_CACHE.size();
    }
}
