package co.jmymble.service;

import java.util.List;

import org.springframework.stereotype.Service;

import co.jmymble.domain.MemberVO;
import co.jmymble.mapper.BoardMapper;
import co.jmymble.mapper.MemberMapper;
import co.jmymble.mapper.ReplyMapper;
import lombok.AllArgsConstructor;
import lombok.ToString;
import lombok.extern.log4j.Log4j;

@Log4j
@Service
@ToString
@AllArgsConstructor
public class MemberServiceImpl implements MemberService{

	private MemberMapper memberMapper;
	
//	@Override
//	public List<MemberVO> getList() {
//		return memberMapper.getList();
//	}

	@Override
	public MemberVO get(String id) {
		return memberMapper.read(id);
	}

//	@Override
//	public MemberVO login(MemberVO vo) {
//		return memberMapper.login(vo);
//	}

}
