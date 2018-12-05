package me.baron.rxjava.mapping;


import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;

/**
 * <p>GitHub:   <a href="https://github.com/BaronZ88"></p>
 * <p>知乎专栏:  <a href="https://zhuanlan.zhihu.com/baron"></p>
 * <p>个人博客:  <a href="http://www.jianshu.com/users/cfdc52ea3399/latest_articles"></p>
 *
 * @author baronzhang (baron[dot]zhanglei[at]gmail[dot]com)
 *         16/8/16
 */
public class OperatorMapTest {


    public static void main(String[] args) {


        //测试1
        Observable.just(1, 2, 3, 4, 5)
                .map(new Function<Integer, String>() {

                    @Override
                    public String apply(Integer integer) throws Exception {
                        return "This is " + integer;
                    }
                }).subscribe(new Consumer<String>() {
                    @Override
                    public void accept(String s) throws Exception {
                        System.out.println(s);
                    }
        });


        //测试2
        Observable<Integer> observableA = Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(ObservableEmitter<Integer> observableEmitter) throws Exception {
                observableEmitter.onNext(1);
                observableEmitter.onComplete();
            }
        });

        Observer<String> mObserver = new Observer<String>() {

            @Override
            public void onError(Throwable e) {
                System.out.println(e.getMessage());
            }

            @Override
            public void onComplete() {
                System.out.println("onCompleted!");
            }

            @Override
            public void onSubscribe(Disposable disposable) {

            }

            @Override
            public void onNext(String s) {
                System.out.println(s);
            }
        };

        Observable<String> observableB =
                observableA.map(new Function<Integer, String>() {
                    @Override
                    public String apply(Integer integer) throws Exception {
                        return "This is " + integer;
                    }
                });

        observableB.subscribe(mObserver);


        //测试3
        Observable.create(new ObservableOnSubscribe<Integer>() {

            @Override
            public void subscribe(ObservableEmitter<Integer> observableEmitter) throws Exception {
                observableEmitter.onNext(1);
                observableEmitter.onComplete();
            }
        }).map(new Function<Integer, String>() {

            @Override
            public String apply(Integer integer) throws Exception {
                return "This is " + integer;
            }
        }).subscribe(new Observer<String>() {
            @Override
            public void onComplete() {
                System.out.println("onCompleted!");
            }

            @Override
            public void onError(Throwable e) {
                System.out.println(e.getMessage());
            }

            @Override
            public void onSubscribe(Disposable disposable) {

            }

            @Override
            public void onNext(String s) {
                System.out.println(s);
            }
        });
    }
}
