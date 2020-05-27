package com.bs.demo.utils;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * Description:
 */

public class RxCountDownUtils {
    private Disposable mDisposables=null;

    public interface RxCountdownListener{
        void onExecute(Long aLong);
    }
    public interface RxCountdownFinishedListener extends RxCountdownListener{
        void onFinished();
    }

    /**
     * 倒计时 间隔1秒
     * @param times 次数
     * @param callBack 延时后回调
     */
    private void countdown(int times, final RxCountdownListener callBack){
        mDisposables= Observable.interval(0,1000, TimeUnit.MILLISECONDS)
                .take(times)//执行一次
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(aLong -> {
                    Long curTimes=aLong+1;
                    callBack.onExecute(curTimes);
                });
    }
    /**
     * 完成倒计时 间隔1秒
     * @param times 次数
     * @param callBack 延时后回调
     */
    public void countdown(final int times, final RxCountdownFinishedListener callBack){
        countdown(times, aLong -> {
            if (aLong==times){
                callBack.onFinished();
            }else {
                callBack.onExecute(aLong);

            }
        });
    }
    public void cancel(){
        if (mDisposables!=null){
            mDisposables.dispose();
        }
    }

    public Disposable getDisposables() {

        return mDisposables;
    }
}
