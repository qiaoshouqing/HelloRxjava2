package me.baron.rxjava.create;


import io.reactivex.Observable;
import io.reactivex.functions.Consumer;

import java.util.concurrent.TimeUnit;

public class OperatorIntervalTest {

    public static void main(String[] args) {

        Observable.interval(10, 500, TimeUnit.MILLISECONDS)
                .subscribe(new Consumer<Long>() {
                    @Override
                    public void accept(Long aLong) throws Exception {
                        System.out.println(aLong + "");
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
