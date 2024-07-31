package com.example.login;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.member.MemberVo;

@Service
public class LoginServiceImpl implements LoginService {
	LoginServiceImpl() {
		System.out.println("===> LoginServiceImpl() 생성자");
	}
	
	@Autowired
	LoginDao dao;

	@Override
	public MemberVo getLogin(MemberVo vo) {
		return dao.getLogin(vo);
	}
	
}
