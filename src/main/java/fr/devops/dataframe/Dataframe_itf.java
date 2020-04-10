/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.devops.dataframe;

import fr.devops.operations.Function;

/**
 *
 * @author chouaib
 */
public interface Dataframe_itf{
    
    public int size();   
    public void fetchAll();
    public void fetchFromTo(int i, int j);
    public void head(int nbline) throws Exception;
    public void tail(int nbline) throws Exception;
    public void stats(Function f, String axis);
    
    
}
