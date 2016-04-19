package com.lmiceli.flickrapp;

import com.lmiceli.flickrapp.model.FlickrResponse;
import com.lmiceli.flickrapp.service.FlickrServiceFactory;

import rx.Observable;
import rx.Subscriber;
import rx.Subscription;
import rx.schedulers.Schedulers;

/**
 * Created by lmiceli on 01/04/2016.
 */
public class Test {

    interface MyInterface {
        public void interfaceMethod(String input);
    }

    public static void staticmethod(String input) {
        System.out.println(" static mymethod  " + input);
    }

    public void method1(String input) {
        System.out.println(" mymethod  " + input);
    }

    public static void mainLambda(String[] args) {
        Test obj = new Test();
        MyInterface dd = obj::method1;
        dd.interfaceMethod(" test string ");
//            MyInterface second = Test::staticmethod;
        MyInterface second = System.out::println;
        second.interfaceMethod(" test static string ");

    }

    /**
     * rx java
     *
     * @param args
     */
    public static void mainObservable1(String[] args) {
        Observable
                .just(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)
                .filter(integer -> integer % 2 == 0)
                .subscribe(System.out::println);

    }


    private Observable<String> lengthyObs() {

        return Observable.create(new Observable.OnSubscribe<String>() {
            @Override
            public void call(Subscriber<? super String> observer) {
                try {
                    Thread.sleep(1000);
                    observer.onNext("lenghty task finished");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                    observer.onNext("error on lenghty task");
                }

            }
        });
    }

    private String lengthyTask () {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return "lenghty task finished";
    }

    public static void mainOBS1(String[] args) {
        System.out.println("before call");

        Test test = new Test();
        SyncWebReader syncWebReader = new SyncWebReader();

        Subscription sub =
////                Observable.just(test.lengthyTask())
////                test.lengthyObs()
////                Observable.just(1, 2, 3, 4, 5)
//                createWebReader("https://api.flickr.com/services/feeds/photos_public.gne?format=json&nojsoncallback=1")
////                Observable.just(syncWebReader.execute("https://api.flickr.com/services/feeds/photos_public.gne?format=json&nojsoncallback=1"))
////                .filter(integer -> integer % 2 == 0)
//                .subscribeOn(Schedulers.io())
////                .subscribeOn(Schedulers.newThread())
//                .observeOn(Schedulers.immediate())
//                .subscribe(System.out::println);
//
//


                FlickrServiceFactory.getWebService()
                .getPhotosData("truck")
                .map(FlickrResponse::getItems)

                .subscribeOn(Schedulers.io())
                .observeOn(Schedulers.immediate())
                .subscribe(System.out::println);
        System.out.println("after call");
        // Important or we will never see the result
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        sub.unsubscribe();
        System.out.println("end of code");

//                .observeOn(AndroidSchedulers.mainThread())


    }
    public static void main(String[] args) {
        System.out.println("before call");

        // FIXME do this more rx way
        /*Subscription sub =
                FlickrServiceFactory.getWebService()
                        .getPhotosData("truck")
                        .map(FlickrResponse::getItems)
                        .flatMap(Observable::from) // Observable<List<Item>> to Observable<Item>
                        .map(Mapper::map) // Item to Photo
                        .toList() // For now we need a List<Photos>
                        .subscribeOn(Schedulers.io())
                        .observeOn(Schedulers.immediate())
                        .subscribe(System.out::println);*/

        System.out.println("after call");
        // Important or we will never see the result
        try {
            Thread.sleep(200000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
//        sub.unsubscribe();
        System.out.println("end of code");

//                .observeOn(AndroidSchedulers.mainThread())


    }
}
