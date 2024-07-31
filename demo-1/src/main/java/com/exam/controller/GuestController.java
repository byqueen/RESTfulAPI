package com.exam.controller;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.guest.GuestService;
import com.example.guest.GuestVo;
import com.fasterxml.jackson.databind.ObjectMapper;

@Controller
@RequestMapping("/guest/")
public class GuestController {
	GuestController() {
		System.out.println("===> GuestController() 생성자");
	}
	
	@Autowired
	private GuestService service;
	
	@GetMapping("insertGuestbookform")
	public void insertForm() {}
	
	@PostMapping("insertGuestbook")
	public String insertGuestbook(GuestVo vo) {
		System.out.println("===> insertGuestbook mapping");
		
		String[] lnameArr = {"김", "고", "한", "이", "차", "장", "최", "유", "새", "강"};
		String[] fnameArr = {"이준", "시우", "서아", "도윤", "이현", "우진", "시안", "정우", "로이", "한결"};
		int[] ageArr = {11, 17, 20, 24,  27, 32, 8, 22, 34};
		String[] memoArr = {"잘보고갑니다.","좋은하루되세요", "좋은정보감사합니다.", "잘부탁드립니다."};
		
		Random random = new Random();	
		for (int i = 0; i < 124; i++) {
			int fnameRd = (int)(Math.random()*9);
			int nnameRd = (int)(Math.random()*9);
			int ageRd = random.nextInt(9);
			int memoRd = random.nextInt(4);
			
			vo.setName(lnameArr[fnameRd]+ fnameArr[nnameRd]);
			vo.setAge(ageArr[ageRd]);
			vo.setMemo(memoArr[memoRd]);
			service.insertGuestbook(vo);
		}
		return "redirect:/guest/guestbookList";
	}
	
	@GetMapping("guestbookList")
	public String guestbookList(Model model, GuestVo vo) {
		System.out.println("===> list : "+vo.getStart()+", "+vo.getEnd());
		String searchKeyword = vo.getSearchKeyword();
		if (searchKeyword != null) {
			if (!searchKeyword.startsWith("%")) {
				vo.setSearchKeyword('%'+searchKeyword+'%');
			}
		}

		int start;
		if (vo.getStart() == 0) {
			start = 1;
		} else {
			start = vo.getStart();
		}
		int pageSize = 10;
		int end = start + pageSize - 1;
		int currentPage = start / pageSize + 1;
		int totalRecord = service.totalRecord(vo);
		int totalPage = (int)(Math.ceil((double)totalRecord / pageSize));
		int listSize = 10;
		int listStartPage = (currentPage-1) / listSize * listSize + 1;
		int listEndPage =  listStartPage + listSize -1;
		
		model.addAttribute("searchCondition", vo.getSearchCondition());
		model.addAttribute("searchKeyword", vo.getSearchKeyword());
		model.addAttribute("start", start);
		model.addAttribute("end", end);
		model.addAttribute("pageSize", pageSize);
		model.addAttribute("currentPage", currentPage);
		model.addAttribute("totalRecord", totalRecord);
		model.addAttribute("totalPage", totalPage);
		model.addAttribute("listSize", listSize);
		model.addAttribute("listStartpage", listStartPage);
		model.addAttribute("listEndpage", listEndPage);
		
		int prePage = start - (pageSize*listSize);
		int nextPage = start + (pageSize*listSize);
		int lastPage = (totalPage-1) * pageSize + 1;
		model.addAttribute("prePage", prePage);
		model.addAttribute("nextPage", nextPage);
		model.addAttribute("lastPage", lastPage);
		
		vo.setStart(start);
		vo.setEnd(end);
		model.addAttribute("li", service.guestbookList(vo));

		/*
		Map<String, Object> resultMap = new HashMap<>();
		resultMap.put("start", start);
		resultMap.put("end", end);
		resultMap.put("pageSize", pageSize);
		resultMap.put("currentPage", currentPage);
		resultMap.put("totalRecord", totalRecord);
		resultMap.put("totalPage", totalPage);
		resultMap.put("listSize", listSize);
		resultMap.put("listStartpage", listStartPage);
		resultMap.put("listEndpage", listEndPage);
		resultMap.put("li", service.guestbookList(vo));
		
		ObjectMapper objmapper = new ObjectMapper();
		try {
			return objmapper.writeValueAsString(resultMap);
		} catch(Exception e) {
			e.printStackTrace();
			return "";
		}
		*/
		return "guest/guestbookList";
	}
}
