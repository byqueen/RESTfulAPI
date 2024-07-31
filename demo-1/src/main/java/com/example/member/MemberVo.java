package com.example.member;

import lombok.Data;

@Data
public class MemberVo {
	private String idx;
	private String id;
	private String username; // 스프링 시큐리티 ID name
	private String password; // 스프링 시큐리티 PW name // 암호문 암호
	private String pwd; // 평문 암호
	private String role;
	private String grade;
	private String name;
	private String fileStr;
	private String regdate;
	
}
