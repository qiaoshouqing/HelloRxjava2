package me.baron.rxjava.create;

import io.reactivex.Observable;

import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class OperatorFutureTest {

    public static void main(String[] args) {
        ScheduledExecutorService executor = Executors.newSingleThreadScheduledExecutor();

        Future<String> future = executor.schedule(() -> "Hello world!", 1, TimeUnit.SECONDS);

        Observable<String> observable = Observable.fromFuture(future);

        observable.subscribe(
                item -> System.out.println(item),
                error -> error.printStackTrace(),
                () -> System.out.println("Done"));

        executor.shutdown();
    }
}
