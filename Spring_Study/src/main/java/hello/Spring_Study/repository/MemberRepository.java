package hello.Spring_Study.repository;

import java.util.List;
import java.util.Optional;

import hello.Spring_Study.domain.Member;

public interface MemberRepository {

	Member save(Member member);
	Optional<Member> findById(Long id);
	Optional<Member> findByname(String name);
	List<Member> findAll();
}
