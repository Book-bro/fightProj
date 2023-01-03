package kr.or.ddit.dao;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import kr.or.ddit.vo.BookVO;
import lombok.extern.slf4j.Slf4j;

/*
 * 매퍼xml(book_SQL.xml)을 실행시키는 DAO(Data Access Object) 클래스
 * 
 * Repository 어노테이션 : '데이터에 접근하는 클래스다'라고 스프링에게 알려주면 스프링은 데이터를 관리하는
 * 						 클래스라고 인지하여 자바빈으로 등록하여 관리해줌
 */
@Slf4j
@Repository
public class BookDao {

	// DI(Dependency Injection) : 의존성 주입 -> 다른 객체를 주입
	// IoC(Inversion Of Control) : 제어의 역전
	// SqlSessionTemplate 타입 객체를 BookDao 객체에 주입함 -> new로 만들지 않음(Spring이 해줌)
	@Autowired
	SqlSessionTemplate sqlSessionTemplate;

	// <insert id="insert" parameterType="bookVO">
	// insert, update, delete는 반영된 건 수가 return됨
	// insert 성공하면 1이상, 실패하면 0 반환
	public int insert(BookVO bookVO) {
		int bookId = 0;
		// .insert("namespace.id", 파라미터)
		// sqlSessionTemplate : 쿼리 실행
		int result = this.sqlSessionTemplate.insert("book.insert", bookVO);
		log.info("result : " + result);
		if (result > 0) { // 성공
			bookId = bookVO.getBookId();
		} else {
			bookId = 0;
		}
		log.info("bookId : " + bookId);
		return bookId; // 입력된 기본키 값
	}
	
	// 책 상세보기
	public BookVO detail(BookVO bookVO) {
		// sqlSessionTemplate : 쿼리를 실행하는 객체
		// selectOne() 메서드 : 1행을 가져올 때 사용 / selectList() 메서드 : 결과 집합 목록 반환(다중행)
		// 결과 행 수가 0일 때는 null을 반환하고 행 수가 2 이상일 때는 TooManyResultException 예외 발생
		// selectOne("namespace.id", 파라미터)
		return this.sqlSessionTemplate.selectOne("book.detail", bookVO);
	}
	
	//책 수정하기
	//insert ,update, delete의 경우 리턴 타입은 int
	public int update(BookVO bookVO) {
		/*
		 * sqlSessionTemplate 객체의 update 메소드(쿼리ID, 쿼리 파라미터)
		 * 쿼리ID : 매퍼XML의 namespace.id
		 * update 구문은 조건에 일치하는 모든 행을 갱신하므로
		 * 	영향받은 행 수는 0 또는 1 이상. 
		 */
		return this.sqlSessionTemplate.update("book.update",bookVO);
	}
	
	//책 삭제
	public int delete(BookVO bookVO) {
		//delete 구문도 where 조건에 일치하는 모든 행을 삭제하믈
		// 영향받은 행 수는 0 또는 1이상.
		return this.sqlSessionTemplate.delete("book.delete",bookVO);
	}
}














