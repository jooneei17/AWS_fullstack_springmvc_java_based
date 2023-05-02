package co.jmymble.service;

import java.util.List;


import co.jmymble.domain.MemberVO;

public interface MemberService {
//	List<MemberVO> getList();
	
	MemberVO get(String id);
	
//	MemberVO login(MemberVO vo);
}
