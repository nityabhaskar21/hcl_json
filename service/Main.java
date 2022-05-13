package service;

import java.io.File;
import java.io.FileInputStream;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

/**
 * Main
 */
public class Main {

    public static void main(String[] args) {
        try{    
            File f = new File("E:\\hcl_json\\resources\\main.tf");
            FileInputStream fin=new FileInputStream(f); 
            byte fileContent[] = new byte[(int)f.length()];   
            fin.read(fileContent);  
            String s = new String(fileContent);
            // System.out.println("File content: \n" + s);  
            
            // String regex101 = "variable\s+?\"\w+?\"\s+?(\{(.|\n)*?\})\s*+(?![\]\)\}])";
            final Pattern p = Pattern.compile( "variable\\s+?\\\"\\w+?\\\"\\s+?(\\{(.|\\n)*?\\})\\s*+(?![\\]\\)\\}])" , Pattern.CASE_INSENSITIVE | Pattern.MULTILINE | Pattern.DOTALL);
            final Pattern extractDefault = Pattern.compile( "\\s*+default\\s*+=\\s*+(\\[(.|\\n)*?\\]|\\((.|\\n)*?\\)|\\\"\\w*?\\\"|\\{(.|\\n)*?\\})" , Pattern.CASE_INSENSITIVE | Pattern.MULTILINE | Pattern.DOTALL);
    
            Matcher m = p.matcher(s);
            // Use results...
            int n = 1;
            while (m.find()) {
                System.out.println(n+++" m.group():\n"+ m.group(1));
            }

            fin.close();    
          } catch(Exception e){
            System.out.println(e);
          }
    }

    
}