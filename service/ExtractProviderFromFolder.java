package service;

import java.io.File;
import java.io.FileInputStream;
import java.util.regex.Pattern;
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

            Matcher m = extractProviderPattern.matcher(allFileContent);
            int n = 1;
            while (m.find()) {
              if (m.group(1)!= null)
                System.out.println(n+++" "+ m.group(1));
              else 
                System.out.println(n+++" "+ m.group(2));
            }
               
          } catch(Exception e){
            System.out.println(e);
          }
    }
    
}
