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
public class Doctor extends Employee{
    
    private final String specialty;
    
    /**
     * @name Default constructor
     */
    public Doctor()
    {
        super();
        this.specialty = "";
    }
    
    /**
     * @name Overload constructor
     * @param id
     * @param name
     * @param surname
     * @param address
     * @param phone
     * @param specialty 
     */
    public Doctor(int id, String name, String surname, String address, String phone, String specialty)
    {
        super(id,name,surname,address,phone);
        this.specialty = specialty;
    }
    
}
