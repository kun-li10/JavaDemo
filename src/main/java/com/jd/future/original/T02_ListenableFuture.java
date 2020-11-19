package com.jd.future.original;

import com.google.common.util.concurrent.FutureCallback;
import com.google.common.util.concurrent.Futures;
import com.google.common.util.concurrent.ListenableFuture;
import com.google.common.util.concurrent.ListeningExecutorService;
import com.google.common.util.concurrent.MoreExecutors;

import java.util.concurrent.Callable;
import java.util.concurrent.Executors;

public class T02_ListenableFuture {
    public static void main(String[] args) {
        ListeningExecutorService service =
                MoreExecutors.listeningDecorator(Executors.newFixedThreadPool(2));
        ListenableFuture<Integer> future = service.submit(new Callable<Integer>() {
            @Override
            public Integer call() throws Exception {
                return 8;
            }
        });

        Futures.addCallback(future, new FutureCallback<Integer>() {
            @Override
            public void onSuccess(Integer integer) {
                System.out.println("SUCCESS:"+integer);
            }

            @Override
            public void onFailure(Throwable throwable) {
                throwable.printStackTrace();
            }
        }, service);

        service.shutdown();
    }
}

