package me.baron.rxjava.merging;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.BiFunction;
import io.reactivex.functions.Function;
import me.baron.rxjava.models.Location;
import me.baron.rxjava.utils.DataSimulator;

import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * <p>GitHub:   <a href="https://github.com/BaronZ88"></p>
 * <p>知乎专栏:  <a href="https://zhuanlan.zhihu.com/baron"></p>
 * <p>个人博客:  <a href="http://www.jianshu.com/users/cfdc52ea3399/latest_articles"></p>
 *
 * @author baronzhang (baron[dot]zhanglei[at]gmail[dot]com)
 *         16/8/16
 */
public class OperatorCombineLatestTest {

    public static void main(String[] args) {

        List<String> communityNames = DataSimulator.getCommunityNames();
        List<Location> locations = DataSimulator.getLocations();

        Observable<String> communityNameSequence = Observable.interval(1, TimeUnit.SECONDS)
                .map(new Function<Long, String>() {
                    @Override
                    public String apply(Long position) throws Exception {
                        return communityNames.get(position.intValue());
                    }
                }).take(communityNames.size());
        Observable<Location> locationSequence = Observable.interval(1, TimeUnit.SECONDS)
                .map(new Function<Long, Location>() {
                    @Override
                    public Location apply(Long position) throws Exception {
                        return locations.get(position.intValue());
                    }
                }).take(locations.size());

        Observable.combineLatest(
                communityNameSequence,
                locationSequence,
                new BiFunction<String, Location, String>() {
                    @Override
                    public String apply(String communityName, Location location) throws Exception {
                        return "小区名:" + communityName + ", 经纬度:" + location.toString();
                    }
                }).subscribe(new Observer<String>() {
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
                    public void onNext(String s) {
                        System.out.println(s);
                    }
                });

        try {
            Thread.sleep(Integer.MAX_VALUE);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
