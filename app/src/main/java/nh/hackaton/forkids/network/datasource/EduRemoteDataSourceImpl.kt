package nh.hackaton.forkids.network.datasource

import io.reactivex.Completable
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import nh.hackaton.forkids.model.response.ResRecommendEduDto
import nh.hackaton.forkids.model.response.ResUserCreateDto
import nh.hackaton.forkids.model.response.ResUserLoginSuccessDto
import nh.hackaton.forkids.model.response.ResVideoEduListDto
import nh.hackaton.forkids.network.api.EduApi
import nh.hackaton.forkids.network.api.UserApi

class EduRemoteDataSourceImpl (private val eduApi: EduApi) : EduRemoteDataSource{
    override fun getAllVideo(): Single<ResVideoEduListDto> {
        return eduApi.getAllVideo()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
    }

    override fun getRecommendEdu(): Single<ResRecommendEduDto> {
        return eduApi.getRecommend()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
    }


}