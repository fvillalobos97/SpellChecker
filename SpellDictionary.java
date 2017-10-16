import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.*;
import java.io.*;
/**
* author: Felicia Villalobos
* version: 4/19/2017
*/

public class SpellDictionary{
  private HashSet<String> dictHash;
  private HashSet<String> spellingSuggestions;
  //new constructor for SpellDictionary
  public SpellDictionary(String fname){
    dictHash = new HashSet<String>();
    buildDictionary(fname);
  }

  /**
  * buildDictionary()
  * method that uses a file to build a dictionary of type HashSet
  * @param String fname
  */

  private void buildDictionary(String fname){
    try{
    BufferedReader buildD = new BufferedReader(new FileReader(fname));
    String wordsforDict = buildD.readLine();
    while(wordsforDict != null){
        dictHash.add(wordsforDict);
        wordsforDict = buildD.readLine();
    }
  } catch(FileNotFoundException exception){
    System.err.println("File not found!");
    System.exit(-1);
  }catch(IOException e){
    System.err.println("IO exception!");
    System.exit(-1);
  }
}

/**
*spellCheck()
* a method that holds all of the smaller checking methods (delete, etc)
*@param String checkingWord
*/

  public HashSet<String> spellCheck(String checkingWord){
    spellingSuggestions = new HashSet<String>();
    if(dictHash.contains(checkingWord) == false){
      deletion(checkingWord);
      insert(checkingWord);
      substitutions(checkingWord);
      splits(checkingWord);
    }
    else{
  //    System.out.println("No misspelling");
    }
  //    System.out.println("Misspelling, here are some suggestions:");
      System.out.println(spellingSuggestions);
    return spellingSuggestions;
  }
  /**
  *deletion()
  * a method that checks if a word is in the dictionary by deleting
  * one letter at a time
  * @param String word
  */

  public void deletion(String word){
    for(int j = 0; j < word.length(); j++){
      String first = word.substring(0,j); //now we have word w/o one of the letters
      String option = word.substring(j+1);
      String suggestion = first.concat(option);
      if(dictHash.contains(suggestion) == true){
        spellingSuggestions.add(suggestion);
  //      System.out.println("spellingSuggestions Delete= " + spellingSuggestions);
      }
      else{
        //System.out.println("Sorry, not a real word!" + suggestion);
      }
    }//close for loop
  }

  /**
  * insert()
  * a method that checks if a word is in the dicitionary by inserting
  * every letter in the alphabet in every spot of the word
  * @param String word
  */

  public void insert(String word){
  //  System.out.println("inside insert");
    String alphabet = "abcdefghijklmnopqrstuvwxyz";
    StringBuilder suggestion = new StringBuilder(word);
  //  System.out.println(suggestion);
    for(int l = 0; l < alphabet.length(); l++){
  //    System.out.println("inside first for");
      char letter = alphabet.charAt(l);
      for(int k = 0; k <= word.length(); k++){
    //   System.out.println("inside second for");
        suggestion.insert(k, letter);
        if(dictHash.contains(suggestion.toString()) == true){
        //  System.out.println("insde if");
          spellingSuggestions.add(suggestion.toString());
      //    System.out.println("spellingSuggestions Insert= " + spellingSuggestions);
        }
          suggestion.deleteCharAt(k);
      }
    }
}

  /**
  *substitutions()
  *a method that replaces each letter of the word being checked with
  *every other letter in the alphabet and looks for a match in the dict
  *@param String word
  */

  public void substitutions(String word){
    String alphabet = "abcdefghijklmnopqrstuvwxyz";
    for(int m = 0; m < alphabet.length(); m++){
      char letterS = alphabet.charAt(m);
      for(int n = 0; n <= word.length(); n++){
        StringBuilder suggestion = new StringBuilder(word);
      //  System.out.println("current letter=" + letterS);
        String letter = Character.toString(letterS);
      //  System.out.println(letter);
        suggestion.replace(n,n+1, letter);
      //  System.out.println("sub suggestion=" + suggestion);
        if(dictHash.contains(suggestion.toString()) == true){
          spellingSuggestions.add(suggestion.toString());
      //    System.out.println("Spelling suggestions Sub=" + spellingSuggestions);
        } //closes conditional
      }
    }
   }

  /**
  *split()
  *a method that splits the word being checked to see if it is actually
  *two words that both exist in the dictionary
  *@param String word
  */

  public void splits(String word){
    for(int z = 0; z < word.length(); z++){
      String firstHalf = word.substring(0,z);
      String lastHalf = word.substring(z);
      if(dictHash.contains(firstHalf) && dictHash.contains(lastHalf)){
        String suggestion = firstHalf.concat(lastHalf);
        spellingSuggestions.add(suggestion);
      }
    }
  }
}
