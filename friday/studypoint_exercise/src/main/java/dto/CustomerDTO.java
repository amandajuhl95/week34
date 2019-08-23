/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dto;

import entities.BankCustomer;

/**
 *
 * @author aamandajuhl
 */
public class CustomerDTO {

    private Long customerID;
    private String fullName;
    private String accountNumber;
    private double balance;

    public CustomerDTO() {
    }
    

    public CustomerDTO(BankCustomer bankcustomer) {
        this.customerID = bankcustomer.getId();
        this.fullName = bankcustomer.getFirstName() + " " + bankcustomer.getLastName();
        this.accountNumber = bankcustomer.getAccountNumber();
        this.balance = bankcustomer.getBalance();
    }

    public Long getCustomerID() {
        return customerID;
    }

    public String getFullName() {
        return fullName;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public double getBalance() {
        return balance;
    }

}
