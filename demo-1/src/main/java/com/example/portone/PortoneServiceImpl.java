package com.example.portone;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PortoneServiceImpl implements PortoneService {
	PortoneServiceImpl() {
		System.out.println("===> PortoneServiceImpl() 생성자");
	}
	
	@Autowired
	PortoneDao dao;
	
	// PortoneVo vo1 = new PortoneVo();

	@Override
	public String insertPay(PortoneVo vo) {
		try {
			System.out.println("===> 결제성공 vo : "+vo);
			dao.insertPay(vo);
			
			// 1. 전역변수
			// vo1.setMpaynum(vo.getMpaynum());
			
			// 2. url 에 매개변수 넘기기
			String mpaynum = vo.getMpaynum();
			return "/portone/success?mpaynum="+mpaynum;
		} catch(Exception e) {
			System.out.println("===> 결제실패");
			e.printStackTrace();
			return "/portone/failure";
		}
	}
	
	@Override
	public PortoneVo selectPayOne(PortoneVo vo) {
		System.out.println("===> selectPayOne()");
		return dao.selectPayOne(vo);
	}
}