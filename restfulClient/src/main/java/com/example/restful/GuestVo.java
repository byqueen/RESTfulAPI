package com.example.restful;

import lombok.Data;

@Data
public class GuestVo {
	private String idx;
	private String name;
	private String memo;
	private String age;
	private String regdate;
	
	// 페이지 검색
	private String searchCondition;
	private String searchKeyword;
	
	// 페이지 나누기
	private int start;
	private int end;
	//private int totalRecord;
	//private int currentPage;
	//private int totalPage;
	//private int listStartPage;
	//private int listEndPage;
	
}
