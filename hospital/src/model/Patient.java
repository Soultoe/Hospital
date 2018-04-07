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
public class Patient extends Person {
    
    private final int socialSecurityNumber;
    
    /**
     *@name Default constructor
     */
    public Patient()
    {
        super();
        this.socialSecurityNumber = 0;
    }
    
    /**
    * @name Overload constructor
    * @param id
    * @param name
    * @param surname
    * @param address
    * @param phone 
    * @param socialSecurityNumber
    */
    public Patient(int id, String name, String surname, String address, String phone, int socialSecurityNumber)
    {
        super(id,name,surname,address,phone);
        this.socialSecurityNumber = socialSecurityNumber;
    }
    
    public void treatedBy()
    {
        
    }
    
    public void hospitalizedAt()
    {
        
    }
    
}
