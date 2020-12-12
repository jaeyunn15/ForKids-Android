package nh.hackaton.forkids.network.api

import io.reactivex.Single
import nh.hackaton.forkids.model.response.ResAccountListDto
import nh.hackaton.forkids.model.response.ResAccountValueDto
import nh.hackaton.forkids.model.response.ResUserLoginSuccessDto
import nh.hackaton.forkids.model.response.ResWithdrawDto
import retrofit2.http.GET
import retrofit2.http.Query

interface AccountApi {
    @GET("getaccount")
    fun getAccount(
            @Query("TODAY") today : String,
            @Query("FinAcno") finacno : String,
            @Query("regno") regno : String,
    ): Single<ResAccountValueDto>

    @GET("accountlist")
    fun getAccountList(
            @Query("TODAY") today : String,
            @Query("startDate") startDate : String,
            @Query("endDate") endDate : String,
            @Query("regno") regno : String
    ): Single<ResAccountListDto>

    @GET("withdraw")
    fun getWithdraw(
            @Query("TODAY") today : String,
            @Query("account") account : String,
            @Query("money") money : String,
            @Query("content") content : String,
            @Query("regno") regno : String
    ): Single<ResWithdrawDto>
}