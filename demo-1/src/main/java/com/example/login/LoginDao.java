package com.example.login;

import org.apache.ibatis.annotations.Mapper;

import com.example.member.MemberVo;

@Mapper
public interface LoginDao {
	MemberVo getLogin(MemberVo vo);
}
