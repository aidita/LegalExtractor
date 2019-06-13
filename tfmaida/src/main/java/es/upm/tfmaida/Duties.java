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
public class Duties {
    
    public static String getDuties(String DUTY, String sentence){
        
        if (sentence.contains("provide a link to the license") || sentence.contains("notice")) {
            if(!DUTY.contains("cc:Notice")){DUTY += "cc:Notice , ";}
        }
        if(sentence.contains("give appropriate credit") || sentence.contains("attribution")) {
            if(!DUTY.contains("cc:Attribution")){DUTY += "cc:Attribution , ";}
        }
        if((sentence.contains("originator") && sentence.contains("identify")) ||
                (sentence.contains("copyright") && sentence.contains("reproduce")) ||
                (sentence.contains("sharealike"))){
            if(!DUTY.contains("cc:ShareAlike")){DUTY += "cc:ShareAlike , ";}
        }
        if(sentence.contains("provide a machine-readable copy of the source code")) {
            if(!DUTY.contains("cc:SourceCode")){DUTY += "cc:SourceCode , ";}
        }
        
        return DUTY;
    }
    
}
