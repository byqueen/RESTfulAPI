package com.example.member;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MemberServiceImpl implements MemberService {
	MemberServiceImpl() {
		System.out.println("===> MemberServiceImpl() 생성자");
	}
	
	@Autowired
	private MemberDao dao;

	@Override
	public List<MemberVo> memberList(MemberVo vo) {
		System.out.println("===> ServiceImpl memberList");
		return dao.memberList(vo);
	}

	@Override
	public MemberVo EditMember(MemberVo vo) {
		return dao.EditMember(vo);
	}

	@Override
	public void insertMember(MemberVo vo) {
		dao.insertMember(vo);
	}

	@Override
	public void deleteMember(MemberVo vo) {
		dao.deleteMember(vo);
	}

	@Override
	public void updateMember(MemberVo vo) {
		dao.updateMember(vo);
	}

}
