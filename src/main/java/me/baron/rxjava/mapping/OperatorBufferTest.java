package me.baron.rxjava.mapping;

import io.reactivex.Observable;
import io.reactivex.functions.Consumer;

import java.util.List;

public class OperatorBufferTest {

    public static void main(String[] args) {
        Observable.just(1,2,3,4,5,6,7,8,9,10)
                .buffer(4)
                .subscribe(new Consumer<List<Integer>>() {
                    @Override
                    public void accept(List<Integer> integers) throws Exception {

                    }
                });
    }

}
