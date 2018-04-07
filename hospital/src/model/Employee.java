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
public class Employee extends Person {
    
    public Employee()
    {
        super();
    }
    
    public Employee(int id, String name, String surname, String address, String phone)
    {
        super(id,name,surname,address,phone);
    }
    
}
