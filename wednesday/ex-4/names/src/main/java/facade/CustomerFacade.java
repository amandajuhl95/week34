/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facade;

import entity.Customer;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

/**
 *
 * @author aamandajuhl
 */
public class CustomerFacade {

    private static EntityManagerFactory emf;
    private static CustomerFacade instance;

    private CustomerFacade() {
    }

    public static CustomerFacade getCustomerFacade(EntityManagerFactory _emf) {
        if (instance == null) {
            emf = _emf;
            instance = new CustomerFacade();
        }
        return instance;
    }

    public Customer findById(long id) {
        EntityManager em = emf.createEntityManager();
        Customer c = em.find(Customer.class, id);
        return c;

    }

    public List<Customer> findByLastName(String name) {
        EntityManager em = emf.createEntityManager();
        TypedQuery<Customer> query = em.createQuery("SELECT c FROM Customer c WHERE c.lastname = :lastname", Customer.class);
        query.setParameter("lastname", name);
        return query.getResultList();

    }

    public Long getNumberOfCustomer() {

        EntityManager em = emf.createEntityManager();

        Query q = em.createQuery("SELECT COUNT(c) FROM Customer c");
        Long count = (Long) q.getSingleResult();
        return count;

    }

    public List<Customer> getAllCustomers() {
        EntityManager em = emf.createEntityManager();

        TypedQuery<Customer> query = em.createQuery("SELECT c FROM Customer c", Customer.class);
        return query.getResultList();
    }

    public Customer addCustomer(String fname, String lname) {
        Customer c = new Customer(fname, lname);
        EntityManager em = emf.createEntityManager();

        try {
            em.getTransaction().begin();
            em.persist(c);
            em.getTransaction().commit();
            return c;
        } finally {
            em.close();
        }
    }

    public void removeCustomer(Customer customer) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            Customer cus = em.merge(customer);
            em.remove(cus);
            em.getTransaction().commit();
        } finally {
            em.close();
        }

    }

}
