//package me.baron.rxjava.merging;
//
//import rx.Observable;
//import rx.Observer;
//import rx.functions.Func1;
//import rx.functions.Func2;
//
//import java.util.concurrent.TimeUnit;
//
///**
// * <p>GitHub:   <a href="https://github.com/BaronZ88"></p>
// * <p>知乎专栏:  <a href="https://zhuanlan.zhihu.com/baron"></p>
// * <p>个人博客:  <a href="http://www.jianshu.com/users/cfdc52ea3399/latest_articles"></p>
// *
// * @author baronzhang (baron[dot]zhanglei[at]gmail[dot]com)
// *         16/8/16
// */
//public class OperatorZipTest {
//
//    public static void main(String[] args) {
//
//        String[] letters = new String[]{"A", "B", "C", "D", "E", "F", "G", "H"};
//        Observable<String> letterSequence = Observable.interval(120, TimeUnit.MILLISECONDS)
//                .map(new Func1<Long, String>() {
//                    @Override
//                    public String call(Long position) {
//                        return letters[position.intValue()];
//                    }
//                }).take(letters.length);
//
//        Observable<Long> numberSequence = Observable.interval(200, TimeUnit.MILLISECONDS).take(5);
//
//        Observable.zip(letterSequence, numberSequence, new Func2<String, Long, String>() {
//            @Override
//            public String call(String letter, Long number) {
//                return letter + number;
//            }
//        }).subscribe(new Observer<String>() {
//            @Override
//            public void onCompleted() {
//                System.exit(0);
//            }
//
//            @Override
//            public void onError(Throwable e) {
//                System.out.println("Error:" + e.getMessage());
//            }
//
//            @Override
//            public void onNext(String result) {
//                System.out.print(result + " ");
//            }
//        });
//
//        try {
//            Thread.sleep(Integer.MAX_VALUE);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//    }
//}
