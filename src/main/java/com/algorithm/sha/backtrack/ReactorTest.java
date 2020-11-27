/**
 * Copyright (c) 2018-2020 ixiancheng.com All Rights Reserved.
 *//*

package com.algorithm.sha.backtrack;

import com.google.common.collect.Lists;
import com.google.common.util.concurrent.ThreadFactoryBuilder;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.*;

*/
/**
 * @author 沙志鸿
 * @version ReactorTest.java, v0.1 2020/9/15 沙志鸿 Exp $$
 *//*

@Slf4j
public class ReactorTest {
    @Test
    public void test() throws InterruptedException {
        SampleSubscriber<String> ss = new SampleSubscriber<String>();

        Flux<String> seq1 = Flux.just("foo", "bar", "foobar");

        List<String> iterable = Arrays.asList("foo", "bar", "foobar");
        Flux<String> seq2 = Flux.fromIterable(iterable);
        */
/*seq2.subscribe(i -> {
                    try {
                        alphabet1(i);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                },
                error -> System.err.println("Error " + error),
                () -> {
                    System.out.println("Done");
                });

        Flux<String> alphabet = Flux.just(-1, 30, 13, 9, 20)
                .handle((i, sink) -> {
                    String letter = alphabet(i);
                    if (letter != null)
                        sink.next(letter);
                });

        alphabet.subscribe(System.out::println);


        Flux<String> flux =
                Flux.interval(Duration.ofMillis(250))
                        .map(input -> {
                            if (input < 3) return "tick " + input;
                            throw new RuntimeException("boom");
                        })
                        .onErrorReturn("Uh oh");

        flux.subscribe(System.out::println);*//*

        Thread.sleep(2100);
    }

    public String alphabet(int letterNumber) {
        if (letterNumber < 1 || letterNumber > 26) {
            return null;
        }
        int letterIndexAscii = 'A' + letterNumber - 1;
        return "" + (char) letterIndexAscii;
    }

    public String alphabet1(String test) {
        try {
            if ("foo".equals(test)) {
                Thread.sleep(30);
            }
            if ("bar".equals(test)) {
                Thread.sleep(20);
            }
            if ("foobar".equals(test)) {
                Thread.sleep(10);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        log.info(test);
        return test;
    }

    @Test
    public void concurrentTest() {
        List<String> iterable = getList(10000);
        concurrentReactorTest(iterable);
//        concurrentThreadPoolTest(iterable);

    }

    public void concurrentReactorTest(List<String> iterable) {
        //第一个参数20 20个并发
        //后面表示N个请求,最长的一个请求可能要2000ms

        //开始测试了
        long start = System.currentTimeMillis();
        list(100, iterable)
                .forEachRemaining(show -> {
                });
        log.info("总时间 : {} ms", System.currentTimeMillis() - start);
    }


    public void concurrentThreadPoolTest(List<String> iterable) {
        ThreadFactory threadFactory = new ThreadFactoryBuilder().setNameFormat("poolName").build();
        ExecutorService assignStoreES = new ThreadPoolExecutor(100, 100, 15L, TimeUnit.SECONDS, new LinkedBlockingQueue<>(), threadFactory);
        //开始测试了
        long start = System.currentTimeMillis();
        iterable.forEach(test -> assignStoreES.submit(() ->
                alphabet1(test)
        ));
        try {
            assignStoreES.shutdown();
            while (!assignStoreES.awaitTermination(1, TimeUnit.SECONDS)) {
                log.info("未结束，继续等待");
            }
        } catch (InterruptedException e) {
            log.error("InterruptedException:{}", e.getMessage());
        }
        log.info("总时间 : {} ms", System.currentTimeMillis() - start);
    }

    private List<String> getList(int size) {
        List<String> iterable = Lists.newArrayListWithCapacity(5000);
        for (int i = 0; i < size; i++) {
            if (i % 3 == 0) {
                iterable.add("foo");
                continue;
            }
            if (i % 3 == 1) {
                iterable.add("bar");
                continue;
            }
            if (i % 3 == 2) {
                iterable.add("foobar");
                continue;
            }
            iterable.add("foobar");

        }
        return iterable;
    }

    */
/**
     * 并行执行
     *
     * @param concurrent 并行数量
     * @param test       模拟停顿时间
     * @return 随便返回了
     *//*

    public Iterator<String> list(int concurrent, List<String> test) {
        return Flux.fromIterable(test)
//                .log()
                .flatMap(item -> Mono.fromCallable(() -> alphabet1(item)).subscribeOn(Schedulers.elastic()), concurrent)
                .toIterable().iterator();
    }

    */
/**
     * 实际上是一个http请求
     *
     * @param sleep 请求耗时
     * @return
     *//*

    public String mockHttp(long sleep) {
        try {
            Thread.sleep(sleep);
            log.info("停顿{}ms真的执行了", sleep);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return String.format("停顿了%sms", sleep);
    }

}
*/
