package nh.hackaton.forkids.network.datasource

import io.reactivex.Completable
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import nh.hackaton.forkids.model.response.ResUserCreateDto
import nh.hackaton.forkids.model.response.ResUserLoginSuccessDto
import nh.hackaton.forkids.network.api.UserApi

class UserRemoteDataSourceImpl (private val userApi: UserApi) : UserRemoteDataSource{
    override fun getUserLogin(reg_nm: String, usr_pw: String) : Single<ResUserLoginSuccessDto> {
        return userApi.getUserLogin(reg_nm, usr_pw)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
    }

    override fun getUserCreate(reg_nm: String, usr_nm: String, usr_pw: String, phone_nm: String, usr_dsc: String)  : Single<ResUserCreateDto>{
        return userApi.getUserCreate(reg_nm, usr_nm, usr_pw, phone_nm, usr_dsc)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
    }

}