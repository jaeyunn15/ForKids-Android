package nh.hackaton.forkids.network.datasource

import io.reactivex.Single
import nh.hackaton.forkids.model.response.*
import retrofit2.http.Query

interface AccountRemoteDataSource {
    fun getAccount(today:String, finacno :String, regno:String) : Single<ResAccountValueDto>
    fun getAccountList(today: String, startDate:String, endDate:String, regno:String) : Single<ResAccountListDto>
    fun getWithdraw(today: String,account:String,money:String,content:String, regno:String) : Single<ResWithdrawDto>
    fun getDataRank() : Single<ResRankDto>
    fun getDetailRank() : Single<ResDetailRankDto>
}