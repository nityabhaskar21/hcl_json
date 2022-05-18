package service;

import java.io.File;
import java.io.FileInputStream;
import java.util.regex.Pattern;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;

public class ExtractProviderFromFolder {
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
            // System.out.println("File content: \n" + allFileContent);  
            
            final Pattern extractProviderPattern = Pattern.compile( "provider(?:\\s++\\\"(\\w+)\\\"|\\W++(\\w+)\\\")" , Pattern.CASE_INSENSITIVE | Pattern.MULTILINE | Pattern.DOTALL);
            List<String> providers = new ArrayList<String>();
            Matcher m = extractProviderPattern.matcher(allFileContent);
            while (m.find()) {
              if (m.group(1)!= null) {
                providers.add(m.group(1));
              }
              else if (m.group(2)!= null) {
                providers.add(m.group(2));
              }
            }

            System.out.println("Providers: "+ providers);
               
          } catch(Exception e){
            System.out.println(e);
          }
    }
    
}
