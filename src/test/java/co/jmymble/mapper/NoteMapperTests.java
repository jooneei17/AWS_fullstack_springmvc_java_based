package co.jmymble.mapper;



import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import co.jmymble.domain.MemberVO;
import co.jmymble.domain.NoteVO;
import lombok.extern.log4j.Log4j;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
@Log4j
public class NoteMapperTests {
	@Autowired
	private NoteMapper noteMapper;
	@Autowired
	private MemberMapper memberMapper;
	
//	@Test
//	public void testInsert2(){
//		List<MemberVO> members = memberMapper.getList();
//		int i = 1;
//		for(MemberVO vo : members){
//			for(MemberVO vo2 : members){
//				NoteVO noteVO = new NoteVO();
//				noteVO.setSender(vo.getId());
//				noteVO.setReceiver(vo2.getId());
//				noteVO.setMessage("mapper 테스트 발송 :: " + i++);
//				noteMapper.insert(noteVO);
//			}
//		}
//	}
	
	@Test
	public void testInsert(){
		NoteVO noteVo = new NoteVO();
		noteVo.setSender("id1");
		noteVo.setReceiver("id2");
		noteVo.setMessage("mapper 테스트 발송");
		noteMapper.insert(noteVo);
	}
	
	@Test
	public void testSelectOne(){
		log.info(noteMapper.selectOne(2L));
	}
	
	@Test
	public void testUpdate(){
		noteMapper.update(2L);
	}
	
	@Test
	public void testDelete(){
		noteMapper.delete(1L);
	}
	
	@Test
	public void testSendList(){
		noteMapper.sendList("id1").forEach(log::info);
	}
	
	@Test
	public void testReceiverList(){
		noteMapper.receiveList("id1").forEach(log::info);
	}
	
	@Test
	public void testReceiveUncheckedList(){
		noteMapper.receiveUncheckedList("id1");
	}
}
