package hello.Spring_Study.repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.stereotype.Repository;

import hello.Spring_Study.domain.Member;

// @Repository 자동으로 스프링 컨테이너에 스프링 빈으로 자동 등록 된다.
// 컴포넌트 스켄 방식.
// 싱글톤 객체로 만들어진다 (스프링 빈).

// 예제의 가상의 시나라오(아직 DB가 정해지지 않은 시나리오) 때문에 메모리에 잠깐 저장 한다.
public class MemoryMemberRepository implements MemberRepository{

	private static Map<Long, Member> store = new HashMap<>(); 
	private static long sequence = 0L;
	
	@Override
	public Member save(Member member) {
		member.setId(++sequence);
		store.put(member.getId(), member);
		return member;
	}

	@Override
	public Optional<Member> findById(Long id) {
		return Optional.ofNullable(store.get(id));
	}

	@Override
	public Optional<Member> findByName(String name) {
		return store.values().stream()
			.filter(member -> member.getName().equals(name))
			.findAny();
	}

	@Override
	public List<Member> findAll() {
		return new ArrayList<>(store.values());
	}

	public void clearStore() {
		store.clear();
	}

}
