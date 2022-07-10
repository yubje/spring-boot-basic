package jpabook.jpashop.service;

import jpabook.jpashop.domain.Delivery;
import jpabook.jpashop.domain.Member;
import jpabook.jpashop.domain.Order;
import jpabook.jpashop.domain.OrderItem;
import jpabook.jpashop.domain.item.Item;
import jpabook.jpashop.repository.ItemRepository;
import jpabook.jpashop.repository.MemberRepository;
import jpabook.jpashop.repository.OrderRepository;
import jpabook.jpashop.repository.OrderSearch;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final MemberRepository memberRepository;
    private final ItemRepository itemRepository;



    /**
     *  주문 저장
     */
    @Transactional
    public Long order(Long memberId, Long itemId, int count) {
        // find entity(엔티티 조회)
        Member member = memberRepository.findOne(memberId);
        Item item = itemRepository.findOne(itemId);

        // create delivery info (배송정보 생성)
        Delivery delivery = new Delivery();
        delivery.setAddress(member.getAddress());

        // create orderItem info (주문상품 정보 생성)
        OrderItem orderItem = OrderItem.createOrderItem(item, item.getPrice(), count);

        // create order (주문 생성)
        Order order = Order.createOrder(member, delivery, orderItem);

        // save order (주문 저장) -  delivery, orderItem은 domain에서 cascade 되어 있으므로 같이 생성
        orderRepository.save(order);

        return order.getId();
    }

    /**
     *  주문 취소
     */
    @Transactional
    public void cancelOrder(Long orderId) {
        // find entity (엔티티 조회)
        Order order = orderRepository.findOne(orderId);
        // cancel order (주문 취소)
        order.cancel();
    }

    /**
     *  주문 검색
     */
    public List<Order> findOrders(OrderSearch orderSearch) {
        return orderRepository.findAllByString(orderSearch);
    }

}
