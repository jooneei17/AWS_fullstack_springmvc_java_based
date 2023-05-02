package co.jmymble.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import co.jmymble.domain.MemberVO;
import co.jmymble.service.MemberService;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j;

@Controller
@RequestMapping("member")
@Log4j
@AllArgsConstructor
public class MemberController {
	private MemberService memberService;
	
	@GetMapping("login")
	public void login() {}
	
//	@GetMapping("chat")
//	public void chat() {}
//	
//	
//	@PostMapping("login")
//	public String login(MemberVO vo, HttpSession session, RedirectAttributes rttr){
//		log.info(vo);
//		MemberVO memberVO = memberService.login(vo);
//		if(memberVO == null) {
//			rttr.addFlashAttribute("mgs" , "로그인 실패");
//		}
//		else {
//			session.setAttribute("member", memberVO);
//			log.info("로그인 성공");
//		}
//		return "redirect:/member/login";
//	}
//	
//	
//	@RequestMapping("logout")
//	public String logout(HttpSession session){
//		log.info("로그아웃");
//		session.invalidate();
//		return "redirect:/member/login";
//	}
//	
//	
//	@GetMapping("getList")
//	@ResponseBody
//	public List<MemberVO> getList(){
//		return memberService.getList();
//	}
}
