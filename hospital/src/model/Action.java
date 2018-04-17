/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import hospital.*;

/**
 *
 * @author romain
 */
public abstract class Action {
    
    protected Connexion con;
    
    public Action(Connexion con)
    {
        this.con = con;
    }
    
}
