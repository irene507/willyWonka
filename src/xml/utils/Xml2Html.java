package xml.utils;

import java.io.File;

import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;

public class Xml2Html {
	
	/**
	 * Simple transformation method. You can use it in your project.
	 * @param sourcePath - Absolute path to source xml file.
	 * @param xsltPath - Absolute path to xslt file.
	 * @param resultDir - Directory where you want to put resulting files.
	 */
	public static void simpleTransform(String sourcePath, String xsltPath,String resultDir) {
		TransformerFactory tFactory = TransformerFactory.newInstance();
		try {
			Transformer transformer = tFactory.newTransformer(new StreamSource(new File(xsltPath)));
			transformer.transform(new StreamSource(new File(sourcePath)),new StreamResult(new File(resultDir)));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		simpleTransform("./xmls/Output-Chocolate.xml", "./xmls/ChocolateStyle.xslt", "./xmls/Chocolate.html");
		simpleTransform("./xmls/Output-OompaLoompa.xml", "./xmls/oompaLoompaStyle.xslt", "./xmls/OompaLoompa.html");
		simpleTransform("./xmls/Output-Warehouse.xml", "./xmls/warehouseStyle.xslt", "./xmls/Warehouse.html");
		simpleTransform("./xmls/Output-Animal.xml", "./xmls/AnimalStyle.xslt", "./xmls/Animal.html");
		
		}
}
