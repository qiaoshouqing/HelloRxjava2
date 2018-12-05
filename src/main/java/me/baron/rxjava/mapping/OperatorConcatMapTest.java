package me.baron.rxjava.mapping;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Function;
import me.baron.rxjava.models.Community;
import me.baron.rxjava.models.House;
import me.baron.rxjava.utils.DataSimulator;

import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * <p>GitHub:   <a href="https://github.com/BaronZ88"></p>
 * <p>知乎专栏:  <a href="https://zhuanlan.zhihu.com/baron"></p>
 * <p>个人博客:  <a href="http://www.jianshu.com/users/cfdc52ea3399"></p>
 *
 * @author baronzhang (baron[dot]zhanglei[at]gmail[dot]com)
 *         16/8/16
 */

/**
 * 和flatMap的唯一区别是concatMap是有序的。
 */
public class OperatorConcatMapTest {

    public static void main(String[] args) {

        List<Community> communities = DataSimulator.getCommunities();
        Observable.fromIterable(communities)
                .concatMap(new Function<Community, Observable<House>>() {
                    @Override
                    public Observable<House> apply(Community community) throws Exception {
                        return Observable.interval(500, TimeUnit.MILLISECONDS)
                                .map(new Function<Long, House>() {

                                    @Override
                                    public House apply(Long position) throws Exception {
                                        System.out.println("position:" + position);
                                        return community.getHouses().get(position.intValue());
                                    }
                                }).take(community.getHouses().size());
                    }
                }).subscribe(new Observer<House>() {
                    @Override
                    public void onComplete() {
                        System.exit(0);
                    }

                    @Override
                    public void onError(Throwable e) {
                        System.out.println("Error:"+e.getMessage());
                    }

            @Override
            public void onSubscribe(Disposable disposable) {

            }

            @Override
                    public void onNext(House house) {
                        System.out.println(house.getCommunityName() + " " + "均价:" + house.getPrice());
                    }
                });

        try {
            Thread.sleep(Integer.MAX_VALUE);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
