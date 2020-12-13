package nh.hackaton.forkids.ui.viewmodel

import android.annotation.SuppressLint
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import nh.hackaton.forkids.network.repository.UserRepo
import nh.hackaton.forkids.ui.base.BaseViewModel

class UserViewModel (
        private val repo : UserRepo
): BaseViewModel(){

    val _loginStatus = MutableLiveData<Boolean>()
    val loginStatusLiveData : LiveData<Boolean>
        get() = _loginStatus

    val _createStatus = MutableLiveData<Boolean>()
    val createStatusLiveData : LiveData<Boolean>
        get() = _createStatus

    val _createAccount = MutableLiveData<String>()
    val createAccountLiveData : LiveData<String>
        get() = _createAccount

    fun getUserLogin(reg_nm:String, usr_pw:String) : Boolean{
        var result = false
        addDisposable(
                repo.getUserLogin(reg_nm, usr_pw)
                        .subscribe({
                            it.forEach {data ->
                                Log.d("로그인 성공  ", data.REG_NM)
                                _loginStatus.postValue(true)
                                result = true
                            }
                        }, {
                            Log.d("로그인 실패  ", it.localizedMessage)
                            _loginStatus.postValue(false)
                            result = false
                        })
        )
        return result
    }

    fun getUserCreate(reg_nm: String, usr_nm:String, usr_pw: String, phone_nm:String, usr_dsc:String){
        addDisposable(
                repo.getUserCreate(reg_nm, usr_nm, usr_pw, phone_nm, usr_dsc)
                        .subscribe({
                            it.forEach {data ->
                                Log.d("회원가입 성공  ", data.USR_NM)
                                _createStatus.postValue(true)
                                _createAccount.postValue(data.REG_NM)
                            }
                        },{
                            Log.d("회원가입 실패  ", it.localizedMessage)
                            _createStatus.postValue(false)
                        })
        )
    }


}