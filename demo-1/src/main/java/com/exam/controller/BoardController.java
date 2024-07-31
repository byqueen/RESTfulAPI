package com.exam.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.board.BoardService;
import com.example.board.BoardVo;

@Controller
@RequestMapping("/board/")
public class BoardController {
	BoardController() {
		System.out.println("===> BoardController() 생성자");
	}
	
	@Autowired
	BoardService service;
	
	@GetMapping("boardList")
	public String boardList(Model model, BoardVo vo) {
		vo.setSearchValue('%'+vo.getSearchValue()+'%');
		model.addAttribute("map", service.BoardListAll(vo));
		return "board/boardList";
	}
	
	@GetMapping("editBoard")
	public String editBoard(Model model, BoardVo vo) {
		System.out.println("===> editBoard vo : "+vo);
		model.addAttribute("m", service.editBoard(vo));
		return "board/editBoard";
	}
	
	@GetMapping("insertBoardForm")
	public String insertBoardForm() {
		return "board/insertBoardForm";
	}
	
	@PostMapping("insertBoard")
	public String insertBoard(BoardVo vo) {
		service.insertBoard(vo);
		return "redirect:/board/boardList";
	}
	
	@PostMapping("deleteBoard")
	public String deleteBoard(BoardVo vo) {
		service.deleteBoard(vo);
		return "redirect:/board/boardList";
	}

	@PostMapping("updateBoard")
	public String updateBoard(BoardVo vo) {
		service.updateBoard(vo);
		return "redirect:/board/boardList";
	}
}
