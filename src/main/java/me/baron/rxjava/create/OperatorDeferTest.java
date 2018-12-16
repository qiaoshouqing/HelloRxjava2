package me.baron.rxjava.create;


import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;

import java.util.concurrent.Callable;

public class OperatorDeferTest {

    public static void main(String[] args) {

        test();
    }


    public static void test() {
        Observable<Long> observable = Observable.defer(() -> {
            long time = System.currentTimeMillis();
            return Observable.just(time, time);
        });

        observable.subscribe(time -> System.out.println(time));

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        observable.subscribe(time -> System.out.println(time));



        ///create


        Observable<Long> observableCreate = Observable.create(emitter -> {
            emitter.onNext(System.currentTimeMillis());
        });

        observableCreate.subscribe(time -> System.out.println(time));

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        observableCreate.subscribe(time -> System.out.println(time));


    }
}
