package nh.hackaton.forkids.model.response

class ResRecommendEduDto : ArrayList<ResRecommendEduDtoItem>()

data class ResRecommendEduDtoItem(
    val KEY: String,
    val TIME: String,
    val TITLE: String
)