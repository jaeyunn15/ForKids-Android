package nh.hackaton.forkids.util

import java.text.DecimalFormat

object AccountUtil{
    var USER_ACCOUNT = ""
}

fun ReplaceDate(date: String) : String {
    val month = date.substring(4,6)
    val day = date.substring(6)
    return "$month.$day"
}

fun ChangeState(type:String) : String{
    var result = ""
    when (type) {
        "2" ->{ result = "#입금" }
        "3" ->{ result = "#출금" }
    }
    return result
}

fun SetComma(money:String) : String{
    return DecimalFormat("###,###").format(Integer.parseInt(money)).toString()
}

fun AddPlusMinus(money:String, type: String) : String{
    var result = ""
    when (type) {
        "2" ->{ result = "+${money}원" }
        "3" ->{ result = "-${money}원" }
    }
    return result
}