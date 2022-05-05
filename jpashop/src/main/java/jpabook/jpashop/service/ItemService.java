package jpabook.jpashop.service;

import jpabook.jpashop.domain.item.Book;
import jpabook.jpashop.domain.item.Item;
import jpabook.jpashop.repository.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ItemService {

    private final ItemRepository itemRepository;

    /**
     *  상품 저장
     */
    @Transactional
    public void saveItem(Item item) {
        itemRepository.save(item);
    }

    /**
     *  상품 수정 - 변경 감지 기능 사용 (merge보다 안전)
     */
    @Transactional
    public void updateItem(Long itemId, int price, String name, int stockQuantity) { // param : 파라미터로 넘어온 준영속 상태의 엔티티
        // 영속 상태
        Item findItem = itemRepository.findOne(itemId); // 같은 엔티티 조회

        // 데이터 수정
        findItem.setPrice(price);
        findItem.setName(name);
        findItem.setStockQuantity(stockQuantity);

    }

    /**
     * 상품 전체 찾기
     */
    public List<Item> finditems() {
        return itemRepository.findAll();
    }

    /**
     * 상품 단건 찾기
     */
    public Item findOne(Long itemId) {
        return itemRepository.findOne(itemId);
    }
}
