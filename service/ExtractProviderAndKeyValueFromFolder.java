package service;

import java.io.File;
import java.io.FileInputStream;
import java.util.regex.Pattern;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;

public class ExtractProviderAndKeyValueFromFolder {
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

            // "(?:(?:\{\s+\")?provider)\W+(\w+)\"(?:[\s\[\{\:\-,\=\.]+).*?\}" flags=gmis , regex101 get provider block
            // "	(?:(?:\"?([\w\$\-\.]+)\"?[\s\:\=]++\"?([\w\-\$\.\=\ \>\<\#]++)\"?)[\s\,]+)(?!\{)" flags=gmis , regex101 get key-value
            
            final Pattern extractProviderPattern = Pattern.compile( "(?:(?:\\{\\s+\\\")?provider)\\W+(\\w+)\\\"(?:[\\s\\[\\{\\:\\-,\\=\\.]+).*?\\}" , Pattern.CASE_INSENSITIVE | Pattern.MULTILINE | Pattern.DOTALL);
            final Pattern extractKeyValuePattern = Pattern.compile( "(?:(?:\\\"?([\\w\\$\\-\\.]+)\\\"?[\\s\\:\\=]++\\\"?([\\w\\-\\$\\.\\=\\ \\>\\<\\#]++)\\\"?)[\\s\\,]+)(?!\\{)" , Pattern.CASE_INSENSITIVE | Pattern.MULTILINE | Pattern.DOTALL);
            List<Map<String, String>> providers  = new ArrayList<Map<String, String>>();
            Matcher m = extractProviderPattern.matcher(allFileContent);
            while (m.find()) {
              if (m.group(0)!= null ) {
                Map<String, String> provider = new HashMap<String, String>();
                if (m.group(1)!= null) {
                  provider.put("provider", m.group(1));
                }
                Matcher matchedProviderBlock = extractKeyValuePattern.matcher(m.group(0));
                while (matchedProviderBlock.find()) {
                  if (matchedProviderBlock.group(1)!= null && matchedProviderBlock.group(2)!= null) {
                    provider.put(matchedProviderBlock.group(1), matchedProviderBlock.group(2));
                  }
                }
                providers.add(provider);
              }
            }

            System.out.println("Providers: "+ providers);
               
          } catch(Exception e){
            System.out.println(e);
          }
    }
    
}
