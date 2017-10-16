import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.*;
import java.io.*;

public class SpellChecker{
  public static final String SYMBOL = "[^\\w]";

  public static void main(String[] args) {
    SpellDictionary dObject = new SpellDictionary("Dictionary.txt");
    if(args.length == 0){
      Scanner userFile = new Scanner(System.in);
      ArrayList<String> file = new ArrayList<String>();

      while(userFile.hasNextLine()){
        String p = userFile.next();
        p  = p.toLowerCase().replaceAll(SYMBOL,"");
        file.add(p);
      }
      for(int a = 0; a < file.size(); a++){
        dObject.spellCheck(file.get(a));
      }
    }//closes if
    else{
      for(int i = 0; i < args.length; i++){
      //  dObject.checkWords(args[i]);
        dObject.spellCheck(args[i]);
      }

    }

  } //close main
}//close class
