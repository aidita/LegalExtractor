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
    public static String TXT2RDF(String ruta) throws IOException {
        String legalCode="";
        try {
            legalCode = TxtReader.TXT2String(ruta);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(TxtWriter.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return Text2RDF(legalCode);
    }
    
    public static String Text2RDF(String legalCode) throws FileNotFoundException { 
        
        String permission = "";
        String duty = "";
        String prohibition = "";
        
        String tag = TxtReader.getLabel(legalCode);
        tag = tag.toLowerCase();
        String version = "1.0";
        int pos = 0;
        String[]findVersion = tag.split(" ");
        boolean encontrado=false;
        for(String v:findVersion){
            if(v.matches("\\d\\.{0,1}\\d{0,1}") && !encontrado){
                version = v;
                encontrado=true;
                break;
            }
            if(!v.matches("v\\.{0,1}|version")){
                pos++;
            }
        }
        String label = "";
        for(int i = 0; i<pos ; i++){
            label = label.concat(findVersion[i]) + " ";
        }label = label.trim();
        
        tag = label.concat(version);
        tag = tag.replaceAll(" ", "");
        

        try{
        
        String sentences[] = Parser.getSentences(legalCode);
        ArrayList<ArrayList<String>> wordsWithTags = Parser(legalCode);
        ArrayList<String> sentence = Lemmatizer.Lemmatizer(wordsWithTags, sentences);
        
        for (String s: sentence){
            s = s.toLowerCase();
            s = s.replaceAll("\\n|\\s+"," ");
            
            permission = Permissions.getPermissions(permission, s);
            duty = Duties.getDuties(duty, s);
            prohibition = Prohibitions.getProhibitions(prohibition, s);
            
        }
        
        if (duty.length()>2) {
            duty = duty.substring(0, duty.length()-2);
        }
        if(permission.length()>2){
            permission = permission.substring(0, permission.length()-2);
        }
        
        String template = null;
        if(prohibition.length()<2){ 
            
            template = templateWithoutProhibitions(tag, version, label, legalCode, permission, duty);
            
        } else { 
            
            template = templateWithProhibitions(tag, version, label, legalCode, permission, duty, prohibition);
        }

        // If the file doesn't exists, create and write to it
	// If the file exists, truncate (remove all content) and write to it
        String home = System.getProperty("user.home");
        try (FileWriter writer = new FileWriter(home+"/Desktop/license.rdf");
             BufferedWriter bw = new BufferedWriter(writer)) {

            bw.write(template);

        } catch (IOException e) {
            System.err.format("IOException: %s%n", e);
        }
        
        return template;
        
    } catch(Exception exc) {
        return exc.getMessage();
    }
       
    }
    
    public static String templateWithoutProhibitions(String tag, String version, String label, String legalCode,
            String permission, String duty){
        return "@prefix cc:      <http://creativecommons.org/ns#> .\n" +
"@prefix dct:     <http://purl.org/dc/terms/> .\n" +
"@prefix rdf:     <http://www.w3.org/1999/02/22-rdf-syntax-ns#> .\n" +
"@prefix rdfs:    <http://www.w3.org/2000/01/rdf-schema#> .\n" +
"@prefix odrl:    <http://www.w3.org/ns/odrl/2/> .\n" +
"\n" +
"<http://purl.org/NET/rdflicense/"+tag+">\n" +
"      a       odrl:Policy ;\n" +
"      rdfs:label \""+label+"\" ;\n" +
"      dct:hasVersion \""+version+"\" ;\n" +
"      dct:language <http://www.lexvo.org/page/iso639-3/eng> ;  \n" +
"      odrl:permission\n" +
"              [ odrl:action " + permission + " ;\n" +
"                odrl:duty\n" +
"                        [ odrl:action " + duty + "\n" +
"                        ]\n" +
"              ] ;\n" +
"cc:legalcode \"\"\"\n" +
""+legalCode+"\n" +
"\"\"\"@en .";
    
    }
    
    
    public static String templateWithProhibitions(String tag, String version, String label, String legalCode,
            String permission, String duty, String prohibition){
        return "@prefix cc:      <http://creativecommons.org/ns#> .\n" +
"@prefix dct:     <http://purl.org/dc/terms/> .\n" +
"@prefix rdf:     <http://www.w3.org/1999/02/22-rdf-syntax-ns#> .\n" +
"@prefix rdfs:    <http://www.w3.org/2000/01/rdf-schema#> .\n" +
"@prefix odrl:    <http://www.w3.org/ns/odrl/2/> .\n" +
"\n" +
"<http://purl.org/NET/rdflicense/"+tag+">\n" +
"      a       odrl:Policy ;\n" +
"      rdfs:label \""+label+"\" ;\n" +
"      dct:hasVersion \""+version+"\" ;\n" +
"      dct:language <http://www.lexvo.org/page/iso639-3/eng> ;  \n" +
"      odrl:permission\n" +
"              [ odrl:action " + permission + " ;\n" +
"                odrl:duty\n" +
"                        [ odrl:action " + duty + "\n" +
"                        ]\n" +
"              ] ;\n" +
"      odrl:prohibition\n" +
"              [ odrl:action " + prohibition + "\n" +
"              ] ;\n" +
"cc:legalcode \"\"\"\n" +
""+legalCode+"\n" +
"\"\"\"@en .";
    }

}
