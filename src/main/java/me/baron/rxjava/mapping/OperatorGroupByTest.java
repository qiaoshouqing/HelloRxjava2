package me.baron.rxjava.mapping;


import io.reactivex.Observable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.observables.GroupedObservable;

import java.util.concurrent.TimeUnit;

public class OperatorGroupByTest {

    public static void main(String[] args) {
        Observable.interval(1, TimeUnit.SECONDS).take(10).groupBy(new Function<Long, Long>() {
            @Override
            public Long apply(Long aLong) throws Exception {
                return aLong % 3;
            }
        }).subscribe(new Consumer<GroupedObservable<Long, Long>>() {
            @Override
            public void accept(GroupedObservable<Long, Long> longLongGroupedObservable) throws Exception {



                longLongGroupedObservable.subscribe(new Consumer<Long>() {
                    @Override
                    public void accept(Long aLong) throws Exception {
                        System.out.println("key:" + longLongGroupedObservable.getKey() +", value:" + aLong);
                    }
                });
            }
        });

        //等一下，不然进程结束了线程还没开始发射呢。
        try {
            Thread.sleep(Integer.MAX_VALUE);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
