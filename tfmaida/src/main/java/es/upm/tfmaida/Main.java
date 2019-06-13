package es.upm.tfmaida;

import static es.upm.tfmaida.Lemmatizer.Lemmatizer;
import static es.upm.tfmaida.Parser.Parser;
import static es.upm.tfmaida.TxtWriter.TXT2RDF;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import opennlp.tools.sentdetect.SentenceDetector;
import opennlp.tools.sentdetect.SentenceDetectorME;
import opennlp.tools.sentdetect.SentenceModel;

/**
 * Ejemplo que pone victor a aida de cómo usar funcionalidad de opennlp.
 * Usa "run file" para probarlo.
 * @author vroddon
 */
public class Main {

    public static void main(String[] args) throws FileNotFoundException, IOException{
        
        //String txt = TxtReader.TXT2String("licenses-en-txt/Creative Commons CC-BY-NC 4.0.txt");
        String home = System.getProperty("user.home");
        //String txt = TxtReader.TXT2String(home+"/NetBeansProjects/tfmaida-master/data/"
        //          + "licenses-en-txt/Creative Commons CC-BY-SA 2.0.txt");
        //String sentences[] = Parser.getSentences(txt);
        //for (String sentence : sentences) {
            //System.out.println(sentence);
        //}
        
        //ArrayList<ArrayList<String>> wordsWithTags = Parser(txt);
        
        //System.out.println("\n" + wordsWithTags + "\n" + wordsWithTags.size());
        //System.out.println("\n\n");
        
        //ArrayList<String> sentence = Lemmatizer.Lemmatizer(wordsWithTags, sentences);
        // for(String s: sentence){
            // System.out.println(Parser(s)+"\n\n");
        // } 
        TXT2RDF(home+"/NetBeansProjects/tfmaida-master/data/licenses-en-txt/Creative Commons CC-BY-SA 2.0.txt");
   }

}