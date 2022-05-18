package service;

import java.io.File;
import java.io.FileInputStream;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

public class FindPatternAndReplace {
    public static void main(String[] args) {
        try{    
            File f = new File("E:\\hcl_json\\resources\\main.tf");
            FileInputStream fin=new FileInputStream(f); 
            byte fileContent[] = new byte[(int)f.length()];   
            fin.read(fileContent);  
            String s = new String(fileContent);
            String extractedVariable = "";
            // System.out.println("File content: \n" + s);  
            
            String variableName = "docker_ports";
            final Pattern p = Pattern.compile( String.format("variable\\s+?\\\"%s\\\"\\s+?(\\{(.|\\n)*?\\})\\s*+(?![\\]\\)\\}])", variableName) , Pattern.CASE_INSENSITIVE | Pattern.MULTILINE | Pattern.DOTALL);
            final Pattern extractDefaultPattern = Pattern.compile( "\\s*+default\\s*+=\\s*+(\\[(.|\\n)*?\\]|\\((.|\\n)*?\\)|\\\"\\w*?\\\"|\\{(.|\\n)*?\\})" , Pattern.CASE_INSENSITIVE | Pattern.MULTILINE | Pattern.DOTALL);

            Matcher m = p.matcher(s);
            // Use results...
            int n = 1;
            while (m.find()) {
                System.out.println(n+++" m.group():\n"+ m.group(1));
                s = extractDefaultPattern.matcher(s).replaceAll("bar");
                Charset charset = StandardCharsets.UTF_8;
                Files.write(Paths.get("E:\\hcl_json\\resources\\main_copy.tf"), s.getBytes(charset));
            }
            
            fin.close();    
          } catch(Exception e){
            System.out.println(e);
          }
    }
    
}
