/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.upm.tfmaida;

import static es.upm.tfmaida.Parser.Parser;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author aida
 */
public class TxtWriter {
    
    /**
     * 
     * @param ruta 
     */
    public static void TXT2RDF(String ruta) throws IOException {
        String PERMISSION = "";
        String DUTY = "";
        String PROHIBITION = "";
        String TEXT="";
        
        try {
            TEXT = TxtReader.TXT2String(ruta);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(TxtWriter.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        if(ruta.contains("/")){
            int pos=ruta.lastIndexOf("/");
            ruta = ruta.substring(pos+1);
        } 
        if(ruta.contains(".txt")){
            ruta = ruta.substring(0, ruta.length()-4);
        }
        
        String TAG = ruta.toLowerCase();
        TAG = TAG.replaceAll("\\s+","");
        
        int pos = ruta.lastIndexOf(" ");
        String LABEL = "label";
        String VERSION = "1.0";
        if (pos!=-1)
        {
        LABEL = ruta.substring(0, pos);
        VERSION = ruta.substring(pos+1);
        
        boolean hasVersion = false;
        
        if(VERSION.contains(".")){
            int pos1 = VERSION.lastIndexOf(".");
            String regex = "\\d+";
            hasVersion = VERSION.substring(0,pos1).matches(regex);
            if(hasVersion){
                hasVersion = VERSION.substring(pos1+1).matches(regex);
            }
        }
        if(!hasVersion){
            LABEL = LABEL + " " + VERSION;
            VERSION = "";
        }
                }

        
        String sentences[] = Parser.getSentences(TEXT);
        ArrayList<ArrayList<String>> wordsWithTags = Parser(TEXT);
        ArrayList<String> sentence = Lemmatizer.Lemmatizer(wordsWithTags, sentences);
        
        for (String s: sentence){
            s = s.toLowerCase();
            
            PERMISSION = Permissions.getPermissions(PERMISSION, s);
            if (s.contains("must") || s.contains("shall")){
                DUTY = Duties.getDuties(DUTY, s);
            }
            if(s.contains("noncommercial") || s.contains("non-commercial")){
                PROHIBITION = "cc:NonCommercial";
            }
        }
        
        if (DUTY.length()>2) {
            DUTY = DUTY.substring(0, DUTY.length()-2);
        }
        
        if(PERMISSION.length()>2){
            PERMISSION = PERMISSION.substring(0, PERMISSION.length()-2);
        }
        
        String plantilla = null;
        if(PROHIBITION.length()<2){ plantilla = 
                
"@prefix cc:      <http://creativecommons.org/ns#> .\n" +
"@prefix dct:     <http://purl.org/dc/terms/> .\n" +
"@prefix rdf:     <http://www.w3.org/1999/02/22-rdf-syntax-ns#> .\n" +
"@prefix rdfs:    <http://www.w3.org/2000/01/rdf-schema#> .\n" +
"@prefix odrl:    <http://www.w3.org/ns/odrl/2/> .\n" +
"\n" +
"<http://purl.org/NET/rdflicense/"+TAG+">\n" +
"      a       odrl:Policy ;\n" +
"      rdfs:label \""+LABEL+"\" ;\n" +
"      dct:hasVersion \""+VERSION+"\" ;\n" +
"      dct:language <http://www.lexvo.org/page/iso639-3/eng> ;  \n" +
"      odrl:permission\n" +
"              [ odrl:action " + PERMISSION + " ;\n" +
"                odrl:duty\n" +
"                        [ odrl:action " + DUTY + "\n" +
"                        ]\n" +
"              ] ;\n" +
"cc:legalcode \"\"\"\n" +
""+TEXT+"\n" +
"\"\"\"@en .";
        
        } else { plantilla =
                
"@prefix cc:      <http://creativecommons.org/ns#> .\n" +
"@prefix dct:     <http://purl.org/dc/terms/> .\n" +
"@prefix rdf:     <http://www.w3.org/1999/02/22-rdf-syntax-ns#> .\n" +
"@prefix rdfs:    <http://www.w3.org/2000/01/rdf-schema#> .\n" +
"@prefix odrl:    <http://www.w3.org/ns/odrl/2/> .\n" +
"\n" +
"<http://purl.org/NET/rdflicense/"+TAG+">\n" +
"      a       odrl:Policy ;\n" +
"      rdfs:label \""+LABEL+"\" ;\n" +
"      dct:hasVersion \""+VERSION+"\" ;\n" +
"      dct:language <http://www.lexvo.org/page/iso639-3/eng> ;  \n" +
"      odrl:permission\n" +
"              [ odrl:action " + PERMISSION + " ;\n" +
"                odrl:duty\n" +
"                        [ odrl:action " + DUTY + "\n" +
"                        ]\n" +
"              ] ;\n" +
"      odrl:prohibition\n" +
"              [ odrl:action " + PROHIBITION + "\n" +
"              ] ;\n" +
"cc:legalcode \"\"\"\n" +
""+TEXT+"\n" +
"\"\"\"@en .";
        
        }

        // If the file doesn't exists, create and write to it
		// If the file exists, truncate (remove all content) and write to it
        String home = System.getProperty("user.home");
        try (FileWriter writer = new FileWriter(home+"/Desktop/license.rdf");
             BufferedWriter bw = new BufferedWriter(writer)) {

            bw.write(plantilla);

        } catch (IOException e) {
            System.err.format("IOException: %s%n", e);
        }

    }

}
