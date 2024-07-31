package com.example.board;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BoardServiceImpl implements BoardService {
	BoardServiceImpl() {
		System.out.println("===> BoardServiceImpl() 생성자");
	}
	
	@Autowired
	private BoardDao dao;
	
	@Override
	public Map<String, Object> BoardListAll(BoardVo vo) {
		Map<String, Object> map = new HashMap<> ();
		map.put("li", dao.boardList(vo));
		map.put("listCount", dao.listCount(vo));
		return map;
	}

	@Override
	public BoardVo editBoard(BoardVo vo) { return dao.editBoard(vo); }
	
	@Override
	public void insertBoard(BoardVo vo) { dao.insertBoard(vo); }

	@Override
	public void deleteBoard(BoardVo vo) { dao.deleteBoard(vo); }

	@Override
	public void updateBoard(BoardVo vo) { dao.updateBoard(vo); }
}
