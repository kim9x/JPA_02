package jpabook.jpashop.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import jpabook.jpashop.domain.Member;
import jpabook.jpashop.repository.MemberRepository;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;

@Service
// 전체는 readOnly를 true로 두고
// readOnly가 아닌 경우 ex)join
// 따로 @Transactional을 붙여주면
// 우선권을 가지므로 쓰기가 가능!
@Transactional(readOnly = true)

// 필드에 대한 모든 값들의 생성자를 만들어준다.
//@AllArgsConstructor

// final로 선언된 필드 값만 생성자를 만들어준다.
@RequiredArgsConstructor
public class MemberService {
	
	/*
	 * @Autowired private MemberRepository memberRepository;
	 */
	
	/*
	 * private MemberRepository memberRepository;
	 * 
	 * // Setter Injection을 주면 테스트 코드를 작성 할 때 // mock으로 직접 주입 등이 가능하다. 
	 * // 조립 전에 행여나 바뀌게 되면 치명적인 문제가 생긴다.
	 * // 동작 중에 바꿀 일이 없다.  
	 * // 그래서 요즘엔 생성자 Injection을 준다.
	 * 
	 * @Autowired public void setMemberRepository(MemberRepository memberRepository)
	 * { this.memberRepository = memberRepository; }
	 */
	
	/*
	 * private MemberRepository memberRepository;
	 * 
	 * // 생성자가 하나만 있으면 // 최신 Spring 버전에서는 // @Autowired를 생략할 수도 있다.
	 * 
	 * @Autowired public MemberService(MemberRepository memberRepository) {
	 * this.memberRepository = memberRepository; }
	 */
	
	private final MemberRepository memberRepository;

	/*
	 * public MemberService(MemberRepository memberRepository) { super();
	 * this.memberRepository = memberRepository; }
	 */

	/**
	 *  회원 가입
	 * @param member
	 * @return
	 */
	@Transactional
	public Long join(Member member) {
		validateDuplicateMember(member);
		memberRepository.save(member);
		return member.getId();
	}

	private void validateDuplicateMember(Member member) {
		// EXCEPTION
		List<Member> findMembers = memberRepository.findByName(member.getName());
		if (!findMembers.isEmpty()) {
			throw new IllegalStateException("이미 존재하는 회원입니다.");
		}
	}
	
	// 회원 전체 조회
//	@Transactional(readOnly = true)
	public List<Member> findMembers() {
		return memberRepository.findAll();
	}
	
	// 회원 단건 조회
//	@Transactional(readOnly = true)
	public Member findOne(Long memberId) {
		return memberRepository.findOne(memberId);
	}
}
