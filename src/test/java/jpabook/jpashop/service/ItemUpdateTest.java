package jpabook.jpashop.service;


import javax.persistence.EntityManager;

import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import jpabook.jpashop.domain.item.Book;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class ItemUpdateTest {
	
	@Autowired
 	EntityManager em;
	
	public void updateTest() throws Exception {
		Book book = em.find(Book.class, 1L);
		
		// TX
		book.setName("asdfasdf");
		
		// 변경감지 == dirty checking
		// TX commit
		
	}

}
