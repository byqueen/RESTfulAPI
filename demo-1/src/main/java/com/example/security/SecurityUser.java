package com.example.security;

import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;

import com.example.member.MemberVo;

public class SecurityUser extends User {
	private static final long serialVersionUID = 1L;

	public SecurityUser(MemberVo vo) {
		super(vo.getUsername(), 
			vo.getPassword(),
			AuthorityUtils.createAuthorityList(vo.getRole().toString()));
			// "{noop}" : 암호화하지 않은 평문
			// "{noop}"+vo.getPassword(), 평문으로 받음
			// vo.getPassword(), 암호문으로 받음
  	}
}
