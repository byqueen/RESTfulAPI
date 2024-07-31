package com.example.board;


import java.util.Date;

import lombok.Data;

@Data
public class BoardVo {
	private String idx;
	private String name;
	private int age;
	private String etc;
	private String regdate;
	private Date regdate1;
	
	private String searchCondition;
	private String searchValue;
	
	private int count;
}
