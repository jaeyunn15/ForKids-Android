package nh.hackaton.forkids.ui.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import nh.hackaton.forkids.model.response.REC
import nh.hackaton.forkids.model.response.ResDetailRankDto
import nh.hackaton.forkids.model.response.ResRankDto
import nh.hackaton.forkids.model.response.ResRankItemDto
import nh.hackaton.forkids.network.repository.AccountRepo
import nh.hackaton.forkids.ui.base.BaseViewModel

class AccountViewModel(
        private val repo : AccountRepo
) : BaseViewModel(){

    private val _accountValue = MutableLiveData<String>()
    val accountValueLiveData : LiveData<String>
        get() = _accountValue

    private val _accountListLiveData = MutableLiveData<List<REC>>()
    val accountListLiveData : LiveData<List<REC>>
        get() = _accountListLiveData

    private val _nickName = MutableLiveData<String>()
    val nickNameLiveData : LiveData<String>
        get() = _nickName

    private val _rankData = MutableLiveData<ResRankDto>()
    val rankDataLiveData : LiveData<ResRankDto>
        get() = _rankData

    private val _detailRankData = MutableLiveData<ResDetailRankDto>()
    val detailRankDataLiveData : LiveData<ResDetailRankDto>
        get() = _detailRankData

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
                    _accountListLiveData.postValue(it.REC)
                    it.REC.forEach {rec->
                        Log.d("계좌 조회 잔액@ ", rec.Txtm)
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

    fun getDataRank(){
        addDisposable(
                repo.getDataRank()
                        .subscribe({
                            _nickName.postValue(it[0].NICKNAME)
                            _rankData.postValue(it)
                            it.forEach {data ->
                                Log.d("rank 성공 ", "${data.NICKNAME} ${data.COMMENT} ${data.COUNT}")
                            }
                        },{
                            Log.d("rank 실패 ", it.localizedMessage)
                        })
        )
    }

    fun getDetailRank(){
        addDisposable(
                repo.getDetailRank()
                        .subscribe({
                            _detailRankData.postValue(it)
                        },{
                            Log.d("detail rank 실패 ", it.localizedMessage)
                        })
        )
    }
}