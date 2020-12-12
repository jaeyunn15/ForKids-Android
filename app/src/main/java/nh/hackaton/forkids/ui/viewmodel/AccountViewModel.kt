package nh.hackaton.forkids.ui.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import nh.hackaton.forkids.network.repository.AccountRepo
import nh.hackaton.forkids.ui.base.BaseViewModel

class AccountViewModel(
        private val repo : AccountRepo
) : BaseViewModel(){

    private val _accountValue = MutableLiveData<String>()
    val accountValueLiveData : LiveData<String>
        get() = _accountValue

    fun getAccount(today:String, finacno :String, regno: String) {
        addDisposable(
                repo.getAccount(today, finacno, regno)
                        .subscribe({
                            Log.d("잔액 @ ", it.Ldbl)
                            _accountValue.postValue(it.Ldbl)
                        },{
                            Log.d("잔액 @ ", it.localizedMessage)
                        })
        )
    }

    fun getAccountList(today: String, startDate:String, endDate:String, regno:String){
        addDisposable(
            repo.getAccountList(today, startDate, endDate, regno)
                .subscribe({
                    it.REC.forEach {rec->
                        Log.d("계좌 조회 잔액@ ", rec.AftrBlnc)
                    }
                },{
                    Log.d("계좌 조회 잔액@ ", it.localizedMessage)
                })
        )
    }

    fun getWithdraw(today: String, account:String, money:String, content:String, regno: String){
        addDisposable(
            repo.getWithdraw(today, account, money, content, regno)
                .subscribe({
                    if (it.Header.Rsms.contentEquals("정상처리 되었습니다.")){
                        Log.d("게좌 출금 ", "완료@")
                    }
                },{
                    Log.d("게좌 출금 실패 ", it.localizedMessage)
                })
        )
    }
}