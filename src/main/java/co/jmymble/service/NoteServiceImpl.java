package co.jmymble.service;

import java.util.List;

import org.springframework.stereotype.Service;

import co.jmymble.domain.NoteVO;
import co.jmymble.mapper.NoteMapper;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class NoteServiceImpl implements NoteService{
	private NoteMapper noteMapper;

	@Override
	public int send(NoteVO vo) {
		return noteMapper.insert(vo);
	}

	@Override
	public NoteVO get(Long noteno) {
		return noteMapper.selectOne(noteno);
	}

	@Override
	public int receive(Long noteno) {
		return noteMapper.update(noteno);
	}

	@Override
	public int remove(Long noteno) {
		return noteMapper.delete(noteno);
	}

	@Override
	public List<NoteVO> getSendList(String id) {
		return noteMapper.sendList(id);
	}

	@Override
	public List<NoteVO> getReceiveList(String id) {
		return noteMapper.receiveList(id);
	}

	@Override
	public List<NoteVO> getReceiveUncheckedList(String id) {
		return noteMapper.receiveUncheckedList(id);
	}
	
}
