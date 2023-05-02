package co.jmymble.service;

import java.util.List;

import co.jmymble.domain.AttachFileDTO;
import co.jmymble.domain.BoardVO;
import co.jmymble.domain.Criteria;

public interface BoardService {
	void register(BoardVO vo);
	
	BoardVO get(Long bno);
	
	boolean modify(BoardVO vo);
	
	boolean remove(Long bno);
	
	List<BoardVO> getList(Criteria cri);
	
	int getTotalCnt(Criteria cri);
	
	String deleteFile(AttachFileDTO dto);
}
