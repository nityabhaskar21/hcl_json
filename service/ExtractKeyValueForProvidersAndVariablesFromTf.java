package service;

import java.io.File;
import java.io.FileInputStream;
import java.util.regex.Pattern;
import java.util.stream.IntStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;

public class ExtractKeyValueForProvidersAndVariablesFromTf {
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
              // "(?:(?:\"?([\w\$\-\.]+)\"?[\s\:\=]++\"?([\/\w\-\$\.\=\ \>\<\#\:\\]++)\"?)[\s\,]+)(?!\{)" flags=gmis , regex101 get key-value
              
              final Pattern extractProviderVariablePattern = Pattern.compile( "(?:variable|provider)[\\\"\\:\\s\\{]+([\\w\\-]++)(?:[\\\"\\ ]++)(?:\\{).+?(?:\\})(?=(?:\\s+\\w|$))" , Pattern.CASE_INSENSITIVE | Pattern.DOTALL);
              final Pattern extractKeyValuePattern = Pattern.compile( 	"(?:(type)\\s*+\\=\\s*+(\\w+)|(default)\\s*+\\=\\s?+(\\\"\\w+\\\"|\\[(?:(?:[^\\[\\]]*)(?>\\[[^\\]]*\\])*(?:[^\\[]*))\\]|\\{(?:(?:[^\\{\\}]*)(?>\\{[^\\}]*\\})*(?:[^\\{]*))\\})|(\\w+)\\s+\\=\\s+\\\"?([\\/\\w\\-\\$\\.\\=\\ \\>\\<\\#\\:\\\\]++)\\\"?)" , Pattern.CASE_INSENSITIVE | Pattern.DOTALL);
              List<Map<String, String>> providersVariables  = new ArrayList<Map<String, String>>();
              Matcher m = extractProviderVariablePattern.matcher(allFileContent);
              while (m.find()) {
                if (m.group(0)!= null ) {
                  Map<String, String> provider = new HashMap<String, String>();
                  if (m.group(1)!= null) {
                    provider.put("provider", m.group(1));
                  }
                  Matcher matchedProviderBlock = extractKeyValuePattern.matcher(m.group(0));
                  while (matchedProviderBlock.find()) {
                    IntStream.rangeClosed(1, 3).forEach(i -> {
                        if (matchedProviderBlock.group(i)!= null && matchedProviderBlock.group(i+1)!= null) {
                            provider.put(matchedProviderBlock.group(i), matchedProviderBlock.group(i+1));
                          }
                    });
                    
                  }
                  providersVariables.add(provider);
                }
              }
  
              System.out.println("values: "+ providersVariables);
                 
            } catch(Exception e){
              System.out.println(e);
            }
    }
    
}
