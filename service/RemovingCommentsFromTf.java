package service;

import java.io.File;
import java.io.FileInputStream;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

public class RemovingCommentsFromTf {
    public static void main(String[] args) {
        try{    
            File f = new File("E:\\hcl_json\\resources\\folder1\\main1.tf");
            FileInputStream fin=new FileInputStream(f); 
            byte fileContent[] = new byte[(int)f.length()];   
            fin.read(fileContent);  
            String s = new String(fileContent);
  
            // System.out.println("File content: \n" + s);  
            
            final Pattern extractProviderPattern = Pattern.compile( "^\\h*(?:\\#|\\/)+(?:.)*+$" , Pattern.CASE_INSENSITIVE | Pattern.MULTILINE );
            var replacedString = extractProviderPattern.matcher(s).replaceAll("");
            System.out.println("replacedString: "+ replacedString);
            
            fin.close();    
          } catch(Exception e){
            System.out.println(e);
          }
    }
}
