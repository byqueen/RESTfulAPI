package com.exam.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.login.LoginService;
import com.example.member.MemberVo;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/login/")
public class LoginController {
	LoginController() {
		System.out.println("===> LoginController() 생성자");
	}
	
	@Autowired
	LoginService service;
	
	@GetMapping("loginform")
	public void loginform() {
		System.out.println("===> loginform mapping");
	}
	
	@PostMapping("login") // 스프링 시큐리티 X
	public String login(Model model, MemberVo vo) { // if 성공 or 실패
		System.out.println("===> login mapping : " +vo.getUsername()+", "+vo.getPwd());
		MemberVo login = service.getLogin(vo);
		if (login == null) {
			System.out.println("===> ID가 존재하지않습니다.");
			return "redirect:/login/fail";
		} else {
			System.out.println("===> ID가 존재합니다.");
			/*	id 와 password 를 같은 sql 문에서 처리할 경우, 
				sql injection 등의 공격으로 보안 이슈가 발생할 수 있음
				따라서 이중 if 문을 사용하여 id 와 password 를 따로 확인함 */
			if (login.getPwd().equals(vo.getPwd())) {
				System.out.println("===> 로그인 성공");
				return "redirect:/login/success";
			} else {
				System.out.println("===> 로그인 실패");
				return "redirect:/login/fail";
			}
		}
	}
	
	@GetMapping("success") // 로그인 성공
	public String loginSuccess() {
		System.out.println("===> login success");
		return "login/loginSuccess";
	}
		
	@GetMapping("fail") // 로그인 실패
	public String loginFail() {
		System.out.println("===> login fail");
		return "login/loginFail";
	}
	
	@GetMapping("accessDenied") // 권한 없음
	public String accessDenied() {
		System.out.println("===> accessDenied");
		return "login/accessDenied";
	}
	
	@GetMapping("logout")
	public String logout(HttpSession session) {
		System.out.println("===> logout");
		session.invalidate();
		return "login/logout";
	}
	
}
