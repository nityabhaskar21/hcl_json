package service;

import java.io.File;
import java.io.FileInputStream;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

/**
 * Main
 */
public class CustomVariableMatcher {

    public static void main(String[] args) {
        try{    
            File f = new File("E:\\hcl_json\\resources\\main.tf");
            FileInputStream fin=new FileInputStream(f); 
            byte fileContent[] = new byte[(int)f.length()];   
            fin.read(fileContent);  
            String s = new String(fileContent);
            // System.out.println("File content: \n" + s);  
            
            // String regex101 = "variable\s+?\"\w+?\"\s+?(\{(.|\n)*?\})\s*+(?![\]\)\}])";
            String variableName = "availability_zone_names";
            final Pattern p = Pattern.compile( String.format("variable\\s+?\\\"%s\\\"\\s+?(\\{(.|\\n)*?\\})\\s*+(?![\\]\\)\\}])", variableName) , Pattern.CASE_INSENSITIVE | Pattern.MULTILINE | Pattern.DOTALL);
    
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