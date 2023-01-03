package kr.or.ddit.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.or.ddit.dao.BookDao;
import kr.or.ddit.service.BookService;
import kr.or.ddit.vo.BookVO;

// 서비스 클래스 : 비즈니스 로직
// 스프링 MVC 구조에서 Controller와 DAO를 연결하는 역할
// 프링이가 자바빈으로 등록해줌

@Service
public class BookServiceImpl implements BookService{
	// 데이터 베이스 접근을 위해서 BookDao 인스턴스(객체)를 주입받자
	@Autowired // 의존성 주입
	BookDao bookDao;
	
	// 인터페이스 상속받기 때문에 Override 어노테이션 달아야함
	@Override
	public int insert(BookVO bookVO) {
		// BOOK테이블에 insert된 그 기본키 값(bookId:1)
		// 입력된 기본키 값
		return this.bookDao.insert(bookVO);
	}
	
	// 책 상세보기
	@Override
	public BookVO detail(BookVO bookVO) {
		return this.bookDao.detail(bookVO);

	}
}
