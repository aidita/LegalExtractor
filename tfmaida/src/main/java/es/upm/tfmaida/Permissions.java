/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.upm.tfmaida;

import java.util.ArrayList;

/**
 *
 * @author aida
 */
public class Permissions {
    
    public static boolean hasReproduction = false;
    public static boolean hasDistribution = false;
    public static boolean hasModify = false; 
    public static boolean hasDerivativeWorks = false; 
    public static boolean hasSell = false;
    public static boolean hasLease = false;
    
    public static String getPermissions(String permission, String sentence) {
        
        ArrayList<String> reproduction = TxtReader.getRegex("../res/Regex/reproduction.txt");
        ArrayList<String> distribution = TxtReader.getRegex("../res/Regex/distribution.txt");
        ArrayList<String> modify = TxtReader.getRegex("../res/Regex/modify.txt");
        ArrayList<String> derivativeWorks = TxtReader.getRegex("../res/Regex/derivativeWorks.txt");
        ArrayList<String> sell = TxtReader.getRegex("../res/Regex/sell.txt");
        ArrayList<String> lease = TxtReader.getRegex("../res/Regex/lease.txt");

        if(!hasReproduction){
            for(String regex: reproduction) {
                if(sentence.matches(regex)){
                    permission += "cc:Reproduction , ";
                    hasReproduction = true;
                    break;
                }
            }
        }
        
        if(!hasDistribution){
            for(String regex: distribution) {
                if(sentence.matches(regex)){
                    permission += "cc:Distribution , ";
                    hasDistribution = true;
                    break;
                }
            }
        }
        
        if(!hasModify){
            for(String regex: modify) {
                if(sentence.matches(regex)){
                    permission += "odrl:modify , ";
                    hasModify = true;
                    break;
                }
            }
        }
        
        if(!hasDerivativeWorks){
            for(String regex: derivativeWorks) {
                if(sentence.matches(regex)){
                    permission += "cc:DerivativeWorks , ";
                    hasDerivativeWorks = true;
                    break;
                }
            }
        }
        
        if(!hasSell){
            for(String regex: sell) {
                if(sentence.matches(regex)){
                    permission += "odrl:sell , ";
                    hasSell = true;
                    break;
                }
            }
        }
        
        if(!hasLease){
            for(String regex: lease) {
                if(sentence.matches(regex)){
                    permission += "odrl:lease , ";
                    hasLease = true;
                    break;
                }
            }
        }
        
        
        return permission;
    }
    
}
