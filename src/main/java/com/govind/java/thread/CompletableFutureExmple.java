package com.govind.java.thread;

import com.govind.util.arraylist.ArrayListUtils;

import java.util.*;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.stream.Collector;
import java.util.stream.Collectors;

/*
*
 * Created by 609600403 on 06/06/2016.
*/


public class CompletableFutureExmple {
    final BlockingQueue<Integer> queue = new LinkedBlockingDeque<>();
    AtomicBoolean atomicBoolean = new AtomicBoolean(true);
    public static void main(String[] args) {
        new CompletableFutureExmple().execute(ArrayListUtils.getArrayList(new String[]{"abb","bac","cad"}));
    }

    private void execute(ArrayList<String> ar) {
        ThreadPoolExecutor executor = new ThreadPoolExecutor(3,5,0L, TimeUnit.MILLISECONDS,new LinkedBlockingDeque<>());
        List<CompletableFuture<Iterator<String>>> list = ar.stream().map(x -> CompletableFuture.supplyAsync(() -> consume(x),executor)).collect(Collectors.toList());
        System.out.println(list.stream().map(CompletableFuture::join).collect(Collectors.toList()));
    }


    private void print(Object ok, Throwable err) {
        System.out.println(ok+" "+err);
    }

    private void produce() {
        for (int i = 0; i < 100; i++) {
            queue.add(i);
        }
        atomicBoolean.set(false);
    }

    private String consume() {
        while (atomicBoolean.get() || !queue.isEmpty()){
            if (!queue.isEmpty()){
                System.out.println(queue.poll());
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
        return "val";
    }


    private Iterator<String> consume(String x) {
        for (int i = 0; i < 10; i++) {
            //System.out.println(i);

        }
        Set<String> set = new HashSet<>();
        set.add("Set");
        Iterator<String> itr = set.iterator();
        return itr;
    }
}
