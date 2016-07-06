package zhangruofan.rxjavademo.operators

import android.text.TextUtils
import android.util.Log
import rx.Observable
import rx.functions.Func1
import java.util.concurrent.TimeUnit

/**
 * Created by zhangruofan on 16-7-5.
 */
object Retry {

    val TAG = Retry::class.java.simpleName

    /**
     * retry at most 10 times
     */
    fun retryWithCounts() {
        Observable.just("")
                .flatMap {
                    Log.i(TAG, "start retry")

                    if (TextUtils.isEmpty(it)) {
                        throw NullPointerException()
                    }
                    Observable.just("")
                }
                .retry(10)  //retry operator must follow the flatmap above otherwise it won't retry
                .subscribe({
                    Log.i(TAG, "start retry")
                }, {
                    it ->
                    Log.i(TAG, "error:$it")
                })
    }

    /**
     * delay 2 seconds before retry
     * it's an endless retry without a count limitation
     */
    fun retryWithDelay() {
        Observable.just("")
                .flatMap {
                    if (TextUtils.isEmpty(it)) {
                        throw NullPointerException()
                    }
                    Observable.just("")
                }
                .retryWhen({ errors ->
                    errors.flatMap({ error ->
                        if (error is NullPointerException) {
                            Log.i(TAG, "start retry")
                            Observable.timer(2, TimeUnit.SECONDS)
                        } else {
                            Observable.error<Exception>(error as Exception)
                        }
                    })
                })
                .subscribe({
                    Log.i(TAG, "success")
                }, {
                    it ->
                    Log.i(TAG, "error:$it")
                })
    }

    /**
     * delay 2 seconds before retry
     * it sets an maximum retry count
     *
     * Since range(1, 5) runs out of numbers on the fifth error, it calls onCompleted(), which causes the entire zip to complete. This prevents further retries.
     */
    fun retryWithDelayAndCount() {

        Observable.just("")
                .flatMap {
                    if (TextUtils.isEmpty(it)) {
                        throw NullPointerException()
                    }
                    Observable.just("")
                }
                .retryWhen({ errors -> //the errors is an Observable<Throwable>
                    //we zip the Observable<Throwable> with Observable.range(0, 5) which only emits 5 integer.
                    //Since range(1, 5) runs out of numbers on the fifth error, it calls onCompleted(), which causes the entire zip to complete. This prevents further retries.
                    errors.zipWith(Observable.range(1, 5), { error, retryCount -> retryCount })
                            .flatMap({ retryCount ->
                                Log.i(TAG, "start retry:$retryCount")
                                Observable.timer(2, TimeUnit.SECONDS)
                            })
                })
                .subscribe({
                    Log.i(TAG, "success")
                }, {
                    it ->
                    Log.i(TAG, "error:$it")
                }, {
                    Log.i(TAG, "complete")
                })
    }
}