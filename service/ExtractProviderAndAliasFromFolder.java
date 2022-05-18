package service;

import java.io.File;
import java.io.FileInputStream;
import java.util.regex.Pattern;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;

public class ExtractProviderAndAliasFromFolder {
    public static void main(String[] args) {
        try{    
          File folder = new File("E:\\hcl_json\\resources\\folder");
          File[] listOfFiles = folder.listFiles();

          String allFileContent = "";
          FileInputStream fin;
          
          for (File file : listOfFiles) {
              if (file.isFile()) {
                fin=new FileInputStream(file);
                byte fileContent[] = new byte[(int)file.length()];   
                fin.read(fileContent); 
                allFileContent += new String(fileContent);
                fin.close();
              }
          }
            System.out.println("File content: \n" + allFileContent);  
            
            // final Pattern extractProviderPattern = Pattern.compile( "provider(?>\\s++\\\"(\\w+)\\\"|\\W++(\\w+)\\\")(((?!alias)(.|\\n))*alias\\W*\\\"(.*)\\\")?" , Pattern.CASE_INSENSITIVE | Pattern.MULTILINE | Pattern.DOTALL);
            // Map<String, String> providers  = new HashMap<String, String>();
            // Matcher m = extractProviderPattern.matcher(allFileContent);
            // while (m.find()) {
            //   if (m.group(1)!= null ) {
            //     providers.put(m.group(1), m.group(6)!= null ? m.group(6): "default");
            //   }
            //   else if (m.group(2)!= null ) {
            //     providers.put(m.group(2), m.group(6)!= null ? m.group(6): "default");
            //   }
            // }

            // System.out.println("Providers: ");
            // providers.forEach((k, v) -> System.out.println("key: "+ k + ", Value: "+ v));
               
          } catch(Exception e){
            System.out.println(e);
          }
    }
    
}
