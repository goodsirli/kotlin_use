package com.example.kotlinfirstdemo.rx

import io.reactivex.BackpressureStrategy
import io.reactivex.Flowable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.functions.Consumer
import io.reactivex.schedulers.Schedulers
import io.reactivex.subjects.PublishSubject
import io.reactivex.subjects.Subject
import java.util.*

/**
 * Rxjava2 版本中的
 */
class RxBus private constructor() {

    private var mSubscriptionMap: HashMap<String, CompositeDisposable?>? =  null
    private val mSubject: Subject<Any> = PublishSubject.create<Any>().toSerialized()

    companion object {
        @Volatile
        private var mRxBus: RxBus? = null
        fun getInstance():RxBus?{
            if (mRxBus == null) {
                synchronized(RxBus::class.java) {
                    if (mRxBus == null) {
                        mRxBus = RxBus()
                    }
                }
            }
            return mRxBus
        }

    }

    fun post(o: Any) {
        mSubject.onNext(o)
    }

    /**
     * 返回指定类型的带背压的Flowable实例
     *
     * @param <T>
     * @param type
     * @return
    </T> */
    fun <T> getObservable(type: Class<T>?): Flowable<T> {
        return mSubject.toFlowable(BackpressureStrategy.BUFFER)
            .ofType(type)
    }

    /**
     * 一个默认的订阅方法
     *
     * @param <T>
     * @param type
     * @param next
     * @param error
     * @return
    </T> */
    fun <T> doSubscribe(type: Class<T>?, next: Consumer<T>?, error: Consumer<Throwable>
    ): Disposable {
        return getObservable(type)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(next, error)
    }

    /**
     * 是否已有观察者订阅
     *
     * @return
     */
    fun hasObservers(): Boolean {
        return mSubject.hasObservers()
    }

    /**
     * 保存订阅后的disposable
     * @param o
     * @param disposable
     */
    fun addSubscription( o: Any, disposable: Disposable? ) {
        if (mSubscriptionMap == null) {
            mSubscriptionMap =  HashMap()
        }
        val key = o.javaClass.name
        if (mSubscriptionMap!![key] != null) {
            mSubscriptionMap!![key]!!.add(disposable!!)
        } else {
            //一次性容器,可以持有多个并提供 添加和移除。
            val disposables = CompositeDisposable()
            disposables.add(disposable!!)
            mSubscriptionMap!![key] = disposables
        }
    }

    /**
     * 取消订阅
     * @param o
     */
    fun unSubscribe(o: Any) {
        if (mSubscriptionMap == null) {
            return
        }
        val key = o.javaClass.name
        if (!mSubscriptionMap!!.containsKey(key)) {
            return
        }
        if (mSubscriptionMap!![key] != null) {
            mSubscriptionMap!![key]!!.dispose()
        }
        mSubscriptionMap!!.remove(key)
    }

}