package nh.hackaton.forkids.network.api

import io.reactivex.Single
import nh.hackaton.forkids.model.response.ResAccountValueDto
import nh.hackaton.forkids.model.response.ResRecommendEduDto
import nh.hackaton.forkids.model.response.ResVideoEduListDto
import retrofit2.http.GET
import retrofit2.http.Query

interface EduApi {
    @GET("totalsearch")
    fun getAllVideo(): Single<ResVideoEduListDto>

    @GET("customizeddata")
    fun getRecommend(): Single<ResRecommendEduDto>
}