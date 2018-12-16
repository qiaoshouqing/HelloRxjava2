package me.baron.rxjava.create;


import io.reactivex.Observable;
import io.reactivex.functions.Consumer;

public class OperatorJustTest {

    public static void main(String[] args) {

        Observable.just(1,2,3,4,5,6,7,8,9,10)
                .subscribe(new Consumer<Integer>() {
                    @Override
                    public void accept(Integer integer) throws Exception {
                        System.out.println(integer + "");
                    }
                });
    }
}
