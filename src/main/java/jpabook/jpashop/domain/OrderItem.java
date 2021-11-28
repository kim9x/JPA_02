package jpabook.jpashop.domain;

import static javax.persistence.FetchType.LAZY;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import jpabook.jpashop.domain.item.Item;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter @Setter
// 기본 생성자의 접근제어자를 protected로 설정하는 annotation
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class OrderItem {
	
	@Id @GeneratedValue
	@Column(name = "order_item_id")
	private Long id;
	
	@ManyToOne(fetch = LAZY)
	@JoinColumn(name = "item_id")
	private Item item;
	
	@ManyToOne(fetch = LAZY)
	@JoinColumn(name = "order_id")
	private Order order;
	
	private int orderPrice; // 주문 가격
	private int count; // 주문 수량
	
	/*
	 * 위의 Lombok Annotation인
	 * @NoArgsConstructor(access = AccessLevel.PROTECTED) 으로 대체 가능
	 * protected OrderItem() {
	 * 
	 * }
	 */
	
	
	//==생성 메서드==//
	public static OrderItem createOrderItem(Item item, int orderPrice, int count) {
		OrderItem orderItem = new OrderItem();
		orderItem.setItem(item);
		orderItem.setOrderPrice(orderPrice);
		orderItem.setCount(count);
		
		item.removeStock(count);
		
		return orderItem; 
	}
	
	//==비즈니스 로직==//
	public void cancel() {
		getItem().addStock(count);
	}

	//==조회 로직==//
	
	/**
	 * 주문상품 전체 가격 조회
	 * @return
	 */
	
	public int getTotalPrice() {
		return getOrderPrice() * getCount() ;
	}
	
	
}
