package kr.ac.kopo.dogworld.demo.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import kr.ac.kopo.dogworld.demo.jpa.JpaBreedRepository;
import kr.ac.kopo.dogworld.demo.jpa.JpaDogRepository;
import kr.ac.kopo.dogworld.demo.jpa.JpaMemberRepository;
import kr.ac.kopo.dogworld.demo.vo.BreedVO;
import kr.ac.kopo.dogworld.demo.vo.DogVO;
import kr.ac.kopo.dogworld.demo.vo.MemberVO;

//쿠키생성을 위한 추가

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import java.util.Base64;

@Controller
public class DogWorldController {
	
	@Autowired
	JpaMemberRepository jpaMember;
	
    @Autowired
    JpaDogRepository jpaDog;
    
    @Autowired
    JpaBreedRepository jpaBreed;

	@RequestMapping(value = "/")
	public ModelAndView mainView() {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("index");
		return mav;
	}

/*    @RequestMapping(value = "/")
    public ModelAndView mainView(HttpServletRequest request) {
        HttpSession session = request.getSession();
        MemberVO loginUser = (MemberVO) session.getAttribute("loginUser");

        ModelAndView mav = new ModelAndView();
        mav.setViewName("index");

        if (loginUser != null && "A".equals(loginUser.getAdmin())) {
            mav.addObject("isAdmin", true);
        } else {
            mav.addObject("isAdmin", false);
        }

        return mav;
    }*/
    
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
		mav.setViewName("loginForm");
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
            HttpSession session = request.getSession();
            session.setAttribute("loginUser", loginUser.get());
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
	

/*	
	//쿠키로그인
	@GetMapping("/loginForm")
    public ModelAndView loginForm(@RequestParam(value = "redirectUrl", required = false) String redirectUrl, HttpServletRequest request) {
        ModelAndView mav = new ModelAndView();
        mav.setViewName("loginForm");
        if (redirectUrl != null) {
            request.getSession().setAttribute("redirectUrl", redirectUrl);
        }
        return mav;
    }
    
	@PostMapping("/login")
    public ModelAndView login(@RequestParam("id") String loginId, 
                              @RequestParam("password") String loginPassword,
                              HttpServletRequest request, 
                              HttpServletResponse response, 
                              RedirectAttributes redirectAttributes) {
        Optional<MemberVO> loginUser = jpaMember.findById(loginId);

        ModelAndView mav = new ModelAndView();
        if (loginUser.isPresent() && loginPassword.equals(loginUser.get().getPassword())) {
            // 세션 생성
            HttpSession session = request.getSession();
            session.setAttribute("loginUser", loginUser.get());

            // 쿠키 생성 및 각 도메인에 설정
            String authToken = generateAutToken(loginId);
            setCookie(response, "172.31.9.168", authToken, "/Shopping"); // B 사이트

            // 리디렉트 URL 설정
            String redirectUrl = (String) request.getSession().getAttribute("redirectUrl");
            if (redirectUrl == null || redirectUrl.isEmpty()) {
                redirectUrl = "http://172.31.9.168:8080/Shopping/loginController";
            }
            mav.setViewName("redirect:" + redirectUrl);
            System.out.println("Redirecting to: " + redirectUrl);
        } else {
            mav.setViewName("loginForm");
        }
        return mav;
    }
	
    @GetMapping("/main")
    public ModelAndView mainPage() {
        ModelAndView mav = new ModelAndView();
        mav.setViewName("index");
        return mav;
    }
	
    //사용자의 이름을 포함한 인증 토큰 생성
	private String generateAutToken(String username) {
		return Base64.getEncoder().encodeToString((username + ":token").getBytes()); 
	} 
	//특정도메인에 대한 인증쿠키 설정 
	private void setCookie(HttpServletResponse response, String domain, String authToken, String path) {
		Cookie cookie = new Cookie("authToken", authToken);
		cookie.setDomain(domain);
        cookie.setPath(path);
        cookie.setHttpOnly(true);
        cookie.setMaxAge(60 * 60); // 쿠키 유효 기간 (1시간)
        response.addCookie(cookie);
	  }
*/
//	
	@RequestMapping(value="logout")
	public ModelAndView logout(HttpServletRequest request) {
		HttpSession session = request.getSession(false);
		ModelAndView mav = new ModelAndView();
		if(session != null) {
			session.invalidate();
		}
		mav.setViewName("redirect:/");
		return mav;
	}
	
	@RequestMapping(value="/myPage")
	public ModelAndView myPage(HttpServletRequest request) {
		HttpSession session = request.getSession();
		MemberVO loginUser = (MemberVO) session.getAttribute("loginUser");

        ModelAndView mav = new ModelAndView();
        mav.addObject("loginUser", loginUser);
        mav.setViewName("myPage");
        return mav;
	}
	

    // 비밀번호 인증 폼
    @RequestMapping(value="/myPageEditPAForm")
    public ModelAndView myPageEditPAForm() {
        ModelAndView mav = new ModelAndView();
        mav.setViewName("myPageEditPAForm");
        return mav;
    }

    // 비밀번호 인증
    @RequestMapping(value="/myPageEditPA")
    public ModelAndView myPageEditPA(HttpServletRequest request, @RequestParam("password") String password, RedirectAttributes redirectAttributes) {
        HttpSession session = request.getSession();
        MemberVO loginUser = (MemberVO) session.getAttribute("loginUser");

        if (loginUser.getPassword().equals(password)) {
            redirectAttributes.addFlashAttribute("loginUser", loginUser);
            return new ModelAndView("redirect:/myPageEditForm");
        } else {
            ModelAndView mav = new ModelAndView();
            mav.setViewName("myPageEditPAForm");
            mav.addObject("error", "비밀번호가 일치하지 않습니다.");
            return mav;
        }
    }

    // 회원 정보 수정 폼
    @RequestMapping(value="/myPageEditForm")
    public ModelAndView myPageEditForm(HttpServletRequest request) {
        HttpSession session = request.getSession();
        MemberVO loginUser = (MemberVO) session.getAttribute("loginUser");

        ModelAndView mav = new ModelAndView();
        mav.addObject("loginUser", loginUser);
        mav.setViewName("myPageEditForm");
        return mav;
    }

    // 회원 정보 수정
    @RequestMapping(value="/myPageEdit")
    public ModelAndView myPageEdit(MemberVO member, HttpServletRequest request) {
        HttpSession session = request.getSession();
        MemberVO loginUser = (MemberVO) session.getAttribute("loginUser");

        // 날짜 형식을 변환
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date birthDate = dateFormat.parse(member.getBirthDate());
            member.setBirthDate(dateFormat.format(birthDate));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        
        //세션정보 변경
        loginUser.setName(member.getName());
        loginUser.setBirthDate(member.getBirthDate());
        loginUser.setPhone(member.getPhone());
        loginUser.setPost(member.getPost());
        loginUser.setBasicAddr(member.getBasicAddr());
        loginUser.setDetailAddr(member.getDetailAddr());
        loginUser.setEmail(member.getEmail());
        
        jpaMember.updateMember(
            loginUser.getId(),
            member.getName(),
            member.getBirthDate(),
            member.getPhone(),
            member.getPost(),
            member.getBasicAddr(),
            member.getDetailAddr(),
            member.getEmail()
        );

        session.setAttribute("loginUser", loginUser);

        ModelAndView mav = new ModelAndView();
        mav.setViewName("redirect:/myPage");
        return mav;
    }
	
    // 애완견 입력 폼, DB테이블에 있는 견종정보 가져옴
    @RequestMapping(value="/dogInsertForm")
    public ModelAndView dogInsertForm(HttpServletRequest request) {
    	HttpSession session = request.getSession();
        MemberVO loginUser = (MemberVO) session.getAttribute("loginUser");
        
        List<BreedVO> breeds = jpaBreed.findAllByOrderByBreedNameAsc();
        ModelAndView mav = new ModelAndView();
        mav.addObject("breeds", breeds);
        mav.addObject("loginUser", loginUser);
        mav.setViewName("dogInsertForm");
        return mav;
    }
    
    //애완견 입력 폼에서 넘어온 정보 저장
    @RequestMapping(value="/dogInsert")
    public ModelAndView dogInsert(DogVO dog, RedirectAttributes redirectAttributes) {
        jpaDog.save(dog);
        ModelAndView mav = new ModelAndView();
        mav.setViewName("redirect:/"); 
        return mav;
    }
    
    // 애완견 리스트
    @RequestMapping(value="/dogList")
    public ModelAndView dogList(HttpServletRequest request) {
    	HttpSession session = request.getSession();
        MemberVO loginUser = (MemberVO) session.getAttribute("loginUser");
        
        List<DogVO> dogs = jpaDog.findByMemberId(loginUser.getId());
        ModelAndView mav = new ModelAndView();
        mav.addObject("dogs", dogs);
        mav.setViewName("dogList");
        return mav;
    }
    
    // 애완견 수정 폼으로 이동하는 메서드
    @RequestMapping(value="/dogUpdateForm")
    public ModelAndView dogUpdateForm(@RequestParam("no") int no) {
        Optional<DogVO> dog = jpaDog.findByNo(no);
        ModelAndView mav = new ModelAndView();
        if (dog.isPresent()) {
            mav.addObject("dog", dog.get());
            List<BreedVO> breeds = jpaBreed.findAllByOrderByBreedNameAsc();
            mav.addObject("breeds", breeds);
            mav.setViewName("dogUpdateForm");
        } else {
            mav.setViewName("redirect:/dogList");
        }
        return mav;
    }

    // 애완견 정보를 업데이트하는 메서드
    @RequestMapping(value="/updateDog")
    public ModelAndView updateDog(DogVO dog, RedirectAttributes redirectAttributes) {
        jpaDog.save(dog);
        ModelAndView mav = new ModelAndView();
        mav.setViewName("redirect:/dogList");
        return mav;	
    }

}
