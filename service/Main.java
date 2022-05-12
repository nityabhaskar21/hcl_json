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
            
            // String regex = "variable\\s+?\\\"\\w+?\\\"\\s+?\\{((.|\\n)*?)\\}(\\z|\\s+?[^\\w])";
            // Pattern p = Pattern.compile(regex, Pattern.CASE_INSENSITIVE);
            final Pattern p = Pattern.compile("variable\\s+?\\\"\\w+?\\\"\\s+?\\{((.|\\n)*?)\\}(\\z|\\s+?[^\\w])" , Pattern.CASE_INSENSITIVE | Pattern.MULTILINE | Pattern.DOTALL);

        
            Matcher m = p.matcher(s);
        // Use results...

            while (m.find()) {
                System.out.println("m.group():\n"+ m.group());
            }

            fin.close();    
          } catch(Exception e){
            System.out.println(e);
          }
    }

    
}