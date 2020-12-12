package nh.hackaton.forkids.ui.base

import androidx.lifecycle.ViewModel
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import nh.hackaton.forkids.util.AppPreference
import org.koin.core.KoinComponent
import org.koin.core.inject

open class BaseViewModel : ViewModel(), KoinComponent{

    private val compositeDisposable = CompositeDisposable()

    val pref : AppPreference by inject()

    fun addDisposable(disposable: Disposable){
        compositeDisposable.add(disposable)
    }

    override fun onCleared() {
        compositeDisposable.dispose()
        super.onCleared()
    }
}