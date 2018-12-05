package me.baron.rxjava.mapping;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Function;
import me.baron.rxjava.models.Community;
import me.baron.rxjava.models.House;
import me.baron.rxjava.utils.DataSimulator;

import javax.xml.crypto.Data;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * <p>GitHub:   <a href="https://github.com/BaronZ88"></p>
 * <p>知乎专栏:  <a href="https://zhuanlan.zhihu.com/baron"></p>
 * <p>个人博客:  <a href="http://www.jianshu.com/users/cfdc52ea3399/latest_articles"></p>
 *
 * @author baronzhang (baron[dot]zhanglei[at]gmail[dot]com)
 *         16/8/16
 *
 */

/**
 *
 * flatMap输出的结果是无序的。flatMap是一个把一个Observable变成几个Observable的操作符，在接口里可以返回多个Observable。
 */
public class OperatorFlatMapTest {

    public static void main(String[] args) {

        List<Community> communities = DataSimulator.getCommunities();
        Observable.fromIterable(communities)
                .flatMap(new Function<Community, Observable<House>>() {
                    @Override
                    public Observable<House> apply(Community community) throws Exception {

                        //interval可以根据时间间隔不断发射数据，take可以控制发射数量。
                        //对于输出position的问题，想明白flatMap是无序的就好理解多了。
                        return Observable.interval(500, TimeUnit.MILLISECONDS)
                                .map(new Function<Long, House>() {

                                    @Override
                                    public House apply(Long position) throws Exception {
                                        System.out.println("position:" + position);
                                        return community.getHouses().get(position.intValue());
                                    }
                                }).take(community.getHouses().size());
//                        return Observable.fromIterable(community.getHouses());
                    }
                }).subscribe(new Observer<House>() {
                    @Override
                    public void onError(Throwable e) {
                        System.out.println("Error:"+e.getMessage());
                    }

                    @Override
                    public void onComplete() {
                        System.exit(0);
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
