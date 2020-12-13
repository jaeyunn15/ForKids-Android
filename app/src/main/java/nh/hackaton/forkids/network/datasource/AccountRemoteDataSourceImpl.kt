package nh.hackaton.forkids.network.datasource

import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import nh.hackaton.forkids.model.response.*
import nh.hackaton.forkids.network.api.AccountApi

class AccountRemoteDataSourceImpl (private val api : AccountApi) : AccountRemoteDataSource{
    override fun getAccount(today: String, finacno: String, regno : String): Single<ResAccountValueDto> {
        return api.getAccount(today, finacno, regno)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
    }

    override fun getAccountList(
        today: String,
        startDate: String,
        endDate: String,
        regno: String
    ): Single<ResAccountListDto> {
        return api.getAccountList(today, startDate, endDate, regno)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }

    override fun getWithdraw(
        today: String,
        account: String,
        money: String,
        content: String,
        regno:String
    ): Single<ResWithdrawDto> {
        return api.getWithdraw(today,account,money,content,regno)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }

    override fun getDataRank(): Single<ResRankDto> {
        return api.getRank()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
    }

    override fun getDetailRank(): Single<ResDetailRankDto> {
        return api.getDetailRank()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
    }

}
