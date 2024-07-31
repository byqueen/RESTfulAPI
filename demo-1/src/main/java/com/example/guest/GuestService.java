package com.example.guest;

import java.util.List;

public interface GuestService {
	List<GuestVo> guestbookList(GuestVo vo);
	void insertGuestbook(GuestVo vo);
	
	int totalRecord(GuestVo vo);
}
