package com.example.member;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface MemberDao {
	List<MemberVo> memberList(MemberVo vo);
	MemberVo EditMember(MemberVo vo);
	void insertMember(MemberVo vo);
	void deleteMember(MemberVo vo);
	void updateMember(MemberVo vo);
}
