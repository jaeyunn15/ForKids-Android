package nh.hackaton.forkids.model.response

data class ResAccountListDto(
    val CtntDataYn: String,
    val Header: Header,
    val Iqtcnt: String,
    val REC: List<REC>,
    val TotCnt: String //전체 처리 건수
)

data class REC(
    val AftrBlnc: String, //잔액
    val BnprCntn: String, //통장에 찍히는 내용
    val Ccyn: String, //취소여부
    val HnbrCd: String,
    val HnisCd: String,
    val MnrcDrotDsnc: String, //입출금구분 (1:신규, 2:입금, 3:출금, 4:해지)
    val Smr: Any,
    val Tram: String, //거래금액
    val Trdd: String, //거래일자
    val TrnsAfAcntBlncSmblCd: String, //거래후 계좌 잔액 부호코드 (+여유 있음, -여유없음)
    val Tuno: String,
    val Txtm: String //거래시각
)


