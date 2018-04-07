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
public class Department {
    
    private final String id;
    private final String name;
    private char building;
    private String director;
    
    /**
     * @name Default constructor
     */
    public Department()
    {
        this.id = this.name = this.director = "";
        this.building = 0;
    }
    
    /**
     * @name Overload constructor
     * @param id
     * @param name
     * @param director
     * @param building 
     */
    public Department(String id, String name, String director, char building)
    {
        this.id = id;
        this.name = name;
        this.director = director;
        this.building = building;
    }
    
    public void setBuilding(char building){this.building = building;}
    public void setDirector(String director){this.director = director;}
    
}
