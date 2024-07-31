package com.example.guest;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface GuestDao {
	List<GuestVo> guestbookList(GuestVo vo);
	void insertGuestbook(GuestVo vo);
	
	int totalRecord(GuestVo vo);
}
