package kr.ac.kopo.dogworld.demo.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import jakarta.servlet.http.HttpServletRequest;
import kr.ac.kopo.dogworld.demo.jpa.JpaMemberRepository;
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
	public ModelAndView loginForm(HttpServletRequest request) {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("loginForm");
		String referer = request.getHeader("Referer");
		System.out.println("이전 페이지!!!!" + referer);
		request.getSession().setAttribute("prevUrl", referer);
		return mav;
	}
	
	@RequestMapping(value="/login")
	public ModelAndView login(MemberVO member, HttpServletRequest request, RedirectAttributes redirectAttributes) {
		String loginId = member.getId();
		String loginPassword = member.getPassword();
		Optional<MemberVO> loginUser = jpaMember.findById(loginId);
		String password = loginUser.get().getPassword();
		
		ModelAndView mav = new ModelAndView();
		if(loginPassword.equals(password)) {
			//mav.addObject("loginUser", loginUser.get());
			//request.setAttribute("loginId", loginId);
			//도메인주소
			redirectAttributes.addAttribute("id", loginId);
			redirectAttributes.addFlashAttribute("loginUser", loginUser.get());
			String prevUrl = (String)request.getSession().getAttribute("prevUrl");
			mav.setViewName("redirect:" + prevUrl);
			System.out.println(prevUrl);
			
			
		} else {
			mav.setViewName("loginForm");
		}
		return mav;
	}
	
}
