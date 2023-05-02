package co.jmymble.domain;

import lombok.Data;

@Data
public class PageDto {
	
	//하단에 출력될 페이지 사이즈
	private int pageCount = 10;
	
	//시작 페이지 숫자 
	private int startPage;
	
	//종료 페이지 숫자 
	private int endPage;
	
	//게시글 총 개수
	private int total;
	
	// prev, next
	//존재하면 보이고, 아니면 보이지 않도록 boolean 타입으로 설정
	private boolean prev; 
	private boolean next;
	
	//<<, >>
	private boolean doublePrev;
	private boolean doubleNext;
	
	
	//Criteria
	private Criteria cri;

	public PageDto(int total, Criteria cri) {
		this(10, total, cri);
	}

	public PageDto(int pageCount, int total, Criteria cri) {
		this.pageCount = pageCount;
		this.total = total;
		this.cri = cri;
		
//		cri.getAmount()
//		total
		
//		내가 현재 있는 페이지의 끝 페이지 = 현재 내 페이지 + (한 페이지에 볼 양 -1) / 한 페이지에 볼 양 * 한 페이지에 볼 양 
		endPage = (cri.getPageNum() + (pageCount - 1)) / pageCount * pageCount;
		startPage = endPage - (pageCount - 1);
		int realEnd = (total + (cri.getAmount() - 1)) / cri.getAmount();
		if(endPage > realEnd) {
			endPage = realEnd;
		}
		
		prev = cri.getPageNum() > 1; //내 페이지가 1 이 아닐 때 존재
		next = cri.getPageNum() < realEnd; //내 페이지가 진짜 마지막이 아닐 때 존재
		//(마지막에서는 보이지 않고 10페이지에서는 > 나와야 하므로)
		
		doublePrev = startPage > 1; //내 페이지의 시작 페이지가 1페이지가 아닐때 존재
		doubleNext = endPage < realEnd; //내 페이지의 마지막 페이지가 최종 마지막 페이지보다 작을 때
				
	}
	

	
}
