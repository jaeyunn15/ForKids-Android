package nh.hackaton.forkids.model.response

class ResRankDto : ArrayList<ResRankItemDto>()

data class ResRankItemDto(
    val COMMENT: String,
    val COUNT: Int,
    val NICKNAME: String
)