package nh.hackaton.forkids.network.repository

import io.reactivex.Single
import nh.hackaton.forkids.model.response.*
import nh.hackaton.forkids.network.datasource.AccountRemoteDataSource

interface AccountRepo {
    fun getAccount(today:String, finacno:String, regno : String) : Single<ResAccountValueDto>
    fun getAccountList(today: String, startDate:String, endDate:String, regno:String) : Single<ResAccountListDto>
    fun getWithdraw(today: String,account:String,money:String,content:String, regno:String) : Single<ResWithdrawDto>
    fun getDataRank() : Single<ResRankDto>
    fun getDetailRank() : Single<ResDetailRankDto>
}