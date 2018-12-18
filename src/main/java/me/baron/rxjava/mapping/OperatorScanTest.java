package me.baron.rxjava.mapping;

import io.reactivex.Observable;

/**
 * Created by qiaoshouqing on 2018/12/17.
 */
public class OperatorScanTest {

    public static void main(String[] args) {
        Observable.just(5, 3, 8, 1, 7)
                .scan((partialSum, x) -> {
                    System.out.println("partialSum:" + partialSum);
                    System.out.println("x:" + x);
                    return partialSum + x;})
                .subscribe(System.out::println);

        System.out.println("--------------------------");


        Observable.just("a", "b", "c", "d", "e")
                .scan((partialSum, x) -> {
                    System.out.println("partialSum:" + partialSum);
                    System.out.println("x:" + x);
                    return partialSum + x;})
                .subscribe(System.out::println);
    }

}
