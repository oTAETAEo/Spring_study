package hello.Spring_Study.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import hello.Spring_Study.service.MemberService;

//컨트롤러 서비스 리포지토리는 정형화된 패턴이다.
//컨트롤러를 통해서 외부 요청을 받고 서비스에서 비즈니스 로직을 만들고 리포지토리에 데이터를 저장.

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
	@Autowired
	public MemberContorller(MemberService memberService) {
		this.memberService = memberService;
	}
	
}
