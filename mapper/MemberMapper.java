package co.jmymble.mapper;

import co.jmymble.domain.MemberVO;

public interface MemberMapper {
	MemberVO read(String userid);
}
