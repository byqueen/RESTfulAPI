package com.exam.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.portone.PortoneService;
import com.example.portone.PortoneVo;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/portone/")
public class PortoneController {
	PortoneController() {
		System.out.println("===> PortoneController() 생성자");
	}
	
	@Autowired
	PortoneService service;
	
	PortoneVo vo1 = new PortoneVo();
	
	@GetMapping("start")
	public String start(Model model, PortoneVo vo) {
		System.out.println("===> 결제시작");
		Map<String, Object> m = new HashMap<>();
		m.put("gymnum", "G1010");
		m.put("gname", "달려라헬스장");
		m.put("membernum", "M0015");
		m.put("mname", "모아이");
		m.put("mphone", "010-9689-0222");
		m.put("mmail", "lookinghhh@gmail.com");
		m.put("dataName", "헬스장3개월권");
		m.put("dataPrice", 100);
		m.put("dataGoodsnum", "A100");
		m.put("today", "2024-07-24");
		model.addAttribute("m", m);
		return "/portone/start";
	}
	
	@PostMapping("insertMPay")
	@ResponseBody
	public String insertMpay(@RequestBody PortoneVo vo, Model model, HttpSession session) {
		System.out.println("===> insertMPay insertvo : "+vo);
		vo1.setMpaynum(vo.getMpaynum());
		System.out.println("===> insertMPay 전역변수 vo : "+vo);
		String OK = service.insertPay(vo);
		return OK; // res 에 OK string 값을 넘김
	}
	
	@GetMapping("success")
	public String success(Model model, PortoneVo vo) {
		// 1. controller
		String mpaynum = vo1.getMpaynum();
		model.addAttribute("m1", mpaynum);
		
		// 2. 전역변수 vo
		// vo.setMpaynum(mpaynum);
		// System.out.println("success vo : "+vo);
		// model.addAttribute("m2", service.selectPayOne(vo));
		
		// 3. service 에서 url 에 파라미터값으로 vo 를 넘김
		model.addAttribute("m2", service.selectPayOne(vo));
		return "portone/result.html";
	}
	
	@GetMapping("failure")
	public String failure(Model model) {
		System.out.println("===> failure");
		model.addAttribute("failure", "결제실패!");
		return "portone/failure.html";
	}
}
