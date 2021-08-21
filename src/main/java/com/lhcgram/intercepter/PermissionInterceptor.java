package com.lhcgram.intercepter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

@Component
public class PermissionInterceptor implements HandlerInterceptor {

	// 로그 찍기를 위해 추가함...
	private Logger logger = LoggerFactory.getLogger(PermissionInterceptor.class);
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		
		// 세션을 통해 로그인 상태 확인
		HttpSession session = request.getSession();
		String loginId = (String)session.getAttribute("userLoginId"); // Object로 가져오기 때문에 형변환.
		// 위 코드에서 userLoginId 변수명을 틀려서 interceptor설정한 것이 걸리지 않음... 잘 확인하자.
		// uri는 리소스를 식별하는 고유한 문자열 시퀀스.
		String uri = request.getRequestURI();
		
		logger.info("#uri : "+uri);
		logger.info("###### loginId : "+loginId);
		
		// 로그인X + /post/~~는 로그인 페이지로 리다이렉트.
		// userId를 가져왔을 때 조건에서 null이 확인 안 되어 loginId로 변경. / int는 null이 안 된다...
		if(loginId==null && uri.startsWith("/timeline")) {
			response.sendRedirect("/user/sign_in_view");
			return false;
			// Controller로 넘어가는 것을 방지. true를 return하면 controller로 넘어가기 때문
		}
		
		// 로그인O + /user/~~는 post 페이지로.
		if(loginId!=null && uri.startsWith("/user")) {
			response.sendRedirect("/timeline/timeline_list_view");
			return false;
		}
		return true;
	}
	
	// 여기서부턴 아직 활용 X라서 로그만...
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			@Nullable ModelAndView modelAndView) throws Exception {
		String uri = request.getRequestURI();
		logger.info("#### postHandle():"+uri);
	}
	
	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler,
			@Nullable Exception ex) throws Exception {
		String uri = request.getRequestURI();
		logger.info("#### afterCompletion():"+uri);
	}
}
