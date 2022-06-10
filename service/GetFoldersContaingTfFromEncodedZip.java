package service;

import java.io.ByteArrayInputStream;
import java.util.zip.ZipFile;
import java.util.zip.ZipInputStream;

public class GetFoldersContaingTfFromEncodedZip {
    public static void main(String[] args) {
        byte[] bytes = org.apache.tomcat.util.codec.binary.Base64.decodeBase64(zipFileBase64Encoded);
		ZipInputStream zipInputStream = new ZipInputStream(new ByteArrayInputStream(bytes));
		try (ZipFile zip = new ZipFile(zipInputStream)){
            Set<String> folderSet = new HashSet<> ();
			for (Enumeration<?> e = zip.entries(); e.hasMoreElements();) {
				ZipEntry entry = (ZipEntry) e.nextElement();
				if (!entry.isDirectory()) {
                    if(List.of(".tf", ".json", ".tf.json").stream().anyMatch(ext -> entry.getName().toLowerCase().endsWith(ext))) {
                        folderSet.add(entry.getName().replaceFirst("(?<=[\\/\\\\])([\\w\\.\\s\\%]+)$", ""));
                    }
				}   
			}
            System.out.println(folderSet);
            return folderSet;
		} catch (Exception e) {
			e.printStackTrace();
		}
    }
}
