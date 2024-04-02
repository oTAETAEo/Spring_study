package hello.Spring_Study.service;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import hello.Spring_Study.repository.MemberRepository;
import hello.Spring_Study.repository.MemoryMemberRepository;

// 자바 코드로 직접 스프링 빈 등록 하는 방법.
// 컴포넌트 스켄을 사용하면 많은 코드를 손봐야 한다.
// 정형화 되지 않거나 상황에 따라서 구현 클래스를 변경해야 할때 직접 스프링 빈으로 등록한다.

@Configuration
public class SpringConfig {
	
	// 스프링 컨테이너에 스프링 빈을 등록 한다.
	
	@Bean
	public MemberService memberService() {
		return new MemberService(memberRepository());
	}
	
	@Bean
	public MemberRepository memberRepository() {
		return new MemoryMemberRepository();
	}
	
}
