/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.upm.tfmaida;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import opennlp.tools.lemmatizer.DictionaryLemmatizer;

/**
 *
 * @author aida
 */
public class Lemmatizer {
    
    /**
     * Método principal que lematiza las frases del texto (licencia) e imprime como resultado 
     * el conjunto de frases que contienen verbos clave
     * 
     * @param wordsWithTags lista de listas con el siguiente formato: [[word1_#sentence, tag1], 
     *                      [word2_#sentence, tag2], [word3_#sentence, tag3], ...]
     * @param sentences lista que contiene todas las frases de la licencia
     * @throws FileNotFoundException
     * @throws IOException 
     */
    public static ArrayList<String> Lemmatizer(ArrayList<ArrayList<String>> wordsWithTags, String[] sentences) throws FileNotFoundException, IOException{
        ArrayList<String> sentences_final = null;
        
        if ((wordsWithTags != null) && !wordsWithTags.isEmpty()){
            
            ArrayList<String> words = new ArrayList();
            ArrayList<String> tags = new ArrayList();
            String wordWithNumber = null;
            int pos = -1;
            String word = null;
            
            // Creo una lista con words (sin #sentence) y otra con tags
            // Será la entrada del lemmatizer
            for(ArrayList<String> el : wordsWithTags){
                wordWithNumber = el.get(0);
                pos = wordWithNumber.lastIndexOf("_");
                word = wordWithNumber.substring(0, pos);
                
                words.add(word);
                tags.add(el.get(1));
            }
            
            // Cargamos el diccionario
            InputStream dictLemmatizer = new FileInputStream("../res/en-lemmatizer.txt");
            
            // Cargamos el lematizador (con el diccionario)
            DictionaryLemmatizer lemmatizer = new DictionaryLemmatizer(dictLemmatizer);
 
            // Buscamos el lema de cada verbo. Se obtiene el resultado con el siguiente 
            // formato: [[lemma],[lemma],[lemma], ...]
            List<List<String>> lem = lemmatizer.lemmatize(words, tags);
 
            // Probamos el resultado: printing the results
            // System.out.println("\nPrinting lemmas for the given sentence...");
            // System.out.println("WORD - POSTAG : LEMMA");
            // for(int i=0;i< words.size();i++){
                // System.out.println(words.get(i)+" - "+tags.get(i)+" : "+lem.get(i));
            // }
            
            // Obtenemos las frases con verbos clave
            sentences_final = isKeyVerb(wordsWithTags, lem, sentences); 
        }
        return sentences_final;
    }
    
    
    /**
     * Método que obtiene todas las frases que contienen alguno de los verbos clave para identificar 
     * los principales eventos que contienen las licencias.
     * 
     * @param wordsWithTags lista de listas con el siguiente formato: [[word1_#sentence, tag1], 
     *                      [word2_#sentence, tag2], [word3_#sentence, tag3], ...]
     * @param lemmas lista de listas con el siguiente formato [[lemmma1], [lemma2], [lemma3], ...]
     * @param sent lista que contiene todas las frases de la licencia
     * @return lista de frases que contienen algún verbo de la lista de verbos clave
     * @throws FileNotFoundException 
     */
    public static ArrayList<String> isKeyVerb (ArrayList<ArrayList<String>> wordsWithTags, List<List<String>> lemmas,
            String[] sent) throws FileNotFoundException{
        
        ArrayList<String> sentences = new ArrayList();
        String wordComp;
        int position;
        String numSentence; 
        ArrayList<Integer> positions = new ArrayList();
        
        // Hago una lista con los verbos clave
        ArrayList<String> keyVerbs = TxtReader.TXT2List("key-verbs-en.txt");
        
        // Escogemos las frases que contienen verbos clave
        if (wordsWithTags.size() == lemmas.size()){
            int pos = 0;
            for (List<String> el : lemmas){
                if(keyVerbs.contains(el.get(0))){
                   wordComp = wordsWithTags.get(pos).get(0);
                   position = wordComp.lastIndexOf("_");
                   numSentence = wordComp.substring(position+1);
                   positions.add(Integer.parseInt(numSentence));
                } pos++;
            }
        }        
        
        // Añadimos las frases que contienen algún verbo clave a la lista de retorno
        // Imprimimos sus valores para comprobar que se hace correctamente
        sentences.add(sent[positions.get(0)]);
        //System.out.println("\n#frase: "+ positions.get(0) + " --> " + sent[positions.get(0)]+ "\n");
        for (int i = 1; i<positions.size();i++) {
            int el = positions.get(i);
            if (!positions.get(i-1).equals(el)) {
                sentences.add(sent[el]);
                //System.out.println("#frase: "+ el + " --> " + sent[el]+ "\n");
            }
        }
        
        // Probamos que las detecta correctamente: 
        // for (String s: sentences) {
            // System.out.println(s + "\n");
        // }
        
        return sentences;
    }    
    
}
