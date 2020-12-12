package nh.hackaton.forkids.model.response


class ResUserLoginSuccessDto : ArrayList<ResUserLoginSuccessItemDto>()

data class ResUserLoginSuccessItemDto(
    val PHONE_NM: String,
    val REG_NM: String,
    val USR_DSC: String,
    val USR_NM: String,
    val USR_PW: String
)