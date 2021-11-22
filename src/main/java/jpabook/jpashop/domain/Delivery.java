package jpabook.jpashop.domain;

import static javax.persistence.FetchType.LAZY;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter @Setter
public class Delivery {
	
	@Id @GeneratedValue
	@Column(name = "delivery_id")
	private Long id;
	
	@OneToOne(mappedBy = "delivery", fetch = LAZY)
	private Order order;
	
	@Embedded
	private Address address;
	
	// EnumType.ORDINAL을 사용하게되면
	// 넘버링을 하게되는데..
	// 중간에 새로운 값이 생길 경우 치명적인 장애로 이어질 수 있다.
	@Enumerated(EnumType.STRING)
	private DeliveryStatus status; // READY, COMP

}
