package co.jmymble.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import co.jmymble.domain.ReplyVO;

public interface ReplyMapper {
	//등록하기
	int insert(ReplyVO vo);
	
	//단일 조회
	ReplyVO read(Long rno);
	
	//리스트 조회
	List<ReplyVO> getList(@Param("bno") Long bno,@Param("rno") Long rno);
	
	//수정하기
	int update(ReplyVO vo);
	
	//삭제하기
	int delete(Long rno);
	
	//bno를 통해 삭제하기
	int deleteByBno(Long bno);
	
}
