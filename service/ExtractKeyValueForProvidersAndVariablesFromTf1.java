package service;

import java.io.File;
import java.io.FileInputStream;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;

public class ExtractKeyValueForProvidersAndVariablesFromTf1 {
    public static void main(String[] args) {
        try{    
            File folder = new File("E:\\hcl_json\\resources\\folder1");
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
           
              // "(variable|provider)[\"\:\s\{]+([\w\-]++)(?:[\"\ ]++)(?:\{).*?(?:\})(?=(?:\s+[\w\#]|$))(?![^\{]*= *\{)(?![^\{]*= *[\w\$\"])" flags=gis , regex101 get provider block
              // "(?:(type)\s*+\=\s*+(\w+)|(default)\s*+\=\s*+((?:(?:\{(?:(?:[^\{\}]*+)(?:\{[^\{\}]*+(?:\{[^\{\}]*+\})*[^\{\}]*+\})*(?:[^\{\}]*+))*\})|(?:\[(?:(?:[^\[\]]*+)(?:\[[^\[\]]*+(?:\[[^\[\]]*+\])*[^\[\]]*+\])*(?:[^\[\]]*+))*\])))|(\w+)\s+\=\s+\"?([\/\w\-\$\.\=\ \>\<\#\:\\\[\]]++)\"?)" flags=gis , regex101 get key-value
              
              final Pattern extractProviderVariablePattern = Pattern.compile( "(variable|provider)[\\\"\\:\\s\\{]+([\\w\\-]++)(?:[\\\"\\ ]++)(?:\\{).*?(?:\\})(?=(?:\\s+[\\w\\#]|$))(?![^\\{]*= *\\{)(?![^\\{]*= *[\\w\\$\\\"])"
              , Pattern.CASE_INSENSITIVE | Pattern.DOTALL);
              
              final Pattern extractKeyValuePattern = Pattern.compile( "(?:(type)\\s*+\\=\\s*+(\\w+)|(default)\\s*+\\=\\s*+((?:(?:\\{(?:(?:[^\\{\\}]*+)(?:\\{[^\\{\\}]*+(?:\\{[^\\{\\}]*+\\})*[^\\{\\}]*+\\})*(?:[^\\{\\}]*+))*\\})|(?:\\[(?:(?:[^\\[\\]]*+)(?:\\[[^\\[\\]]*+(?:\\[[^\\[\\]]*+\\])*[^\\[\\]]*+\\])*(?:[^\\[\\]]*+))*\\])))|(\\w+)\\s+\\=\\s+\\\"?([\\/\\w\\-\\$\\.\\=\\ \\>\\<\\#\\:\\\\\\[\\]]++)\\\"?)"
              , Pattern.CASE_INSENSITIVE | Pattern.DOTALL);
              
              List<Map<String, String>> providersVariablesList  = new ArrayList<Map<String, String>>();
              Matcher m = extractProviderVariablePattern.matcher(allFileContent);
              while (m.find()) {
                if (m.group(0)!= null ) {
                  Map<String, String> providerVariableMap = new HashMap<String, String>();
                  if (m.group(1)!= null && m.group(2)!= null) {
                    providerVariableMap.put(m.group(1), m.group(2));
                  }
                  Matcher matchedProviderBlock = extractKeyValuePattern.matcher(m.group(0));
                  while (matchedProviderBlock.find()) {
                    IntStream.rangeClosed(1, 6).filter(n -> n % 2 != 0).boxed().collect(Collectors.toSet()).forEach(i -> {
                        if (matchedProviderBlock.group(i)!= null && matchedProviderBlock.group(i+1)!= null) {
                            providerVariableMap.put(matchedProviderBlock.group(i), matchedProviderBlock.group(i+1));
                          }
                    });
                    
                  }
                  if (providerVariableMap.containsKey("variable")) {
                    providerVariableMap.keySet().retainAll(List.of("variable", "type", "default"));
                  }
                  providersVariablesList.add(providerVariableMap);
                }
              }

              providersVariablesList.forEach(e -> System.out.println(e));
                 
            } catch(Exception e){
              System.out.println(e);
            }
    }
}
