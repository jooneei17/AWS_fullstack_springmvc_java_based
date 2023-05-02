package co.jmymble.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import co.jmymble.domain.NoteVO;

public interface NoteMapper {
	//CRUD
	// 노트 작성
	@Insert("INSERT INTO tbl_note(noteno, sender, receiver, message) VALUES (seq_note.nextval, #{sender}, #{receiver}, #{message})")
	int insert(NoteVO vo);
	
	// 단일 조회
	@Select("SELECT * FROM TBL_NOTE WHERE NOTENO = #{noteno}")
	NoteVO selectOne(Long noteno);
	
	//수신 확인
	@Update("UPDATE TBL_NOTE SET RDATE = SYSDATE WHERE NOTENO = #{noteno}")
	int update(Long noteno);
	
	//노트 삭제
	@Delete("DELETE FROM TBL_NOTE WHERE NOTENO = #{noteno}")
	int delete(Long noteno);
	
	//보낸 거
	@Select("SELECT * FROM tbl_note WHERE noteno > 0 and sender = #{sender} ORDER BY 1 desc")
	List<NoteVO> sendList(String sender);
	
	//받은 거
	@Select("SELECT * FROM tbl_note WHERE noteno > 0 AND receiver = #{receiver} ORDER BY 1 DESC")
	List<NoteVO> receiveList(String receiver);
	
	//받았는데 확인 안 한거
	@Select("SELECT * FROM tbl_note WHERE noteno > 0 AND receiver = #{receiver} AND rdate IS NULL ORDER BY 1 DESC")
	List<NoteVO> receiveUncheckedList(String receiver);
	
	
	
	
}
