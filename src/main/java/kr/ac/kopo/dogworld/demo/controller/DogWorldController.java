package kr.ac.kopo.dogworld.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import jakarta.servlet.http.HttpServletRequest;
import kr.ac.kopo.dogworld.demo.jpa.JpaMemberRepository;
import kr.ac.kopo.dogworld.demo.model.Login;
import kr.ac.kopo.dogworld.demo.vo.MemberVO;

@Controller
public class DogWorldController {
	
	@Autowired
	JpaMemberRepository jpaMember;
	
	@RequestMapping(value = "/")
	public ModelAndView mainView() {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("index");
		return mav;
	}
	
	@RequestMapping(value="/join")
	public ModelAndView join() {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("join");
		return mav;
	} 
	@RequestMapping(value="/joinForm")
	public ModelAndView joinForm(MemberVO member) {
		jpaMember.save(member);
		ModelAndView mav = new ModelAndView();
		mav.setViewName("join");
		return mav;
	}
	
	//로그인
	@RequestMapping(value="/loginForm")
	public ModelAndView loginForm() {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("loginForm");
		return mav;
	}
	
	@RequestMapping(value="/login")
	public ModelAndView login(HttpServletRequest request) {
		String id = request.getParameter("id");
		String password = request.getParameter("password");
		
		Login model = new Login();
		boolean check = model.checkLoginInfo(id, password);
		return null;
	}
}
