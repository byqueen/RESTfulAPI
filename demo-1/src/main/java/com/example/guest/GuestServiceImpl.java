package com.example.guest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GuestServiceImpl implements GuestService {
	GuestServiceImpl() {
		System.out.println("===> GuestServiceImpl() 생성자");
	}
	
	@Autowired
	GuestDao dao;

	@Override
	public List<GuestVo> guestbookList(GuestVo vo) {
		return dao.guestbookList(vo);
	}

	@Override
	public void insertGuestbook(GuestVo vo) {
		dao.insertGuestbook(vo);
	}

	@Override
	public int totalRecord(GuestVo vo) {
		return dao.totalRecord(vo);
	}
}
