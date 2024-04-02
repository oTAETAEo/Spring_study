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

// MemberContorller -> MemberService -> MemoryMemberRepository 의존 관계 형성.

@Controller
public class MemberContorller {
	
	private final MemberService memberService;
	
	// (생성자 의존관계 주입)
	// @Autowired 필요한 의존 객체의 “타입"에 해당하는 빈을 찾아 주입한다.
	// 의존성 주입을 할 대상을 찾지 못한다면 애플리케이션 구동에 실패한다.
	// 스프링 빈에 등록 되어있지 않은경우를 말한다.
	// 내가 직접 new로 MemberContorller를 생성 한다고 했을때 @Autowired는 실행되지 않는다.
	@Autowired
	public MemberContorller(MemberService memberService) {
		this.memberService = memberService;
	}
	
	// /members/new 이 URL로 접속하면 templates에 있는 members/createMemberForm.html을 뿌려준다.
	@GetMapping("/members/new")
	public String createForm() {
		return "members/createMemberForm";
	}
	
	
	@PostMapping("/members/new")
	public String create(MemberForm form) {
		// 받아온 객체를 옮겨 저장.
		Member member = new Member();
		member.setName(form.getName());
	
		memberService.join(member);
		
		// redirect:/ 초기 화면으로 돌아가라는 코드.
		return "redirect:/";
	}

	// /members 이 URL로 접속하면 
	@GetMapping("/members")
	public String list(Model model) {
		// 객체의 이름을 Array List로 리턴 받는다.
		List<Member> members = memberService.findMembers();
		// Controller에서 사용자에게 응답할 View를 생성할 때 Model을 이용하여 View에 데이터를 전달하는 방법 addAttribute().
		model.addAttribute("members", members);
		return "members/memberList";
	}
	
}

