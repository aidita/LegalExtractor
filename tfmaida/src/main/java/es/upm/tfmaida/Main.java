package es.upm.tfmaida;

import static es.upm.tfmaida.TxtWriter.TXT2RDF;
import java.io.FileNotFoundException;
import java.io.IOException;


/**
 * 
 * @author aida
 */
public class Main {

    public static void main(String[] args) throws FileNotFoundException, IOException{
        
        TXT2RDF("/Users/aida/NetBeansProjects/tfmaida-master/test/licenses-en-txt/9.txt");
        
   }

}