package nh.hackaton.forkids.model.response

class ResVideoEduListDto : ArrayList<ResVideoEduItemDto>()

data class ResVideoEduItemDto(
    val KEY: String,
    val TIME: String,
    val TITLE: String
)