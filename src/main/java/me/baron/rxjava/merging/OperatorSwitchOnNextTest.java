package me.baron.rxjava.merging;

import io.reactivex.Observable;
import io.reactivex.functions.Consumer;

import java.util.concurrent.TimeUnit;

/**
 * Created by qiaoshouqing on 2018/12/18.
 */
public class OperatorSwitchOnNextTest {

    public static void main(String[] args) {
        Observable<Observable<String>> timeIntervals =
                Observable.interval(1, TimeUnit.SECONDS)
                        .map(ticks -> Observable.interval(100, TimeUnit.MILLISECONDS)
                                .map(innerInterval -> "outer: " + ticks + " - inner: " + innerInterval));

//        Observable.switchOnNext(timeIntervals)
        timeIntervals
                .subscribe(item -> {
                    item.subscribe(new Consumer<String>() {
                        @Override
                        public void accept(String s) throws Exception {
                            System.out.println(item);
                        }
                    });
                });

        try {
            Thread.sleep(Integer.MAX_VALUE);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
