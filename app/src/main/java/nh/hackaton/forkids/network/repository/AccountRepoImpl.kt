package nh.hackaton.forkids.network.repository

import io.reactivex.Single
import nh.hackaton.forkids.model.response.*
import nh.hackaton.forkids.network.datasource.AccountRemoteDataSource

class AccountRepoImpl (private val remote: AccountRemoteDataSource) : AccountRepo{
    override fun getAccount(today: String, finacno: String, regno:String): Single<ResAccountValueDto> {
        return remote.getAccount(today, finacno, regno)
    }

    override fun getAccountList(
        today: String,
        startDate: String,
        endDate: String,
        regno: String
    ): Single<ResAccountListDto> {
        return remote.getAccountList(today, startDate, endDate, regno)
    }

    override fun getWithdraw(
        today: String,
        account: String,
        money: String,
        content: String,
        regno: String
    ): Single<ResWithdrawDto> {
        return remote.getWithdraw(today, account, money, content, regno)
    }

    override fun getDataRank(): Single<ResRankDto> {
        return remote.getDataRank()
    }

    override fun getDetailRank(): Single<ResDetailRankDto> {
        return remote.getDetailRank()
    }
}
