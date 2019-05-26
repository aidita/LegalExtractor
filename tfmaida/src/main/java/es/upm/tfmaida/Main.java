package es.upm.tfmaida;

import static es.upm.tfmaida.Lemmatizer.Lemmatizer;
import static es.upm.tfmaida.Parser.Parser;
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
        
        String txt = TxtReader.TXT2String("licenses-en-txt/1.txt");
        
        String sentences[] = Parser.getSentences(txt,true);
        // for (String sentence : sentences) {
            //System.out.println(sentence);
        //}
        
        ArrayList<ArrayList<String>> wordsWithTags = Parser(txt);
        
        System.out.println("\n" + wordsWithTags + "\n" + wordsWithTags.size());
        System.out.println("\n\n");
        
        Lemmatizer.Lemmatizer(wordsWithTags, sentences);
        
        
    }


}