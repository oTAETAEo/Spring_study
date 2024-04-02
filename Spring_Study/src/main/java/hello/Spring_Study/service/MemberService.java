package hello.Spring_Study.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import hello.Spring_Study.domain.Member;
import hello.Spring_Study.repository.MemberRepository;

// @Service 자동으로 스프링 컨테이너에 스프링 빈으로 자동 등록 된다.
// 컴포넌트 스켄 방식 (자동 의존 관계 설정).
// 싱글톤 객체로 만들어진다 (스프링 빈).

//@Service
public class MemberService {
	
	private final MemberRepository memberRepository;
	
	// 최대한 호출되지 않아야할 메서드가 호출되면 안된다. 
	
	// (생성자 의존관계 주입) 제일 권장.
	// 생성자 주입.
	// 스프링 컨테이너에 있는 싱글톤 객체를 넣어준다.
	// @Autowired 필요한 의존 객체의 “타입"에 해당하는 빈을 찾아 주입한다.
	// 의존성 주입을 할 대상을 찾지 못한다면 애플리케이션 구동에 실패한다.
	// 이 코드에서는 스프링 컨테이너에서 MemberRepository 타입에 맞는 스프링 빈을 찾아서 넣어주는 코드 없다면 구동 실패.
	@Autowired 
	public MemberService(MemberRepository memberRepository) {
		this.memberRepository = memberRepository;
	}	
	
	// (필드 의존관계 주입)
	// 필드주입 방법. 별로 좋지는 않다.
//	@Autowired private MemberRepository memberRepository;

	// (setter 의존관계 주입)
	// 한번 세팅하고 거의 바꿀일이 없는데 계속 public 상태로 있어야 한다.
	// 보안에 취약해진다.

//	private MemberRepository memberRepository;

//	public void setMemberService(MemberRepository memberRepository) {
//		this.memberRepository = memberRepository;
//	}	
	
	/**
	 * 회원가입
	 */
	
	public Long join(Member member) {
		// 같은 이름이 있는 중복회원은 x
		validateDuplicateMember(member); // 중복 회원 검증.
		memberRepository.save(member);
		return member.getId();
	}

	private void validateDuplicateMember(Member member) {
		// ifPresent 위에서 findByName을 해서 이미 있는 객체인지 확인 한다.
		// 없다면 null을 리턴한다.
		// ifPresent는 result이 변수에 값이 있으면 true (실행), 없으면 false (실행x)
		memberRepository.findByName(member.getName())
		.ifPresent(t -> {
			throw new IllegalStateException("이미 존재하는 회원입니다.");
		});
	}
	
	/**
	 * 전체 회원 조회
	 */
	public List<Member> findMembers() {
		return memberRepository.findAll();
	}
	
	public Optional<Member> findOne(Long memberId) {
		return memberRepository.findById(memberId);
	}
	
}














