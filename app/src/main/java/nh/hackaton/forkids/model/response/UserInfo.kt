package nh.hackaton.forkids.model.response

data class UserInfo(
        val name : String,
        val reg_no : String,
        val phone_no : String,
        val type : Int,
        val pw : String
)