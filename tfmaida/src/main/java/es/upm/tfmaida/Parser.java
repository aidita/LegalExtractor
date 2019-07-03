package es.upm.tfmaida;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import opennlp.tools.postag.POSModel;
import opennlp.tools.postag.POSTaggerME;
import opennlp.tools.sentdetect.SentenceDetector;
import opennlp.tools.sentdetect.SentenceDetectorME;
import opennlp.tools.sentdetect.SentenceModel;
import opennlp.tools.tokenize.WhitespaceTokenizer;

/**
 *
 * @author vroddon
 */
public class Parser {

    static POSTaggerME tagger = null;
    static POSModel model = null;
    
    /**
     * Método que devuelve cada una de las frases de un texto separadas por un 
     * salto de línea cada una de ellas.
     * 
     * @param paragraph texto
     * @return frases del texto
     */
    public static String[] getSentences(String paragraph) {
        try {
            final File fsent;
            fsent = new File("../res/en-sent.bin");
            if (!fsent.exists()) {
                System.err.println("I could not find the file " + fsent.getAbsolutePath());
            }
            FileInputStream modelIn = new FileInputStream(fsent);
            final SentenceModel sentenceModel = new SentenceModel(modelIn);
            modelIn.close();
            SentenceDetector sentenceDetector = new SentenceDetectorME(sentenceModel);
            String sentences[] = sentenceDetector.sentDetect(paragraph);
            //for (String sent : sentences) {
                //   System.out.println(sent);
            //}
            return sentences;
        } catch (Exception e) {
            return new String[0];
        }
    }

    public static void initialize(String lexiconFileName) {
        try {
            File fensent = new File(lexiconFileName);
            if (!fensent.exists()) {
                System.err.println("I could not find the file " + fensent.getAbsolutePath());
            }
            FileInputStream modelStream = new FileInputStream(fensent);
            model = new POSModel(modelStream);
            tagger = new POSTaggerME(model);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Método que devuelve el texto etiquetado con el siguiente formato: 
     *  "tag:word tag:word ... tag:word"
     * Estas etiquetas están en formato Penn Treebank (el usado por OpenNLP).
     * 
     * También devuelve una lista con los verbos identificados y las etiquetas 
     * correspondientes para su posible lematización
     *
     * @param text texto de entrada
     * @return Devuelve una lista con el siguiente formato:
     *          [[word1_#sentence,tag1], [word2_#sentence,tag2], ...]
     */
    public static ArrayList<ArrayList<String>> Parser(String text) {
        ArrayList<ArrayList<String>> wordsWithTags = new ArrayList<ArrayList<String>>();
        initialize("../res/en-pos-maxent.bin");
        try {
            if (model != null) {
                POSTaggerME tagger = new POSTaggerME(model);
                if (tagger != null) {
                    String[] sentences = getSentences(text);
                    int numSentence = 0;
                    for (String sentence : sentences) {
                        String whitespaceTokenizerLine[] = WhitespaceTokenizer.INSTANCE.tokenize(sentence);
                        String[] tags = tagger.tag(whitespaceTokenizerLine);
                        for (int i = 0; i < whitespaceTokenizerLine.length; i++) {
                            String word = whitespaceTokenizerLine[i].trim();
                            String tag = tags[i].trim();
                            //System.out.print(tag + ":" + word + "  ");
                            
                            // Si la etiqueta tag coincide con VB o VB* --> 
                                // Metemos la palabra word en una lista junto 
                                // con el número de oración a la que pertenece
                                // y su etiqueta tag correspondiente
                                
                            if(tag.startsWith("VB")){
                                ArrayList<String> e = new ArrayList();
                                e.add(word + "_" + numSentence);
                                e.add(tag);
                                wordsWithTags.add(e);
                            }
                            
                        }
                        numSentence++;
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return wordsWithTags;
    }
    
    
    
}
