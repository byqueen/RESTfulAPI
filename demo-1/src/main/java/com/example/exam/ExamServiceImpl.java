package com.example.exam;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ExamServiceImpl implements ExamService {
	ExamServiceImpl() {
		System.out.println("===> ExamServiceImpl() 생성자");
	}
	
	@Autowired
	ExamDao dao;

	@Override
	public List<ExamVo> examList(ExamVo vo) {
		return dao.examList(vo);
	}
}
