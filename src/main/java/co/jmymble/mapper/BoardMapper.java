package co.jmymble.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import co.jmymble.domain.BoardVO;
import co.jmymble.domain.Criteria;

public interface BoardMapper {
	//목록 조회
	List<BoardVO> getList();
	List<BoardVO> getListWithPaging(Criteria criteria);

	//글 등록
	void insert(BoardVO vo);
	
	//글 등록
	void insertSelectKey(BoardVO vo);
	
	//조회
	BoardVO read(Long bno);
	
	//삭제
	int delete(Long bno);
	
	//수정
	int update(BoardVO vo);
	
	//검색어 개수
	int getTotalCnt(Criteria criteria);
	
	//댓글 개수 반영
	void updateReplyCnt(@Param("bno") Long bno, @Param("amount") int amount); //파라미터가 여러 개 사용할 경우 @Param 등록하기
}
