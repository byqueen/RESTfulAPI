package com.example.member;

import java.util.List;

public interface MemberService {
	List<MemberVo> memberList(MemberVo vo);
	MemberVo EditMember(MemberVo vo);
	void insertMember(MemberVo vo);
	void deleteMember(MemberVo vo);
	void updateMember(MemberVo vo);
}
