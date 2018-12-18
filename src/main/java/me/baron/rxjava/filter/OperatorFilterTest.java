package me.baron.rxjava.filter;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by qiaoshouqing on 2018/12/11.
 */
public class OperatorFilterTest {

    public static void main(String[] args) {

        List<Integer> numList = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            numList.add(i);
        }

        Observable.fromIterable(numList)
                .take(50)
                .filter(integer -> integer % 2 == 0)
                .subscribe(new Observer<Integer>() {
                    @Override
                    public void onSubscribe(Disposable disposable) {

                    }

                    @Override
                    public void onNext(Integer integer) {
                        System.out.println("" + integer);
                    }

                    @Override
                    public void onError(Throwable throwable) {

                    }

                    @Override
                    public void onComplete() {
                        System.out.println("over");
                    }
                });
    }

}
