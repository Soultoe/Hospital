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
public class Room {
    
    private String deptId;
    private final int roomNumber;
    private int warden;
    private final int bedNumber;
    
    /**
     * @name Default constructor
     */
    public Room()
    {
        this.deptId = "";
        this.roomNumber = this.warden = this.bedNumber = 0;
    }
    
    /**
     * @name Overload constructor
     * @param deptId
     * @param roomNumber
     * @param warden
     * @param bedNumber 
     */
    public Room(String deptId, int roomNumber, int warden, int bedNumber)
    {
        this.deptId = deptId;
        this.roomNumber = roomNumber;
        this.warden = warden;
        this.bedNumber = bedNumber;
    }
    
    /**
     * @name setDeptId
     * @param deptId 
     */
    public void setDeptId(String deptId){this.deptId = deptId;}
    /**
     * @name setWarden
     * @param warden 
     */
    public void setWarden(int warden){this.warden = warden;}
    
}
