package es.upm.tfmaida;

import static es.upm.tfmaida.TxtWriter.TXT2RDF;
import java.io.FileNotFoundException;
import java.io.IOException;


/**
 * Ejemplo que pone victor a aida de cómo usar funcionalidad de opennlp.
 * Usa "run file" para probarlo.
 * @author vroddon
 */
public class Main {

    public static void main(String[] args) throws FileNotFoundException, IOException{
        
        TXT2RDF("/Users/aida/NetBeansProjects/tfmaida-master/data/licenses-en-txt/5.txt");
        
   }

}