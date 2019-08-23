/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author aamandajuhl
 */
public class MakeTestData {

    public static void main(String[] args) {

        EntityManagerFactory emf
                = Persistence.createEntityManagerFactory("pu");
        EntityManager em = emf.createEntityManager();
        
        List<BankCustomer> bc = new ArrayList();
        bc.add(new BankCustomer("Amalie", "Landt", "1234", 2000, 1, "unknown"));
        bc.add(new BankCustomer("Laura", "Saxtrup", "4321", 1000, 2, "unknown"));
        bc.add(new BankCustomer("Jonas", "Haslund", "2341", 2500, 3, "unknown"));
        bc.add(new BankCustomer("Amanda", "Hansen", "2134", 4000, 4, "unknown"));
        
        for (BankCustomer bankCustomer : bc) {
            em.getTransaction().begin();
            em.persist(bankCustomer);
            em.getTransaction().commit();
        }
        em.close();
        
        emf.close();

    }

}
