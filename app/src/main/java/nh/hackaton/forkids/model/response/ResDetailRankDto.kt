package nh.hackaton.forkids.model.response

class ResDetailRankDto : ArrayList<ResDetailRankItemDto>()

data class ResDetailRankItemDto(
    val COMMENT: String,
    val COUNT: Int,
    val EXPENSE: Double
)