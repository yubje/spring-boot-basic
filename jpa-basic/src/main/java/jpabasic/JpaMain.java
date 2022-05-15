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

            em.persist(member);
            tx.commit();

            /* read logic */
            Member findMember = em.find(Member.class, 1L);
            System.out.println("findMember.id = "+ findMember.getId());
            System.out.println("findMember.name = "+ findMember.getName());

            /* update logic */
            findMember.setName("changed name");

            
            System.out.println("findMember.id = "+ findMember.getId());
            System.out.println("findMember.name = "+ findMember.getName());

            /* remove logic */
            em.remove(findMember);

        } catch (Exception e) {
            tx.rollback();
        } finally {
            em.close();
        }

        emf.close();
    }
}
