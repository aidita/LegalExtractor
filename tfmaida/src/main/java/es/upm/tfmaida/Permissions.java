/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.upm.tfmaida;

/**
 *
 * @author aida
 */
public class Permissions {
    public static String getPermissions(String PERMISSION, String sentence) {
        
        // Casos identificados
        if(sentence.contains("you are free to")|| sentence.contains("royalty-free") ||
                sentence.contains("non-exclusive")|| sentence.contains("permit") ||
                sentence.contains("without restriction")|| sentence.contains("without limitation") ||
                sentence.contains("free of charge")|| sentence.contains("requires permission")
                || sentence.contains("requiring permission")){
        
            if(sentence.contains("reproduce") || sentence.contains("copy")){
                if(!PERMISSION.contains("cc:Reproduction")){PERMISSION += "cc:Reproduction , ";}
            }
            if(sentence.contains("distribute") || sentence.contains("distribution")){
                if(!PERMISSION.contains("cc:Distribution")){PERMISSION += "cc:Distribution , ";}
            }
            if(sentence.contains("modify") || sentence.contains("modification")){
                if(!PERMISSION.contains("odrl:modify")){PERMISSION += "odrl:modify , ";}
            }
            if(sentence.contains("remix") || sentence.contains("transform") 
                    || sentence.contains("make derivative works") || sentence.contains("prepare derivative works")){
                if(!PERMISSION.contains("cc:DerivativeWorks")){PERMISSION += "cc:DerivativeWorks , ";}
            }
            if(sentence.contains("sell") || sentence.contains("rent")){
                if(!PERMISSION.contains("odrl:sell")){PERMISSION += "odrl:sell , ";}
            }
            if(sentence.contains("lend") || sentence.contains("lease")){
                if(!PERMISSION.contains("odrl:lease")){PERMISSION += "odrl:lease , ";}
            }
            if(sentence.contains("share") && sentence.contains("noncommercial")){
                if(!PERMISSION.contains("odrl:lease")){PERMISSION += "odrl:lease , ";}
            }
        }
        
        return PERMISSION;
    }
    
}
