package com.feiniaojin.gracefulresponse.async;

import com.feiniaojin.gracefulresponse.data.Response;
import java.util.concurrent.CompletableFuture;
import java.util.function.Function;

/**
 * Async response wrapper for handling async operations
 * @author qinyujie
 */
public class AsyncResponseWrapper {
    
    /**
     * Wrap async operation with graceful response
     * @param asyncOperation async operation
     * @return CompletableFuture of Response
     */
    public static <T> CompletableFuture<Response> wrapAsync(CompletableFuture<T> asyncOperation, 
            Function<T, Response> responseMapper) {
        return asyncOperation
                .thenApply(responseMapper)
                .exceptionally(throwable -> {
                    // Handle exception and return error response
                    throw new RuntimeException(throwable);
                });
    }
    
}
