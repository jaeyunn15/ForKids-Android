package nh.hackaton.forkids.ui.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import nh.hackaton.forkids.model.response.ResRecommendEduDto
import nh.hackaton.forkids.model.response.ResRecommendEduDtoItem
import nh.hackaton.forkids.model.response.ResVideoEduListDto
import nh.hackaton.forkids.network.repository.EduRepo
import nh.hackaton.forkids.ui.base.BaseViewModel

class EduViewModel(
        private val repo : EduRepo
) : BaseViewModel(){

    private val _eduItemList = MutableLiveData<ResVideoEduListDto>()
    val eduItemListLiveData : LiveData<ResVideoEduListDto>
        get() = _eduItemList

    private val _recommendEduItem = MutableLiveData<ResRecommendEduDto>()
    val recommendEduItemLiveData : LiveData<ResRecommendEduDto>
        get() = _recommendEduItem

    fun getAllVideo(){
        addDisposable(
                repo.getAllVideo()
                        .subscribe({
                            _eduItemList.postValue(it)
                            it.forEach {data ->
                                Log.d("비디오 겟 성공  ", data.TITLE)
                            }
                        },{
                            Log.d("비디오 겟 실패  ", it.localizedMessage)
                        })
        )
    }

    fun getRecommendEdu(){
        addDisposable(
                repo.getRecommendEdu()
                        .subscribe({
                            _recommendEduItem.postValue(it)
                            Log.d("비디오 겟 성공   ", it[0].KEY)
                        },{
                            Log.d("비디오 겟 실패  ", it.localizedMessage)
                        })
        )
    }


}