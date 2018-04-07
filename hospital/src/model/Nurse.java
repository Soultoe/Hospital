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
public class Nurse extends Employee{
    
    private Department dept;
    private boolean rotation; //rotation only has 2 possible values so I'll be using a Boolean.
    private int wage;
    
    public Nurse()
    {
        super();
        this.rotation = false;
        this.dept = new Dept();
        this.wage = 0;
        
    }
    
    /**
     * @name Overload constructor
     * @param id
     * @param name
     * @param surname
     * @param address
     * @param phone
     * @param dept
     * @param wage
     * @param rotation
     */
    public Nurse(int id, String name, String surname, String address, String phone, Department dept, int wage, boolean rotation)
    {
        super(id,name,surname,address,phone);
        this.rotation = rotation;
        this.dept = dept;
        this.wage = wage;
    }
    
    /**
     * @name setWage
     * @param wage 
     */
    public void setWage(int wage){this.wage = wage;}
    /**
     * @name setRotation
     * @param rotation 
     */
    public void setRotation(boolean rotation){this.rotation = rotation;}
    /**
     * @name setDept
     * @param dept 
     */
    public void setDept(Department dept){this.dept = dept;}
    
}
