package com.example.board;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface BoardDao {
	List<BoardVo> boardList(BoardVo vo);
	BoardVo editBoard(BoardVo vo);
	int listCount(BoardVo vo); // 등록된 게시글 수
	void insertBoard(BoardVo vo);
	void deleteBoard(BoardVo vo);
	void updateBoard(BoardVo vo);
}
