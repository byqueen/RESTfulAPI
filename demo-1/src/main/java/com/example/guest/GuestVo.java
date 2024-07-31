package com.example.guest;

import lombok.Data;

@Data
public class GuestVo {
	private int idx;
	private String name;
	private String memo;
	private int age;
	private String regdate;
	
	// 페이지 검색
	private String searchCondition;
	private String searchKeyword;
	
	// 페이지 나누기
	private int start;
	private int end;
	private int totalRecord;
}
