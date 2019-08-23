/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facades;

import dto.CustomerDTO;
import entities.BankCustomer;
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
        BankCustomer c = cf.getAllBankCustomers().get(cf.getAllBankCustomers().size()-1);
        cf.removeCustomer(c);
    }

    @Before
    public void setUp() {
        
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of getFacadeExample method, of class CustomerFacade.
     */
    @Test
    public void testGetFacadeExample() {
        CustomerFacade result = CustomerFacade.getCustomerFacade(emf);
        assertNotNull(result);
    }

    /**
     * Test of getCustomerByID method, of class CustomerFacade.
     */
    @Test
    public void testGetCustomerByID() {

        CustomerDTO cusDTO = cf.getCustomerByID(1);
        assertNotNull(cusDTO);
        assertEquals(2000, cusDTO.getBalance(), 0.01);
        assertEquals("1234", cusDTO.getAccountNumber());
        assertEquals("Amalie Landt", cusDTO.getFullName());
    }

    /**
     * Test of getCustomerByName method, of class CustomerFacade.
     */
    @Test
    public void testGetCustomerByName() {
        List<CustomerDTO> cusDTO = cf.getCustomerByName("Laura");

        assertNotNull(cusDTO);
        assertEquals(1, cusDTO.size());
        assertEquals("4321", cusDTO.get(0).getAccountNumber());
        assertEquals(1000, cusDTO.get(0).getBalance(), 0.01);
    }

    /**
     * Test of addCustomer method, of class CustomerFacade.
     */
    @Test
    public void testAddCustomer() {
       
        int customersbefore = cf.getAllBankCustomers().size();
        BankCustomer bc = new BankCustomer("Sanne", "Hansen", "4532", 1000, 5, "unknown");
        cf.addCustomer(bc);
        int customersafter = cf.getAllBankCustomers().size();
        
        assertTrue(customersbefore < customersafter);
        
    }

    /**
     * Test of getAllBankCustomers method, of class CustomerFacade.
     */
    @Test
    public void testGetAllBankCustomers() {
        List<BankCustomer> bc = cf.getAllBankCustomers();

        assertNotNull(bc);
        assertEquals(5, bc.size());
    }

}
