package facades;

import dto.CustomerDTO;
import entities.BankCustomer;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

/**
 *
 * Rename Class to a relevant name Add add relevant facade methods
 */
public class CustomerFacade {

    private static CustomerFacade instance;
    private static EntityManagerFactory emf = Persistence.createEntityManagerFactory("pu");

    //Private Constructor to ensure Singleton
    private CustomerFacade() {
    }

    /**
     *
     * @param _emf
     * @return an instance of this facade class.
     */
    public static CustomerFacade getCustomerFacade(EntityManagerFactory _emf) {
        if (instance == null) {
            emf = _emf;
            instance = new CustomerFacade();
        }
        return instance;
    }

    private EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public CustomerDTO getCustomerByID(long id) {
        EntityManager em = getEntityManager();
        BankCustomer bc = em.find(BankCustomer.class, id);
        CustomerDTO cusDTO = new CustomerDTO(bc);

        return cusDTO;
    }

    public List<CustomerDTO> getCustomerByName(String name) {
        EntityManager em = getEntityManager();
        TypedQuery<BankCustomer> query = em.createQuery("SELECT bc FROM BankCustomer bc WHERE bc.lastName = :name OR bc.firstName = :name", BankCustomer.class);
        query.setParameter("name", name);
        List<CustomerDTO> cusDTO = new ArrayList();

        for (BankCustomer bc : query.getResultList()) {
            cusDTO.add(new CustomerDTO(bc));
        }

        return cusDTO;
    }

    public BankCustomer addCustomer(BankCustomer customer) {
        EntityManager em = getEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(customer);
            em.getTransaction().commit();
            return customer;
        } finally {
            em.close();
        }

    }

    public void removeCustomer(BankCustomer customer) {
        EntityManager em = getEntityManager();
        try {
            em.getTransaction().begin();
            BankCustomer cus = em.merge(customer);
            em.remove(cus);
            em.getTransaction().commit();
        } finally {
            em.close();
        }

    }

    public List<BankCustomer> getAllBankCustomers() {
        EntityManager em = getEntityManager();
        TypedQuery<BankCustomer> query = em.createQuery("SELECT bc FROM BankCustomer bc", BankCustomer.class);
        return query.getResultList();
    }

}
