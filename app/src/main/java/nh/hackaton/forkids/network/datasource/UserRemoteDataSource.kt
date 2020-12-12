package nh.hackaton.forkids.network.datasource

import io.reactivex.Completable
import io.reactivex.Single
import nh.hackaton.forkids.model.response.ResUserCreateDto
import nh.hackaton.forkids.model.response.ResUserLoginSuccessDto

interface UserRemoteDataSource {
    fun getUserLogin(reg_nm:String, usr_pw:String) : Single<ResUserLoginSuccessDto>
    fun getUserCreate(reg_nm:String, usr_nm:String, usr_pw:String, phone_nm:String, usr_dsc:String) : Single<ResUserCreateDto>

    //REG_NM=9006161013213&
    // USR_NM=테스트&
    // USR_PW=111122&
    // PHONE_NM=01012345678&
    // USR_DSC=0
}