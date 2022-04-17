package jpabook.jpashop.repository;

import jpabook.jpashop.domain.item.Item;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class ItemRepository {

    private final EntityManager em;

    public void save(Item item) {
        if (item.getId() == null) {   // 1. id 값이 없는 신규 객체인 경우 저장
            em.persist(item);
        } else {                      // 2. id 값이 있는 경우 update
            em.merge(item);
        }
    }

    public Item indOne(Long id) {
        return em.find(Item.class, id);
    }

    public List<Item> findAll() {
        return em.createQuery("select * from Item i", Item.class)
                .getResultList();
    }
}
