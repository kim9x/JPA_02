package jpabook.jpashop.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import jpabook.jpashop.domain.Member;
import lombok.RequiredArgsConstructor;

@Repository
// Repository에서도 가능하다.
@RequiredArgsConstructor
public class MemberRepository {
	
//	@PersistenceContext
	private final EntityManager em;
	
	public void save(Member member) {
		em.persist(member); 
	}
	
	// 파라미터로 PK 전달
	public Member findOne(Long id) {
		return em.find(Member.class, id);
	}
	
	// 테이블이 아닌 엔티티가 from의 대상
	public List<Member> findAll() {
//		List<Member> result = em.createQuery("select m from Member m", Member.class)
//				.getResultList();
//		
//		return result;
		
		return em.createQuery("select m from Member m", Member.class)
				.getResultList( );
	}
	
	public List<Member> findByName(String name) {
		return em.createQuery("select m from Member m where m.name= :name", Member.class)
				.setParameter("name", name)
				.getResultList();
	}

}
