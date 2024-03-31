package hello.Spring_Study.repository;

import static org.assertj.core.api.Assertions.assertThat;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import hello.Spring_Study.domain.Member;

class MemoryMemberRepositoryTest{

	MemoryMemberRepository repository = new MemoryMemberRepository();
	
	// 테스트 기능을 사용할때 이전의 결과에 나온 db에 저장된 데이터의 의해 다음 테스트 코드에서 에러가 발생할수 있기때문에
	// 하나의 테스트가 끝나면 db의 이전 결과를 지워준다. db의 결과를 지우는 매소드를 실행시킨다(@AfterEach).
	// 테스트 순서의 의존 관계가 있는 테스트 코드는 좋은 코드가 아니다.
	@AfterEach
	public void afterEach() {
		repository.clearStore();
	}
	
	@Test
	public void save() {
		Member member = new Member();
		member.setName("spring");
		
		repository.save(member);
		
		Member result =  repository.findById(member.getId()).get();
		assertThat(member).isEqualTo(result);
	}
	
	@Test
	public void findByName() {
		
		Member member1 = new Member();
		member1.setName("spring1");
		repository.save(member1);
		
		Member member2 = new Member();
		member2.setName("spring2");
		repository.save(member2);
		
		Member result =  repository.findByName("spring1").get();
		assertThat(result).isEqualTo(member1);
	}
	
	@Test
	public void findAll() {
		
		Member member1 = new Member();
		member1.setName("spring1");
		repository.save(member1);
		
		Member member2 = new Member();
		member2.setName("spring2");
		repository.save(member2);
		
		List<Member> result = repository.findAll();
		assertThat(result.size()).isEqualTo(2);
	}
}

















