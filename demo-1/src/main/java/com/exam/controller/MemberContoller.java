package com.exam.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.member.MemberService;
import com.example.member.MemberVo;

@Controller
@RequestMapping("/member/")
public class MemberContoller {
	MemberContoller() {
		System.out.println("===> MemberContoller() 생성자");
	}
	
	@Autowired
	MemberService service;
	
	@Autowired
	PasswordEncoder pwdEncoder;
	
	@GetMapping("insertMemberForm")
	public String insertMemberForm() {
		return "member/insertMemberForm";
	}
	
	@GetMapping("memberList")
	public String memberList(Model model) {
		System.out.println("===> Controller memberList");
		List<MemberVo> li = service.memberList(null);
		model.addAttribute("countMember", li.size());
		model.addAttribute("li", li);
		return "member/memberList";
	}
	
	@GetMapping("editMember")
	public String editMember(Model model, MemberVo vo) {
		System.out.println("===> Controller editMember");
		model.addAttribute("m", service.EditMember(vo));
		return "member/editMember";
	}
	
	@PostMapping("insertMember")
	public String insertMember(MemberVo vo) {
		System.out.println("===> Controller insertMember");
		String EncodePwd = pwdEncoder.encode(vo.getPassword());
		System.out.println("===> encodePwd : "+EncodePwd);
		vo.setPassword(EncodePwd);
		service.insertMember(vo);
		return "redirect:/member/memberList";
	}
	
	@PostMapping("deleteMember")
	public String deleteMember(MemberVo vo) {
		System.out.println("===> Controller deleteMember");
		service.deleteMember(vo);
		return "redirect:/member/memberList";
	}
	
	@PostMapping("updateMember")
	public String updateMember(MemberVo vo) {
		System.out.println("===> Controller updateMember");
		String EncodePwd = pwdEncoder.encode(vo.getPassword());
		System.out.println("===> encodePwd : "+EncodePwd);
		vo.setPassword(EncodePwd); // 암호문 암호로 수정하기
		service.updateMember(vo);
		return "redirect:/member/memberList";
	}
	
}