package hello.Spring_Study.service;

import java.util.List;
import java.util.Optional;

import hello.Spring_Study.domain.Member;
import hello.Spring_Study.repository.MemberRepository;
import hello.Spring_Study.repository.MemoryMemberRepository;

public class MemberService {
	
	private final MemberRepository memberRepository;
	
	public MemberService(MemoryMemberRepository memberRepository) {
		this.memberRepository = memberRepository;
	}	
	
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














