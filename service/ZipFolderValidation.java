package service;
import java.io.IOException;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

public class ZipFolderValidation {

    public static void main(String[] args) throws IOException {
        ZipFile zip = null;
		try {
			zip = new ZipFile("E:\\hcl_json\\resources\\test.zip");
            Set<String> folderSet = new HashSet<> ();
			for (Enumeration<?> e = zip.entries(); e.hasMoreElements();) {
				ZipEntry entry = (ZipEntry) e.nextElement();
				if (!entry.isDirectory()) {
                    if(List.of(".tf", ".json", ".tf.json").stream().anyMatch(ext -> entry.getName().endsWith(ext))) {
                        folderSet.add(entry.getName().replaceFirst(	"(?<=[\\/\\\\])([\\w\\.\\s\\%]+)$", ""));
                    }
					
				}
                
			}
            System.out.println(folderSet);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			zip.close();
		}
    }
    
}
