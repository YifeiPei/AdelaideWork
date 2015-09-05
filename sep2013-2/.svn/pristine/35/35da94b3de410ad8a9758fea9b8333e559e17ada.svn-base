import java.io.File;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;


public class Savemap {
	
	// for testing ...
	public static void main(String args[])
	{
		Savemap aa = new Savemap();
		aa.Saving("OriginalFile.xml");
	}
	public void Saving (String a)
	{
	try{
		
		a="OriginalFile.xml";
		File fXmlFile = new File("C:\\Users\\Justin\\workspace\\Mapping\\src\\"+a);
		DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();	
		Document doc = dBuilder.parse(fXmlFile);
		doc.getDocumentElement().normalize();
		// get the information from robot detecting
		DataOfDetecting addmap = new DataOfDetecting();
		// define the obstacle node
		Node obstacle =null;
		NodeList structures = doc.getElementsByTagName("structures");
		Node point =null;
		Node structure =structures.item(0); 
	    // add an obstacle on the map
		for (int i=0;i<addmap.obstacle.length;i++)	
		{
			obstacle= doc.createElement("obstacle");
			structure.appendChild(obstacle);
			point =doc.createElement("point");
			obstacle.appendChild(point);
			Element o =(Element)point;
			String o_x =Integer.toString(addmap.obstacle[i][0]);
			String o_y =Integer.toString(addmap.obstacle[i][1]);		
			o.setAttribute("x", o_x);
			o.setAttribute("y", o_y);
		}
		// test the output..
		output(doc);
		// save the modification..
		Source xmlSource = new DOMSource(doc);
		TransformerFactory factory = TransformerFactory.newInstance();  
		Transformer transformer = factory.newTransformer(); 
		Result result = new StreamResult(new File("C:\\Users\\Justin\\workspace\\Mapping\\src\\NewFile.xml")); 
		transformer.transform(xmlSource, result);
	}
	catch (Exception e) {
		e.printStackTrace();
		
	    }

}
	
	// For testing......	
	public static void output(Node node) {
		
		// For testing......
        TransformerFactory transFactory=TransformerFactory.newInstance();
        try {
            Transformer transformer = transFactory.newTransformer();
            transformer.setOutputProperty("encoding", "gb2312");
            transformer.setOutputProperty("indent", "yes");

            DOMSource source=new DOMSource();
            source.setNode(node);
            StreamResult result=new StreamResult();
            result.setOutputStream(System.out);
            transformer.transform(source, result);
            
            Node document = null;
			Source xmlSource = new DOMSource(document);
		    transformer.transform(xmlSource, result);
              
            
            
        } catch (TransformerConfigurationException e) {
            e.printStackTrace();
        } catch (TransformerException e) {
            e.printStackTrace();
        }   
    }

	
}





