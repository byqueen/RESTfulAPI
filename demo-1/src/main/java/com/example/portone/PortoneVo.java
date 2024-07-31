package com.example.portone;

import lombok.Data;

@Data
public class PortoneVo {
	private String gymnum; //헬스장번호 (테이블)
	private String gname; //헬스장명
	private String membernum; //회원번호 (테이블)
	private String mname; //회원명
	private String mphone; //회원 연락처
	private String mmail; //회원 이메일
	private String dataName;
	private int dataPrice;
	private String dataGoodsnum;
	
	// 이하 테이블 데이터
	private String idx;
	private String mpaynum; // 결제번호
	private String mpaymethod; // 결제수단
	private String mpayproduct; // 헬스장명 + 상품명
	private String mpayprice; // 결제금액
	private String mpaydate; // 결제일
	private String mpaygym; // 헬스장명
	private String mpayperiod; // 상품이용기간
	private String mpaytime; // null
	private String trainername; // 트레이너명
	private String ggoodsnum; // 상품번호
	private String tgoodsint; // null
	private int cnt;
}
