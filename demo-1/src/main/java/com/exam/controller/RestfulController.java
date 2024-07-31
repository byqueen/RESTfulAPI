package com.exam.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.guest.GuestService;
import com.example.guest.GuestVo;
import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.servlet.http.HttpServletRequest;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/restful")
public class RestfulController {
	RestfulController() {
		System.out.println("===> RestfulController()");
	}
	
	@Autowired
	private GuestService service;
	
	@GetMapping(value="/list", produces="application/json;charset=UTF-8") // 한글 처리
	public String guestbookList(HttpServletRequest request, Model model, GuestVo vo) {
		System.out.println("===> (server) list");
		
		String startStr = request.getParameter("start");
		String endStr = request.getParameter("end");
		String searchCondition = request.getParameter("searchCondition");
		String searchKeyword = request.getParameter("searchKeyword");
		
		System.out.println("===> (server) start & end : " + startStr  +" & "+ endStr);
		
		searchKeyword = vo.getSearchKeyword();
		System.out.println("===> (server) condition & keyword : " + searchCondition  +" & "+ searchKeyword);
		
		if (searchCondition != null || searchKeyword != null) {
			if (!searchKeyword.startsWith("%")) {
				vo.setSearchCondition(searchCondition);
				vo.setSearchKeyword('%'+searchKeyword+'%');
			} else {
				vo.setSearchCondition(searchCondition);
				vo.setSearchKeyword(searchKeyword);
			}
		}

		int totalRecord = service.totalRecord(vo);
		
		if (startStr == null || endStr == null) {
			vo.setStart(0);
			vo.setEnd(totalRecord);
		} else {
			int start = Integer.parseInt(request.getParameter("start"));
			int end = Integer.parseInt(request.getParameter("end"));
			vo.setStart(start);
			vo.setEnd(end);
		}
		
		model.addAttribute("li", service.guestbookList(vo));

		Map<String, Object> resultMap = new HashMap<>();
		resultMap.put("totalRecord", totalRecord);
		resultMap.put("li", service.guestbookList(vo));
		
		ObjectMapper objmapper = new ObjectMapper();
		try {
			return objmapper.writeValueAsString(resultMap);
		} catch(Exception e) {
			e.printStackTrace();
			return "";
		}
	}
}
