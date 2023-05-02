package co.jmymble.service;

import java.io.File;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import co.jmymble.controller.UploadController;
import co.jmymble.domain.AttachFileDTO;
import co.jmymble.domain.AttachVO;
import co.jmymble.domain.BoardVO;
import co.jmymble.domain.Criteria;
import co.jmymble.mapper.AttachMapper;
import co.jmymble.mapper.BoardMapper;
import co.jmymble.mapper.ReplyMapper;
import lombok.AllArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.extern.log4j.Log4j;

@Log4j
@Service
@ToString
@AllArgsConstructor
public class BoardServiceImpl implements BoardService {

//	@Setter
//	@Autowired
	private final BoardMapper boardMapper;
	private final AttachMapper attachMapper;
	private ReplyMapper replyMapper;

	@Override
	@Transactional
	public void register(BoardVO vo) {
		boardMapper.insertSelectKey(vo);
		Long bno = vo.getBno();
		List<AttachVO> list = vo.getAttachs(); //무조건 not null ->  BoardVO에서 처리
		int idx = 0;
		for(AttachVO attach : list){ //첨부파일 db에 인서트
			attach.setBno(bno);
			attach.setOdr(idx++);
			attachMapper.insert(attach);
		}
		
	}

	@Override
	public BoardVO get(Long bno) {
		return boardMapper.read(bno);
	}

	@Override
	@Transactional
	public boolean modify(BoardVO vo) {
		attachMapper.deleteAll(vo.getBno()); //db에서 전부 삭제
		
		List<AttachVO> list = vo.getAttachs(); 
		int idx = 0;
		for(AttachVO attach : list){ 
			attach.setBno(vo.getBno());
			attach.setOdr(idx++);
			attachMapper.insert(attach); //해당하는 값 다시 추가
		}
		
		return boardMapper.update(vo) > 0;
	}

	@Override
	@Transactional
	public boolean remove(Long bno) {
		replyMapper.deleteByBno(bno);
		attachMapper.deleteAll(bno);
		return boardMapper.delete(bno) > 0;
	}

	@Override
	public List<BoardVO> getList(Criteria cri) {
		return boardMapper.getListWithPaging(cri);
	}

	@Override
	public int getTotalCnt(Criteria cri) {
		// TODO Auto-generated method stub
		return boardMapper.getTotalCnt(cri);
	}

	@Override
	public String deleteFile(AttachFileDTO dto) {
		log.info(dto);
		
		String s = UploadController.getPATH() + "/" + dto.getPath() + "/" +  dto.getUuid() + "_" + dto.getName();
		
		File file = new File(s);
		file.delete();
		if(dto.isImage()){
			s = UploadController.getPATH() + "/" + dto.getPath() + "/s_" +  dto.getUuid() + "_" + dto.getName();
			file = new File(s);
			file.delete();
		}
		return dto.getUuid();
	}
	
	
}
