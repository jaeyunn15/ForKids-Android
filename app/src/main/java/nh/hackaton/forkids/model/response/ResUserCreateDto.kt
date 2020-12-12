package nh.hackaton.forkids.model.response

class ResUserCreateDto : ArrayList<ResUserCreateItemDto>()

data class ResUserCreateItemDto(
    val PHONE_NM: String,
    val REG_NM: String,
    val ACC_NM: String,
    val USR_DSC: String,
    val USR_NM: String,
    val USR_PW: String
)