package hello.Spring_Study.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.fail;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import hello.Spring_Study.domain.Member;
import hello.Spring_Study.repository.MemoryMemberRepository;

public class MemberServiceTest {
	
	MemoryMemberRepository memberRepository;
	MemberService memberService;	// 테스트할 메소드가 있는 객체.
	
	// 테스트 메서드가 실행되기 전에 먼저 실행 (반복).
	// 이전에는 모든 테스트 케이스가 같은 객체를 사용했기 때문에 의존성이 높았다.
	// (이전 코드)
	// MemoryMemberRepository memberRepository = new MemoryMemberRepository();
	// MemberService memberService = new MemberService();
	// 아래 코드는 테스트 메서드가 실행 되기 전에 먼저 실행해서 의존성을 낮추기 위해 계속 객체를 만든다.
	// 이것을 DI(Dependency Injection) 디펜던시 인젝션 이라고 한다.
	
	// @BeforeEach는 테스트 메서드 하나가 시작하기 전에 먼저 실행 (반복)
	// 테스트 메서드 하나가 실행되기 전에 미리 실행이 되어 다른 테스트 코드에서 만들어진 객체를 사용하지 않는다.
	@BeforeEach
	public void beforEach() {
		memberRepository = new MemoryMemberRepository();
		memberService = new MemberService(memberRepository); 
	}
	
	// @AfterEach는 테스트 메서드 하나가 끌나면 실행 (반복)
	// 테스트 메서드 하나가 끌나면 실행되어 이전 데이터 값을 모두 지운다.
	@AfterEach 
	public void afterEach() {
		memberRepository.clearStore();
	}
	
	
	@Test
	void 회원가입() {
		// given (정해진)
		Member member = new Member();
		member.setName("hello");
		
		// when (언제)
		Long saveId = memberService.join(member);
		
		// then (그리고 나서)
		Member findMember = memberService.findOne(saveId).get();
		Assertions.assertThat(member.getName()).isEqualTo(findMember.getName());

		// isEqualTo 메소드는 내용 자체를 비교한다.
	}

	@Test
	void 중복_회원_에외() {
		// given (정해진)
		Member member1 = new Member();
		member1.setName("spring");

		Member member2 = new Member();
		member2.setName("spring");
		// when (언제)
		memberService.join(member1);	
		IllegalStateException e = assertThrows(IllegalStateException.class,() -> memberService.join(member2));
		assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");

		//		try {
//			memberService.join(member2);
//			fail();
//		} catch (IllegalStateException e) {
//			assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");
//		}
		// then (그리고 나서)
	}
	
	
	@Test
	void findMembers() {
		
	}
	
	@Test
	void findOne() {
		
	}
	
}
