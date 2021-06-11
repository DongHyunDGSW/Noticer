package kr.hs.dgsw.donghyeon.noticer.base

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import io.reactivex.Single
import io.reactivex.SingleObserver
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers

open class BaseAndroidViewModel(application: Application) : AndroidViewModel(application) {
    val ref : DatabaseReference = FirebaseDatabase.getInstance().reference
    private val compositeDisposable = CompositeDisposable()

    fun addDisposable(disposable: Disposable) {
        compositeDisposable.add(disposable)
    }

    fun addDisposable(single: Single<*>, observer: DisposableSingleObserver<*>) {
        compositeDisposable.add(single.subscribeOn(Schedulers.io()).
        observeOn(AndroidSchedulers.mainThread()).subscribeWith(observer as SingleObserver<Any>) as Disposable
        )
    }

    override fun onCleared() {
        compositeDisposable.clear()
        super.onCleared()
    }
}