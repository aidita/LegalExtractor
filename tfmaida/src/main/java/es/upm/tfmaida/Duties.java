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
public class Duties {
    
    public static boolean hasNotice = false;
    public static boolean hasAttribution = false;
    public static boolean hasShareAlike = false;
    public static boolean hasSourceCode = false;
    
    public static String getDuties(String duty, String sentence){
        
        ArrayList<String> notice = TxtReader.getRegex("../res/notice.txt");
        ArrayList<String> attribution = TxtReader.getRegex("../res/attribution.txt");
        ArrayList<String> shareAlike = TxtReader.getRegex("../res/shareAlike.txt");
        ArrayList<String> sourceCode = TxtReader.getRegex("../res/sourceCode.txt");
        
        if(!hasNotice){
            for(String regex: notice) {
                if(sentence.matches(regex)){
                    duty += "cc:Notice , ";
                    hasNotice = true;
                    break;
                }
            }
        }
        
        if(!hasAttribution){
            for(String regex: attribution) {
                if(sentence.matches(regex)){
                    duty += "cc:Attribution , ";
                    hasAttribution = true;
                    break;
                }
            }
        }
        
        if(!hasShareAlike){
            for(String regex: shareAlike) {
                if(sentence.matches(regex)){
                    duty += "cc:ShareAlike , ";
                    hasShareAlike = true;
                    break;
                }
            }
        }
        
        if(!hasSourceCode){
            for(String regex: sourceCode) {
                if(sentence.matches(regex)){
                    duty += "cc:SourceCode , ";
                    hasSourceCode = true;
                    break;
                }
            }
        }
        
        
        return duty;
    }
    
}
