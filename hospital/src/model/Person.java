/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author romain
 */
public abstract class Person {
    
    private final int idNumber;
    private final String name;
    private final String surname;
    private String address;
    private String phone;
    
    /**
     * @name Default constructor
     */
    public Person()
    {
        idNumber = 0;
        name = surname = address = phone = "";
    }
    
    /**
     * @name overload constructor
     * @param id
     * @param name
     * @param surname
     * @param address
     * @param phone 
     */
    public Person(int id, String name, String surname, String address, String phone)
    {
        this.idNumber = id;
        this.name = name ;
        this.surname = surname;
        this.address = address;
        this.phone = phone;
    }
    
    /**
     * @ setAddress
     * @param address 
     */
    public void setAddress(String address){this.address = address;}
    /**
     * @name setPhone
     * @param phone 
     */
    public void setPhone(String phone){this.phone = phone;}
    
}
