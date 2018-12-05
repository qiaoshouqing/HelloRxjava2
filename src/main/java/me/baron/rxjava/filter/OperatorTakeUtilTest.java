package me.baron.rxjava.filter;


import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Predicate;

import java.util.concurrent.TimeUnit;

/**
 * <p>GitHub:   <a href="https://github.com/BaronZ88"></p>
 * <p>知乎专栏:  <a href="https://zhuanlan.zhihu.com/baron"></p>
 * <p>个人博客:  <a href="http://www.jianshu.com/users/cfdc52ea3399"></p>
 *
 * @author baronzhang (baron[dot]zhanglei[at]gmail[dot]com)
 *         16/9/22
 */
public class OperatorTakeUtilTest {

    public static void main(String[] args) {

        Observable.just(1, 2, 3, 4, 5, 6, 7)
                .takeUntil(new Predicate<Integer>() {
                    @Override
                    public boolean test(Integer integer) throws Exception {
                        return integer >= 5;
                    }
                }).subscribe(new Consumer<Integer>() {
                    @Override
                    public void accept(Integer integer) throws Exception {
                        System.out.println("Sample1==>>" + integer);
                    }
        });

        System.out.println("=================================");

        //http://reactivex.io/documentation/operators/takeuntil.html
        //官方解释：在第二个Observable发出项目或终止后，丢弃Observable发出的任何项目
        Observable<Long> observableA = Observable.interval(300, TimeUnit.MILLISECONDS);
        Observable<Long> observableB = Observable.interval(200, TimeUnit.MILLISECONDS);

        observableA.takeUntil(observableB)
                .subscribe(new Observer<Long>() {
                    @Override
                    public void onComplete() {
                        System.exit(0);
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onSubscribe(Disposable disposable) {

                    }

                    @Override
                    public void onNext(Long aLong) {
                        System.out.println("Sample2==>>" + aLong);
                    }
                });

        try {
            Thread.sleep(Integer.MAX_VALUE);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
