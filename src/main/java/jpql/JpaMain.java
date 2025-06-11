package jpql;

import jakarta.persistence.*;

import java.util.List;

public class JpaMain {

    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();

        try {
            Member member = new Member();
            member.setUsername("member1");
            em.persist(member);

//            TypedQuery<Member> query = em.createQuery("SELECT m FROM Member m where m.username = :username", Member.class);
            // Query query2 = em.createQuery("SELECT m.username, m.age FROM Member m");
            //List<Member> resultList = query.getResultList();
//            query.setParameter("username", "member1");
//            Member singleResult = query.getSingleResult();
//            System.out.println("singleResult = " + singleResult);

//            for (Member member1 : resultList) {
//                System.out.println("member1 = " + member1);
//            }

            Member singleResult = em.createQuery("SELECT m FROM Member m where m.username = :username", Member.class)
                    .setParameter("username", "member1")
                    .getSingleResult();

            System.out.println("singleResult = " + singleResult.getUsername());

            tx.commit();
        } catch (Exception e) {
            tx.rollback();
            e.printStackTrace();
        } finally {
            em.close();
        }

        emf.close();
    }
}
