package jpabook.jpashop.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import javax.persistence.EntityManager;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;

import jpabook.jpashop.domain.Member;
import jpabook.jpashop.repository.MemberRepository;

@WebAppConfiguration
@ExtendWith(SpringExtension.class)
@SpringBootTest
@Transactional
class MemberServiceTest {
	
	@Autowired MemberService memberService;
	@Autowired MemberRepository memberRepository;
	@Autowired EntityManager em;
	
	@Test
//	@Rollback(false)
	public void 회원가입() throws Exception {
		// given
		Member member = new Member();
		member.setName("kim");
		
		// when
		Long savedId = memberService.join(member);
		
		// then
		em.flush();  
		assertEquals(member, memberRepository.findOne(savedId));
	}
	
	@Test
	public void 중복_회원_예외() throws Exception {
		// given
		Member member1 = new Member();
		member1.setName("kim");
		
		Member member2 = new Member();
		member2.setName("kim");
		
		// when
		memberService.join(member1);
		try {
			memberService.join(member2); // 예이가 발생해야 한다!!!
			
		} catch (IllegalStateException e) {
			return;
			// TODO: handle exception
		}
		
		//then
		fail("예외가 발생해야 한다.");
	}

}
