package jpabasic;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class JpaMain {

    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction();
        tx.begin();


        try {
            /* create logic */
            Member member = new Member();
            member.setId(1L);
            member.setName("name");

            em.persist(member); // 1차 캐시에 저장
            tx.commit();  // 커밋할 때 쿼리가 수행됨

            /* read logic */
            Member findMember = em.find(Member.class, 1L); // 1차 캐시에서 조회
            System.out.println("findMember.id = "+ findMember.getId());
            System.out.println("findMember.name = "+ findMember.getName());

            /* update logic */
            findMember.setName("changed name"); // 커밋 시점에 변경사항 반영됨


            System.out.println("findMember.id = "+ findMember.getId());
            System.out.println("findMember.name = "+ findMember.getName());

            /* remove logic */
            em.remove(findMember);



            /* 트랜잭션을 지원하는 쓰기 지연 */
            Member member1 = new Member(150L, "A");
            Member member2 = new Member(160L, "B");

            // 영속성 컨텍스트에 저장
            em.persist(member1);
            em.persist(member2);

            tx.commit(); // DB에 쿼리 수행

        } catch (Exception e) {
            tx.rollback();
        } finally {
            em.close();
        }

        emf.close();
    }
}
