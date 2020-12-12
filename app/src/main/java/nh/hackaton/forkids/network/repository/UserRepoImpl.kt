package nh.hackaton.forkids.network.repository

import io.reactivex.Completable
import io.reactivex.Single
import nh.hackaton.forkids.model.response.ResUserCreateDto
import nh.hackaton.forkids.model.response.ResUserLoginSuccessDto
import nh.hackaton.forkids.network.datasource.UserRemoteDataSource

class UserRepoImpl (private val userDataSource : UserRemoteDataSource) : UserRepo{

    override fun getUserLogin(reg_nm: String, usr_pw: String): Single<ResUserLoginSuccessDto> {
        return userDataSource.getUserLogin(reg_nm, usr_pw)
    }

    override fun getUserCreate(reg_nm: String, usr_nm: String, usr_pw: String, phone_nm: String, usr_dsc: String) : Single<ResUserCreateDto>{
        return userDataSource.getUserCreate(reg_nm, usr_nm, usr_pw, phone_nm, usr_dsc)
    }

}