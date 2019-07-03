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
public class Prohibitions {
    
    public static boolean hasCommercial = false;
    
    
    public static String getProhibitions(String prohibition, String sentence) {
        
        ArrayList<String> nonCommercial = TxtReader.getRegex("../res/nonCommercial.txt");

        if(!hasCommercial){
            for(String regex: nonCommercial) {
                if(sentence.matches(regex)){
                    prohibition = "cc:CommercialUse";
                    hasCommercial = true;
                    break;
                }
            }
        }
        
        
        return prohibition;
    }
    
}
