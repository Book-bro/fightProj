package kr.or.ddit.service;

import kr.or.ddit.vo.BookVO;

public interface BookService {
	
	// 메서드 시그니처
	// 삽입
	public int insert(BookVO bookVO);
	
	// 상세보기
	public BookVO detail(BookVO bookVO);

	//책 수정
	public int update(BookVO bookVO);

	//책 삭제
	public int delete(BookVO bookVO);
}
