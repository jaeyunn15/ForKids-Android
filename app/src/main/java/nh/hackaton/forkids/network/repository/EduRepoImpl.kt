package nh.hackaton.forkids.network.repository

import io.reactivex.Completable
import io.reactivex.Single
import nh.hackaton.forkids.model.response.ResRecommendEduDto
import nh.hackaton.forkids.model.response.ResUserCreateDto
import nh.hackaton.forkids.model.response.ResUserLoginSuccessDto
import nh.hackaton.forkids.model.response.ResVideoEduListDto
import nh.hackaton.forkids.network.datasource.EduRemoteDataSource
import nh.hackaton.forkids.network.datasource.UserRemoteDataSource

class EduRepoImpl (private val eduDataSource : EduRemoteDataSource) : EduRepo{

    override fun getAllVideo(): Single<ResVideoEduListDto> {
        return eduDataSource.getAllVideo()
    }

    override fun getRecommendEdu(): Single<ResRecommendEduDto> {
        return eduDataSource.getRecommendEdu()
    }

}