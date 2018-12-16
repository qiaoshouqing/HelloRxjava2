package me.baron.rxjava.create;


import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

public class OperatorNeverTest {

    public static void main(String[] args) {
        Observable.never().subscribe(new Observer<Object>() {
            @Override
            public void onSubscribe(Disposable disposable) {
                System.out.println("onSubscribe");
            }

            @Override
            public void onNext(Object o) {
                System.out.println("onNext");

            }

            @Override
            public void onError(Throwable throwable) {
                System.out.println("onError");

            }

            @Override
            public void onComplete() {
                System.out.println("onComplete");

            }
        });
    }
}
