package service;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Base64;
// import apache.tomcat.util.codec.binary.Base64;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

public class ReadZipFile {
    public static void main(String[] args) throws IOException {
        String tfCongiContent = "";
		byte[] buffer = new byte[1024];
		String zipFileBase64Encoded = "UEsDBBQAAAAAAK1UlVQAAAAAAAAAAAAAAAALAAAAdGYtemlwRGVtby9QSwMEFAAAAAgAcFSVVEr8pvWyAAAAGwEAABIAAAB0Zi16aXBEZW1vL21haW4udGZlT7sOwjAM3Cv1H6wwpxULGwMjA98QhdSUSHmUOCFCiH8nUdMBYVmyrDv77pbgn3rCAExmYvDuO4CAs/YOjsAw8YwU+Z713afv+m73U2WH8+kCUimfXPyHrZ+SQWBaWtFITYN8CgqrxjCMpVcmjYXJN2YVhO25kEZLqgcOM4/V1YqUWTx6i1x5u0j3andWO22TFYskyj5MwqCb4718OKwhH0kHFC7ZKwaCVgW+SUNY8n4BUEsDBBQAAAAIAIVUlVSCniUj3gAAANQBAAAUAAAAdGYtemlwRGVtby9vdXRwdXQudGaVUEtqwzAQ3Rt8h4cPYHKBLgrdZNsWujSDPE4GZEmMRk1C6d2ruk7SRQuJVmJ4/1gsFUPnyHvWQUYOJnYayLlYgtVDh4+2AUbOTiWZxIAHdK97xvYJcYLV3+PbC1ZG9w1+J18Yy6vgOY7Fcy80n2X7/+3a5rNt2uac6xdpSJTzIeo4pOjFnQY+JlG+nPPfSbdhFEfGGYc917CKCwESlvirAX4EezyzFQ0ZprWFTJjpeDWnHcPFYCQVQWvVnXK10KpGAZurwkQ+LxJikIwNoiJEQ1LOtXp/21r3jFD3+wJQSwMECgAAAAAAqlSVVAAAAAAAAAAAAAAAABcAAAB0Zi16aXBEZW1vL3ZhcmlhYmxlcy50ZlBLAwQUAAAACACbVJVUs+JtWHAAAACfAAAAFgAAAHRmLXppcERlbW8vdmVyc2lvbnMudGZVjTsOhDAMRHsk7mDlANndrLQdexUUgREpwDDhUyDujoOgYApblt/MTAz4RtDRlmdE4HEO4LpcGDFITwWZf0Fv+3H2Z/LswQyQJdQKnl6VX6Ma0nEqyoyKKWW0PrahEgwvZcwNPEucdd/rtetKYz8AUEsBAj8AFAAAAAAArVSVVAAAAAAAAAAAAAAAAAsAJAAAAAAAAAAQAAAAAAAAAHRmLXppcERlbW8vCgAgAAAAAAABABgANQO5sD1V2AE1A7mwPVXYAbtLAwk9VdgBUEsBAj8AFAAAAAgAcFSVVEr8pvWyAAAAGwEAABIAJAAAAAAAAAAgAAAAKQAAAHRmLXppcERlbW8vbWFpbi50ZgoAIAAAAAAAAQAYAHn3cWw9VdgBefdxbD1V2AHovMwwPVXYAVBLAQI/ABQAAAAIAIVUlVSCniUj3gAAANQBAAAUACQAAAAAAAAAIAAAAAsBAAB0Zi16aXBEZW1vL291dHB1dC50ZgoAIAAAAAAAAQAYACqYRoM9VdgBKphGgz1V2AEqmEaDPVXYAVBLAQI/AAoAAAAAAKpUlVQAAAAAAAAAAAAAAAAXACQAAAAAAAAAIAAAABsCAAB0Zi16aXBEZW1vL3ZhcmlhYmxlcy50ZgoAIAAAAAAAAQAYABqaTK09VdgBSHu6uD1V2AEamkytPVXYAVBLAQI/ABQAAAAIAJtUlVSz4m1YcAAAAJ8AAAAWACQAAAAAAAAAIAAAAFACAAB0Zi16aXBEZW1vL3ZlcnNpb25zLnRmCgAgAAAAAAABABgAOXU4nT1V2AFz+b+4PVXYAQVNOJ09VdgBUEsFBgAAAAAFAAUA+AEAAPQCAAAAAA==";
        byte[] bytes = Base64.getDecoder().decode(zipFileBase64Encoded);
		ZipInputStream zipInputStream = new ZipInputStream(new ByteArrayInputStream(bytes));
		ZipEntry zipEntry;
		int read;
		while ((zipEntry = zipInputStream.getNextEntry()) != null) {
			while ((read = zipInputStream.read(buffer, 0, 1024)) >= 0) {
				tfCongiContent += (new String(buffer, 0, read));
			}
		}
		while (zipEntry != null) {
			zipEntry = zipInputStream.getNextEntry();
		}
		zipInputStream.closeEntry();
		zipInputStream.close();
		System.out.println(tfCongiContent); 
    }
}
