package com.exam.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class StartContorller {
	StartContorller() {
		System.out.println("===> StartContorller() 생성자");
	}
	
	@GetMapping("/index")
	public void index() {
		System.out.println("===> index mapping");
	}
}
