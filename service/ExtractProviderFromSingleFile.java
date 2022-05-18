package service;

import java.io.File;
import java.io.FileInputStream;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

public class ExtractProviderFromSingleFile {
    public static void main(String[] args) {
        try{    
            File f = new File("E:\\hcl_json\\resources\\provider_format.txt");
            FileInputStream fin=new FileInputStream(f); 
            byte fileContent[] = new byte[(int)f.length()];   
            fin.read(fileContent);  
            String s = new String(fileContent);
  
            // System.out.println("File content: \n" + s);  
            
            final Pattern extractProviderPattern = Pattern.compile( "provider(?:\\s++\\\"(\\w+)\\\"|\\W++(\\w+)\\\")" , Pattern.CASE_INSENSITIVE | Pattern.MULTILINE | Pattern.DOTALL);

            Matcher m = extractProviderPattern.matcher(s);
            int n = 1;
            while (m.find()) {
              if (m.group(1)!= null)
                System.out.println(n+++" m.group():\n"+ m.group(1));
              else 
                System.out.println(n+++" m.group():\n"+ m.group(2));
            }
            
            fin.close();    
          } catch(Exception e){
            System.out.println(e);
          }
    }
    
}
