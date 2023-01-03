package kr.or.ddit.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import kr.or.ddit.service.BookService;
import kr.or.ddit.vo.BookVO;
import lombok.extern.slf4j.Slf4j;

/*
 * Controller 어노테이션
 * 프링아 이거 웹 브라우저의 요청(request)를 받아들이는 컨트롤러야 
 * 미리 메모리에 올림
 */
@Slf4j
@Controller
public class BookController {
	@Autowired
	BookService bookService;
	
	// get 방식으로/create URL을 톰캣서버에 요청
	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public String create() {
		// 주소 리턴 /WEB-INF/views/ + ... + .jsp
		return "book/create";
	}
	
	// post방식으로 보내기
	// URL : http://localhost/create?title=개똥이모험&category=소설&price=10000
	// 요청파라미터(HTTP 파라미터, QueryString) : title=개똥이모험&category=소설&price=10000
	// 멤버변수와 쿼리스트링의 name값은 같아야한다.
	@RequestMapping(value="/create", method= RequestMethod.POST)
	// vo는 modelAttribute로 받음
	public ModelAndView createPost(@ModelAttribute BookVO bookVO) {
		/*
		 * ModelAndView 
		 * 1) Model : Controller가 jsp로 반환할 데이터(String, int, List, Map, VO...)를 담당 
		 * 2) View : 화면을 담당(뷰(View : JSP)의 경로)
		 */
		log.info("bookVO : " + bookVO.toString());
		
		// 1증가된 기본키값
		int bookId = this.bookService.insert(bookVO);
		log.info("bookId : " + bookId);
		
		// 쿼리스트링이 bookVO로 갔다가
		ModelAndView mav = new ModelAndView();
		
		// bookId : 입력된 기본키 값
		if(bookId<1) { // insert 실패
			// /create를 다시 요청 함 -> uri주소가 바뀜
			mav.setViewName("redirect:/create");
		}else { // insert 성공
			mav.setViewName("redirect:/detail?bookId=" + bookId);
		}
		
		// /create로 옴(get)
		// redirect : 재요청 -> url주소 바뀜		
		return mav;
	}
	
	// 책 상세보기
	// 요청 URI : http://localhost/detail?bookId=2
	// 요청 URL : http://localhost/detail
	// 요청파라미터 : bookId=2
	// 방식 : get
	@RequestMapping(value="/detail", method=RequestMethod.GET)
	public ModelAndView detail(@ModelAttribute BookVO bookVO, ModelAndView mav) {
		log.info("bookVO : " + bookVO.toString());
		
		// 후 : bookId=4, title=아바타, category=영화, price=13000, insertDate=22/12/21
		BookVO data = this.bookService.detail(bookVO);
		
		// model : 데이터를 jsp로 넘겨줌
		mav.addObject("data", data); // 값
		mav.addObject("bookId", bookVO.getBookId()); // 기본키
		
		// forwarding => 데이터를 jsp로 넘겨줌 / but, redirect : 데이터를 jsp로 못 넘겨줌
		// /WEB-INF/views/ + ... + .jsp
		mav.setViewName("book/detail");
		return mav;
	}
	
	// 요청URI : /update?bookId=2
	// 요청 URL : /update
	// 요청 파라미터(String 타입) : bookId=2 / int형으로 자동 형변환이 됨
	// 파라미터 받는 법 : 어노테이션(@)
	@RequestMapping(value="/update", method=RequestMethod.GET)
	public ModelAndView update(@RequestParam int bookId, @ModelAttribute BookVO bookVO,
			ModelAndView mav) {
		// 책 수정 화면 = 책 입력화면 + 책 상세 화면
		// 책 입력 화면 형식을 그대로 따라가고 빈 칸을 데이터로 채워주면 됨
		log.info("bookId : " + bookId);
		log.info("bookVO : " + bookVO.toString());
		
		BookVO data = this.bookService.detail(bookVO);
		
		mav.addObject("data",data);
		// view : jsp의 경로
		// /WEB-INF/views/ + ... + .jsp
		// forwarding : update jsp 처리
		mav.setViewName("book/update");
		return mav;
	}
	
	/*
	 * {"bookId":2, "title":"수정된제목","category":"수정딘카테고리","price":"10000"}
	 * VO 객체 매개변수의 경우 골뱅이ModelAttribute를 써줌
	 */
	@RequestMapping(value="/update", method=RequestMethod.POST)
	public ModelAndView update(@ModelAttribute BookVO bookVO,
			ModelAndView mav) {
		//BookVO [bookId=2, title=국악, category=음악, price=10000, insertDate=null]
		log.info("bookVO : " + bookVO.toString());
		
		//bookId가 2번인 정보를 수정
		int result = this.bookService.update(bookVO);
		
		if(result>0) { //성공
			//상세페이지로 이동(redirect : URI 주소 재요청)
			// /detail?bookId=bookVO의 bookId멤버변수의 값
			// /detail?bookId=2
			mav.setViewName("redirect:/detail?bookId="+bookVO.getBookId());
		}else {//실패
			mav.setViewName("redirect:/update?bookId="+bookVO.getBookId());
		}
		return mav;
	}
	
	//글 삭제, get방식은 위험
	@RequestMapping(value="/delete", method=RequestMethod.POST)
	public ModelAndView delete(BookVO bookVO,
			ModelAndView mav) {
		log.info("bookVO : " +bookVO.toString());
		
		//글 삭제 처리
		int result = this.bookService.delete(bookVO);
		//성공 : /list로 redirect
		if(result>0) {
			mav.setViewName("redirect:/list");
		}else {
			mav.setViewName("redirect:/detail?bookId="+bookVO.getBookId());
		}
		//실패 : /detail?bookId=2로 redirect
		return mav;
	}
	
	//글 목록
	@RequestMapping(value="/list", method=RequestMethod.GET)
	public ModelAndView list(ModelAndView mav) {
		//book폴더의 list.jsp를 해석(list_jsp.java)하고, 컴파일(list_jsp.class)..서블릿
		//하여 html을 만들어 크롬으로 응답(response)
		//forwarding
		mav.setViewName("book/list");
		
		return mav;
	}

}
























