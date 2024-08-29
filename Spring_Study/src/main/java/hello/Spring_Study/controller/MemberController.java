package hello.Spring_Study.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import hello.Spring_Study.domain.Member;
import hello.Spring_Study.service.MemberService;

// 컨트롤러 서비스 리포지토리는 정형화된 패턴이다.
// 컨트롤러를 통해서 외부 요청을 받고 서비스에서 비즈니스 로직을 만들고 리포지토리에 데이터를 저장.
// 정형화된 컨트롤러 서비스 리포지토리는 컴포넌크 스켄을 사용한다.

// @Controller 자동으로 스프링 컨테이너에 스프링 빈으로 자동 등록 된다.
// 컴포넌트 스캔 방식
// 싱글톤 객체로 만들어진다 (스프링 빈).

// MemberController -> MemberService -> MemoryMemberRepository 의존 관계 형성.

@Controller
public class MemberController {
	
	private final MemberService memberService;

	@Autowired
	public MemberController(MemberService memberService) {
		this.memberService = memberService;
	}
	
	@GetMapping("/members/new")
	public String createForm() {
		return "members/createMemberForm";
	}

	@PostMapping("/members/new")
	public String create(MemberForm form) {
		Member member = new Member();
		member.setName(form.getName());
		memberService.join(member);
		return "redirect:/";
	}

	@GetMapping("/members")
	public String list(Model model) {
		List<Member> members = memberService.findMembers();
		model.addAttribute("members", members);
		return "members/memberList";
	}
	
}

