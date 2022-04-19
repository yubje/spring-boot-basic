package jpabook.jpashop.repository;

import jpabook.jpashop.domain.Order;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class OrderRepository {
    private final EntityManager em;

    /**
     *  주문 저장
     */
    public void save(Order order) {
        if (order.getId() == null) {
            em.persist(order);
        } else {
            em.merge(order);
        }
    }

    /**
     *  주문 찾기
     */
    public Order findOne(Long id) {
        return em.find(Order.class, id);
    }


    /**
     * 전체 주문 찾기
     */
    public List<Order> findAll() {
        return em.createQuery("select * from Order o", Order.class)
                .getResultList();
    }


}
