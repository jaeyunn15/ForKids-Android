package nh.hackaton.forkids.network.api

import io.reactivex.Completable
import io.reactivex.Single
import nh.hackaton.forkids.model.response.ResUserCreateDto
import nh.hackaton.forkids.model.response.ResUserLoginSuccessDto
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface UserApi {

    @GET("login")
    fun getUserLogin(
            @Query("REG_NM") reg_nm : String,
            @Query("USR_PW") usr_pw : String
    ): Single<ResUserLoginSuccessDto>

    @GET("create")
    fun getUserCreate(
            @Query("REG_NM")reg_nm : String,
            @Query("USR_NM")usr_nm : String,
            @Query("USR_PW")usr_pw : String,
            @Query("PHONE_NM")phone_nm : String,
            @Query("USR_DSC")usr_dsc : String
    ) : Single<ResUserCreateDto>

    //REG_NM=9006161013213&
    // USR_NM=테스트&
    // USR_PW=111122&
    // PHONE_NM=01012345678&
    // USR_DSC=0
}