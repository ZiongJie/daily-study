/**
 * 分享一道阿里的面试题：假如提供一个闹钟服务，订阅这个服务的人特别多，10亿人，就意味着在每天早上七点钟
 * 的时候会有10亿的并发量涌向你的服务器，问你怎么优化？
 *
 * 思想是把这个定时的任务分发到很多很多的边缘服务器上去，一台服务器不够啊，在一台服务器上有一个队列存着
 * 这些任务，然后线程去消费，也是要用到线程池的，大的结构上用分而治之的思想，主服务器把这些同步到边缘服务器，
 * 在每台服务器上用线程池加任务队列。
 *
 * 到现在我们学习了四种线程池，我们来稍微回顾一下：
 * 1.SingleThreadPool只有一个线程的线程池；
 * 2.FixedThreadPool固定多少个线程池的线程池；
 * 3.CachedPool有弹性的线程池，来一个启动一个，只要没闲着就启动新的来执行；
 * 4.ScheduledPool定时任务来执行线程池；
 * 这几个线程池底层全都是用的ThreadPoolExecutor。
 *
 * 自定义一个拒绝策略的例子，代码演示如下：
 */
package com.study.juc.seventh;

import java.util.concurrent.*;

public class T17_interview_question_MyRejectedHandler {

    public static void main(String[] args) {
        ExecutorService service = new ThreadPoolExecutor(4, 4,
                0, TimeUnit.SECONDS, new ArrayBlockingQueue<>(6),
                Executors.defaultThreadFactory(),
                new MyHandler());
    }

    static class MyHandler implements RejectedExecutionHandler {

        @Override
        public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
            //log("r rejected")
            //save r kafka mysql redis
            //try 3 times
            if (executor.getQueue().size() < 10000) {
                //try put again();
            }
        }
    }
}
