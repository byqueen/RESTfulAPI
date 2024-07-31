package com.example.board;

import java.util.Map;

public interface BoardService {
	Map<String, Object> BoardListAll(BoardVo vo);
	BoardVo editBoard(BoardVo vo);
	void insertBoard(BoardVo vo);
	void deleteBoard(BoardVo vo);
	void updateBoard(BoardVo vo);
}
