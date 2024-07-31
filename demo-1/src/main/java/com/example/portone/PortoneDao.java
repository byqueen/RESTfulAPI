package com.example.portone;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface PortoneDao {
	void insertPay(PortoneVo vo);
	PortoneVo selectPayOne(PortoneVo vo);
}
