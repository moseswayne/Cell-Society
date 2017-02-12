package Utils;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

public class StateSaver {
	
	private Map<String,String> myParameters;
	private Map<int[], String> myCells;
	private String mySimType;
	private String myFileName;
	
	Document myDoc;

	public StateSaver() {
	}
	
	public StateSaver(String fileName, String simType,Map<String,String> parameters,Map<int[], String> cells) throws TransformerException {
		myFileName = fileName;
		myParameters = parameters;
		myCells = cells;
		mySimType = simType;
		writeToXML();
	}
	
	public void writeToXML() throws TransformerException {
		try {

			DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
			
			myDoc = docBuilder.newDocument();
			Element simulationElement = myDoc.createElement("simulation");
			myDoc.appendChild(simulationElement);
			
			Attr attr = myDoc.createAttribute("id");
			attr.setValue(mySimType);
			simulationElement.setAttributeNode(attr);
		    
			writeParameters(simulationElement);
		    writeCells(simulationElement);
		 
		    // write the content into xml file
			TransformerFactory transformerFactory = TransformerFactory.newInstance();
			Transformer transformer = transformerFactory.newTransformer();
			DOMSource source = new DOMSource(myDoc);
			StreamResult result = new StreamResult(new File(myFileName));

			// Output to console for testing
			// StreamResult result = new StreamResult(System.out);

			transformer.transform(source, result);

			System.out.println("File saved!");
		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	} 
	
	private void writeParameters(Element parentEl) {
		for (String parameter : myParameters.keySet()) {
			Element el = myDoc.createElement(parameter);
			el.appendChild(myDoc.createTextNode(myParameters.get(parameter)));
			parentEl.appendChild(el);
		}
	}
	
	private void writeCells(Element parentEl) {
		Element cellEl = myDoc.createElement("cells");
		parentEl.appendChild(cellEl);
		for (int[] coordinates : myCells.keySet()) {
			Element cell = myDoc.createElement("cell");
			cell.appendChild(myDoc.createTextNode(myCells.get(coordinates)));
			cellEl.appendChild(cell);
		}
	}

	public void setParameters(Map<String,String> parameters) {
		myParameters = parameters;
	}
	
	public void setCells(Map<int[], String> cells) {
		myCells = cells;
	}
	
	public void setSimType(String simType) {
		mySimType = simType;
	}
}
