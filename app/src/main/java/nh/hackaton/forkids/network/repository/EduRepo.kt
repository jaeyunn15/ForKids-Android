package nh.hackaton.forkids.network.repository

import io.reactivex.Completable
import io.reactivex.Single
import nh.hackaton.forkids.model.response.ResRecommendEduDto
import nh.hackaton.forkids.model.response.ResUserCreateDto
import nh.hackaton.forkids.model.response.ResUserLoginSuccessDto
import nh.hackaton.forkids.model.response.ResVideoEduListDto

interface EduRepo {
    fun getAllVideo() : Single<ResVideoEduListDto>
    fun getRecommendEdu() : Single<ResRecommendEduDto>

}