package org.insecurelabs.api.contacts;

import com.thoughtworks.xstream.*;
import com.thoughtworks.xstream.io.xml.*;
import java.io.*;
import javax.xml.transform.*;
import javax.xml.transform.stream.*;
import java.nio.*;
import java.nio.file.*;

public class Formatter {
  public static String format(Contact contact, String xslt) throws Exception {
  	System.out.println(xslt);
		XStream xstream = new XStream();
		xstream.alias("contact", Contact.class);

		Path path = Paths.get("./doc.xslt");
    Files.write(path, xslt.getBytes());
    System.out.println(path.toString());
		TraxSource traxSource = new TraxSource(contact, xstream);
		Writer buffer = new StringWriter();
		Transformer transformer = TransformerFactory.newInstance().newTransformer(
		    new StreamSource(path.toFile()));
		transformer.transform(traxSource, new StreamResult(buffer));
		return buffer.toString();
	}
}
