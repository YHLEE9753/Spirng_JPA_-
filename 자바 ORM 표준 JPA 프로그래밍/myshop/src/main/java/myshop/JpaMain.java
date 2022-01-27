package myshop;

import myshop.domain.Member;
import myshop.domain.Order;
import myshop.domain.OrderStatus;

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

        try{
            Member member = new Member();
            member.setName("yong");
            member.setCity("Incheon");
            member.setStreet("Nansuck");
            member.setZipcode("10001");
            em.persist(member);

            Order order = new Order();
            order.setStatus(OrderStatus.CANCEL);
            order.setMember(member);
            em.persist(order);

//            order.setMember(member);
//            member.getOrders().add(order);

            em.flush();
            em.clear();
//
            Member member1 = em.find(Member.class, member.getId());
            System.out.println("===================");
            System.out.println("member1.getCity() = " + member1.getCity());

            tx.commit();
        }catch(Exception e){
            tx.rollback();
        }finally{
            em.close();
        }
        emf.close();
    }


}
