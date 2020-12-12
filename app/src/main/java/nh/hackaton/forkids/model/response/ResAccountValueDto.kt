package nh.hackaton.forkids.model.response

data class ResAccountValueDto(
    val FinAcno: String,
    val Header: Header,
    val Ldbl: String,
    val RlpmAbamt: String
)

data class Header(
    val ApiNm: String,
    val ApiSvcCd: String,
    val FintechApsno: String,
    val IsTuno: String,
    val Iscd: String,
    val Rpcd: String,
    val Rsms: String,
    val Trtm: String,
    val Tsymd: String
)