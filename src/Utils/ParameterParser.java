package Utils;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;


public class ParameterParser {
	private String simType;
	private Map<String,String> parameters;
	private Map<int[],String> cells;
	public static final String TAG_NAME = "simulation";
	public static final String TAG_ID = "id";
	private Document myDoc;
	public ParameterParser(File file)  {
		DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder dBuilder = null;
		try {
			dBuilder = dbFactory.newDocumentBuilder();
		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			myDoc = dBuilder.parse(file);
		} catch (SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		myDoc.getDocumentElement().normalize();
		NodeList nodeList = myDoc.getElementsByTagName("*");
		Element el = (Element) nodeList.item(0);
		setSimType(el.getAttribute("id"));
		initiateParameterMap(nodeList);
	}
	private void initiateParameterMap(NodeList nodeList) {
		parameters = new HashMap<String,String>();
		for (int i = 1; i<nodeList.getLength();i++) {
			Element element = (Element) nodeList.item(i);
			String attr = element.getNodeName();
			if (attr.equals("cells")) { 
				initiateCellMap(element);
				continue;
			}
			String value = myDoc.getElementsByTagName(attr).item(0).getTextContent();
			//System.out.println(attr+" "+value);
			parameters.put(attr, value);
		}
	}
	private void initiateCellMap(Element el) {
		cells = new HashMap<int[],String>();
		NodeList cellList = el.getElementsByTagName("cell");
		for (int idx = 0; idx<cellList.getLength();idx++) {
			String str = cellList.item(idx).getTextContent();
			String[] strArr = str.split(",");
			int[] coordinates = new int[2];
			coordinates[0] = Integer.parseInt(strArr[0]);
			coordinates[1] = Integer.parseInt(strArr[1]);
			cells.put(coordinates,strArr[2]);
		}
	}
	public void setSimType(String simType) {
		this.simType = simType;
	}

	public String getSimType() {
		return simType;
	}

	public Map<String,String> getParameters() {
		return parameters;
	}
	public Map<int[],String> getCells(){
		return cells;
	}
}
