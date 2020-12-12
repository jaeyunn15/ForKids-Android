package nh.hackaton.forkids.network.repository

import io.reactivex.Completable
import io.reactivex.Single
import nh.hackaton.forkids.model.response.ResUserCreateDto
import nh.hackaton.forkids.model.response.ResUserLoginSuccessDto

interface UserRepo {
    fun getUserLogin(reg_nm:String, usr_pw:String) : Single<ResUserLoginSuccessDto>
    fun getUserCreate(reg_nm: String, usr_nm:String, usr_pw: String, phone_nm:String, usr_dsc:String) : Single<ResUserCreateDto>
}