package me.baron.rxjava.merging;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Function;

import java.io.Serializable;
import java.util.concurrent.TimeUnit;

/**
 * <p>GitHub:   <a href="https://github.com/BaronZ88"></p>
 * <p>知乎专栏:  <a href="https://zhuanlan.zhihu.com/baron"></p>
 * <p>个人博客:  <a href="http://www.jianshu.com/users/cfdc52ea3399/latest_articles"></p>
 *
 * @author baronzhang (baron[dot]zhanglei[at]gmail[dot]com)
 *         16/8/16
 */
public class OperatorMergeTest {

    public static void main(String[] args) {

        String[] letters = new String[]{"A", "B", "C", "D", "E", "F", "G", "H"};
        Observable<String> letterSequence = Observable.interval(300, TimeUnit.MILLISECONDS)
                .map(new Function<Long, String>() {
                    @Override
                    public String apply(Long position) throws Exception {
                        return letters[position.intValue()];
                    }
                }).take(letters.length);

        Observable<Long> numberSequence = Observable.interval(500, TimeUnit.MILLISECONDS).take(5);

        Observable.merge(letterSequence, numberSequence)
                .subscribe(new Observer<Serializable>() {
                    @Override
                    public void onComplete() {
                        System.exit(0);
                    }

                    @Override
                    public void onError(Throwable e) {
                        System.out.println("Error:" + e.getMessage());
                    }

                    @Override
                    public void onSubscribe(Disposable disposable) {

                    }

                    @Override
                    public void onNext(Serializable serializable) {
                        System.out.print(serializable.toString() + " ");
                    }
                });

        try {
            Thread.sleep(Integer.MAX_VALUE);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
