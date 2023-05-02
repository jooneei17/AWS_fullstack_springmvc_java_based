package co.jmymble.controller;

import java.util.List;
import java.util.Optional;

import org.apache.ibatis.annotations.Delete;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import co.jmymble.domain.ReplyVO;
import co.jmymble.service.ReplyService;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j;

@RequestMapping("replies")
@RestController
@Log4j
@AllArgsConstructor
public class ReplyController {
	private ReplyService replyService;
	
	// replies/list/{bno}
	// replies/list/{bno}/{rno}
	
	@GetMapping({"list/{bno}", "list/{bno}/{rno}"})
	public List<ReplyVO> getList(@PathVariable Long bno, @PathVariable(required = false) Optional<Long> rno){
//		log.info(bno);
//		log.info(rno.isPresent()? rno.get() : 0L);
//		log.info(rno.orElse(0L));
//		return replyService.getList(bno, rno.orElseGet(() -> 0L));
		return replyService.getList(bno, rno.orElse(0L));
	}
	
	@PostMapping("new")
	@PreAuthorize("isAuthenticated()")
	public Long create(@RequestBody ReplyVO vo) { //bno reply replyer 
		log.info(vo);
		replyService.register(vo);
		return vo.getRno();
	}
	
	//조회
	@GetMapping("{rno}")
	public ReplyVO get(@PathVariable Long rno) {
		log.info(rno);
		return replyService.get(rno);
	}
	
	//삭제
	@DeleteMapping("{rno}")
	@PreAuthorize("isAuthenticated() and principal.username eq #vo.replyer")
	public int remove(@PathVariable Long rno, @RequestBody ReplyVO vo) {
		log.info(rno);
		return replyService.remove(rno);
	}
	
	//수정 
	@PutMapping("{rno}")
	@PreAuthorize("isAuthenticated() and principal.username eq #vo.replyer")
	public int modify(@PathVariable Long rno, @RequestBody ReplyVO vo) {
		log.info(rno); //경로를 알아오기 위한 rno
		log.info(vo); // 실제 수정 되는 댓글 = vo
		return replyService.modify(vo);
	}
}
