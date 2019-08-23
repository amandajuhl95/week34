/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facade;

import entity.Customer;
import java.util.List;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author aamandajuhl
 */
public class CustomerFacadeTest {

    static EntityManagerFactory emf;
    static CustomerFacade cf;

    public CustomerFacadeTest() {
    }

    @BeforeClass
    public static void setUpClass() {
        emf = Persistence.createEntityManagerFactory("pu");
        cf = CustomerFacade.getCustomerFacade(emf);
    }

    @AfterClass
    public static void tearDownClass() {
        Customer c = cf.getAllCustomers().get(cf.getAllCustomers().size() - 1);
        cf.removeCustomer(c);
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of getCustomerFacade method, of class CustomerFacade.
     */
    @Test
    public void testGetCustomerFacade() {
        CustomerFacade result = CustomerFacade.getCustomerFacade(emf);
        assertNotNull(result);
    }

    /**
     * Test of findById method, of class CustomerFacade.
     */
    @Test
    public void testFindById() {

        Customer cus = cf.findById(1);
        assertNotNull(cus);
        assertEquals("Hans", cus.getFirstname());
        assertEquals("Hansen", cus.getLastname());
    }

    /**
     * Test of findByLastName method, of class CustomerFacade.
     */
    @Test
    public void testFindByLastName() {
        List<Customer> cus = cf.findByLastName("Larsen");

        assertNotNull(cus);
        assertEquals(1, cus.size());
        assertEquals("Lars", cus.get(0).getFirstname());
    }

    /**
     * Test of getNumberOfCustomer method, of class CustomerFacade.
     */
    @Test
    public void testGetNumberOfCustomer() {
        long cus = cf.getNumberOfCustomer();
        assertEquals(2, cus);

    }

    /**
     * Test of getAllCustomers method, of class CustomerFacade.
     */
    @Test
    public void testGetAllCustomers() {
       List<Customer> cus = cf.getAllCustomers();
       
        assertNotNull(cus);
        assertEquals(3, cus.size());
    }

    /**
     * Test of addCustomer method, of class CustomerFacade.
     */
    @Test
    public void testAddCustomer() {
        int customersbefore = cf.getAllCustomers().size();
        cf.addCustomer("Claus", "Clausen");
        int customersafter = cf.getAllCustomers().size();
        
        assertTrue(customersbefore < customersafter);

    }

}
