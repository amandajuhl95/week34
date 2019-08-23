/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import facade.CustomerFacade;
import java.util.List;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author aamandajuhl
 */
public class EntityTested {

    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("pu");
        CustomerFacade cf = CustomerFacade.getCustomerFacade(emf);
        //cf.addCustomer("Hans", "Hansen");
        //cf.addCustomer("Lars", "Larsen");

        List<Customer> customers = cf.getAllCustomers();
        for (Customer customer : customers) {
            System.out.println(customer.getFirstname() + " " + customer.getLastname());
        }

        Long count = cf.getNumberOfCustomer();
        System.out.println("Number of customers: " + count);
        
        System.out.println(cf.findByLastName("Hansen"));
        System.out.println(cf.findById(2));

    }
}
