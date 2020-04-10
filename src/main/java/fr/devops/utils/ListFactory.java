/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.devops.utils;

import java.util.ArrayList;

/**
 *
 * @author chouaib
 */
public class ListFactory {
    
        public static ArrayList<?> createList(String type) throws Exception{
        ArrayList list;
        switch(type){
            case "Integer":
                list =  new ArrayList<Integer>();
                break;
            case "Boolean":
                list =  new ArrayList<Boolean>();
                break;
            case "Double":
            case "Float":
                list =  new ArrayList<Double>();
                break;
            case "String":
                list =  new ArrayList<String>();
                break;
            case "Character":
                list =  new ArrayList<Character>();
                break;
            default:
                throw new Exception("Invalide type");
             
        }
        return list;
    }
        
}
