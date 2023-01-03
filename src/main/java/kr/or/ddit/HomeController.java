package kr.or.ddit;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	/**
	 * Simply selects the home view to render by returning its name.
	 */
	//Request : 요청(크롬(jsp) -> 톰켓)
	//Mapping : 요청(localhost/)과 메소드를 매핑
	//요청 방식(타입) : GET(주소표시줄에 파라미터가 노출)
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		logger.info("Welcome home! The client locale is {}.", locale);
		
		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
		
		String formattedDate = dateFormat.format(date);
		//모델 인터페이스 -> 이름속성에 데이터를 넣음
		model.addAttribute("serverTime", formattedDate );
		model.addAttribute("name", "개똥이" );
		
		///WEB-INF/views/+home+.jsp => /WEB-INF/views/home.jsp
		return "home";
	}
	
}
