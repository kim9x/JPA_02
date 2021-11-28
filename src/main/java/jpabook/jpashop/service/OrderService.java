package jpabook.jpashop.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import jpabook.jpashop.domain.Delivery;
import jpabook.jpashop.domain.Member;
import jpabook.jpashop.domain.Order;
import jpabook.jpashop.domain.OrderItem;
import jpabook.jpashop.domain.item.Item;
import jpabook.jpashop.repository.ItemRepository;
import jpabook.jpashop.repository.MemberRepository;
import jpabook.jpashop.repository.OrderRepository;
import lombok.RequiredArgsConstructor;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class OrderService {
	
	private final OrderRepository orderRepository;
	private final MemberRepository memberRepository;
	private final ItemRepository itemRepository;
	
	/**
	 * 주문
	 */
	@Transactional
	public Long order(Long memberId, Long itemId, int count) {
		
		// 엔티티 조회
		Member member = memberRepository.findOne(memberId);
		Item item = itemRepository.findOne(itemId);
		
		// 배송정보 생성
		Delivery delivery = new Delivery();
		delivery.setAddress(member.getAddress());
		
		// 주문상품 생성
		OrderItem orderItem = OrderItem.createOrderItem(item, item.getPrice(), count);
		
		// 아래처럼 개발할 수도 있지만,
		// 생성하는 경로를 막아야한다.
		// 왜냐? 생성 로직이 다 다르게되면 분산되어 유지보수하기 어려워진다.
		// 기본 생성자의 접근제어자를 protected로 설정
//		OrderItem orderItem1 = new OrderItem();
		
		// 주문 생성
		Order order = Order.createOrder(member, delivery, orderItem);
		
		// 주문 저장
		orderRepository.save(order);
		
		return order.getId();
	}
	
	/**
	 * 주문 취소
	 */
	@Transactional
	public void cancelOrder(Long orderId) {
		// 주문 엔티티 조회
		Order order = orderRepository.findOne(orderId);
		// 주문 취소
		order.cancel();
	}
	
	// 검색
	/*
	 * public List<Order> findOrders(OrderSearch orderSearch) { return
	 * orderRepository.findAll(orderSearch); }
	 */

}