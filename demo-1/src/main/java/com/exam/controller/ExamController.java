package com.exam.controller;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.exam.ExamService;
import com.example.exam.ExamVo;

@Controller
@RequestMapping("/exam/")
public class ExamController {
	ExamController() {
		System.out.println("===> ExamController() 생성자");
	}
	
	@Autowired
	ExamService service;
	
	@GetMapping("examList")
	public String examList(Model model, ExamVo vo) {
		model.addAttribute("li", service.examList(vo));
		model.addAttribute("subjects", Arrays.asList("kor", "eng", "math", "hist"));
		
		return "/exam/examList";
	}
}
