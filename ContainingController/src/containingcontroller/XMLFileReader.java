package containingcontroller;

import java.io.File;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.jme3.math.Vector3f;

public class XMLFileReader
{
	public ArrayList<Container> getContainers()
	{
		ArrayList<Container> containers = new ArrayList<Container>();
	    try 
	    {	 
	    	File fXmlFile = new File("xml1.xml");
	    	DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
	    	DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
	    	Document doc = dBuilder.parse(fXmlFile);
	     
	    	doc.getDocumentElement().normalize();
	     	     
	    	NodeList nList = doc.getElementsByTagName("record");
	    	
	    	
	    	for (int temp = 0; temp < nList.getLength(); temp++) 
	    	{
	     
	    		Node nNode = nList.item(temp);
	    		
	    		if (nNode.getNodeType() == Node.ELEMENT_NODE) 
	    		{
	    			Element eElement = (Element) nNode;
	    			
	    			ContainerInfo arrival = null;
					ContainerInfo departure = null;
	    			String ownerName = null;
	    			int containerNumber = 0;
	    			Vector3f positie = null;
	    			Vector3f afmetingen = null;
	    			int gewichtLeeg = 0;
	    			int gewichtVol = 0;
	    			ContainerInhoud inhoud;
	    			String ISO = "";
	    			
	    			arrival = new ContainerInfo(Integer.parseInt(eElement.getElementsByTagName("datum").item(0).getChildNodes().item(1).getTextContent()),
	    										Integer.parseInt(eElement.getElementsByTagName("datum").item(0).getChildNodes().item(3).getTextContent()),
	    										Integer.parseInt(eElement.getElementsByTagName("datum").item(0).getChildNodes().item(5).getTextContent()),
	    										Double.parseDouble(eElement.getElementsByTagName("tijd").item(0).getChildNodes().item(1).getTextContent()),
	    										Double.parseDouble(eElement.getElementsByTagName("tijd").item(0).getChildNodes().item(3).getTextContent()),
	    										TravelType.getTravelType(eElement.getElementsByTagName("soort_vervoer").item(0).getTextContent()),
	    										eElement.getElementsByTagName("bedrijf").item(0).getTextContent());
	    			
	    			departure = new ContainerInfo(Integer.parseInt(eElement.getElementsByTagName("datum").item(1).getChildNodes().item(1).getTextContent()),
												  Integer.parseInt(eElement.getElementsByTagName("datum").item(1).getChildNodes().item(3).getTextContent()),
												  Integer.parseInt(eElement.getElementsByTagName("datum").item(1).getChildNodes().item(5).getTextContent()),
												  Double.parseDouble(eElement.getElementsByTagName("tijd").item(1).getChildNodes().item(1).getTextContent()),
												  Double.parseDouble(eElement.getElementsByTagName("tijd").item(1).getChildNodes().item(3).getTextContent()),
												  TravelType.getTravelType(eElement.getElementsByTagName("soort_vervoer").item(0).getTextContent()),
												  eElement.getElementsByTagName("bedrijf").item(0).getTextContent());

	    			ownerName = eElement.getElementsByTagName("eigenaar").item(0).getChildNodes().item(1).getTextContent();
	    			containerNumber = Integer.parseInt(eElement.getElementsByTagName("eigenaar").item(0).getChildNodes().item(3).getTextContent());
	    			
	    			positie = new Vector3f(Float.parseFloat(eElement.getElementsByTagName("positie").item(0).getChildNodes().item(1).getTextContent()),
	    								   Float.parseFloat(eElement.getElementsByTagName("positie").item(0).getChildNodes().item(3).getTextContent()),
	    								   Float.parseFloat(eElement.getElementsByTagName("positie").item(0).getChildNodes().item(5).getTextContent()));
	    		
	    			String[] x = eElement.getElementsByTagName("afmetingen").item(0).getChildNodes().item(1).getTextContent().split("'");
	    			
	    			String xLoc = "";
	    			
	    			if(x.length > 1)
	    				xLoc = x[0].concat("." + x[1]);
	    			else
	    				xLoc = x[0];
	    			
	    			String[] y = eElement.getElementsByTagName("afmetingen").item(0).getChildNodes().item(3).getTextContent().split("'");
	    			
	    			String yLoc = "";
	    			
	    			if(y.length > 1)
	    				yLoc = y[0].concat("." + y[1]);
	    			else
	    				yLoc = y[0];

	    			String[] z = eElement.getElementsByTagName("afmetingen").item(0).getChildNodes().item(5).getTextContent().split("'");
	    			
	    			String zLoc = "";
	    			
	    			if(z.length > 1)
	    				zLoc = z[0].concat("." + z[1]);
	    			else
	    				zLoc = z[0];
	    			
	    			afmetingen = new Vector3f(Float.parseFloat(xLoc), Float.parseFloat(yLoc), Float.parseFloat(zLoc));
	    			gewichtLeeg = Integer.parseInt(eElement.getElementsByTagName("gewicht").item(0).getChildNodes().item(1).getTextContent());
	    			gewichtVol = Integer.parseInt(eElement.getElementsByTagName("gewicht").item(0).getChildNodes().item(3).getTextContent());
	    			inhoud = new ContainerInhoud(eElement.getElementsByTagName("inhoud").item(1).getChildNodes().item(1).getTextContent(),
	    										 eElement.getElementsByTagName("inhoud").item(1).getChildNodes().item(3).getTextContent(),
	    										 eElement.getElementsByTagName("inhoud").item(1).getChildNodes().item(5).getTextContent());
	    			ISO = eElement.getElementsByTagName("ISO").item(0).getTextContent();
	    			containers.add(new Container(arrival, departure, ownerName, containerNumber, positie, afmetingen, gewichtLeeg, gewichtVol, inhoud, ISO));
	    			
	    			System.out.println(temp);
	    		}
	    	}
	    }
	    catch (Exception e) { e.printStackTrace(); }
	    
	    for(Container c : containers)
	    {
	    	ContainerInfo info1 = c.arrival;
	    	ContainerInfo info2 = c.departure;
	    	System.out.printf("AANKOMST: dag: %s maand: %s jaar: %s AankomstTijd: %s VertrekTijd: %s Soort Vervoer: %s Bedrijf: %s \n"
	    					+ "DEPARTURE: dag: %s maand: %s jaar: %s AankomstTijd: %s VertrekTijd: %s Soort Vervoer: %s Bedrijf: %s \n"
	    					+ "Overige Info: eigenaar: %s containerNummer: %s Positie: %s %s %s Afmetingen: %s %s %s \n"
	    					+ "Meer Overige: gewicht leeg: %s gewicht vol: %s inhoud: %s %s %s ISO: %s \n \n", info1.day, info1.month, info1.year, info1.arrivalTime, info1.departureTime, info1.type, info1.bedrijf,
	    																							 info2.day, info2.month, info2.year, info2.arrivalTime, info2.departureTime, info2.type, info2.bedrijf,
	    																							 c.ownerName, c.containerNumber, c.positie.x, c.positie.y, c.positie.z, c.afmetingen.x, c.afmetingen.y, c.afmetingen.z,
	    																							 c.gewichtLeeg, c.gewichtVol, c.inhoud.naam, c.inhoud.soort, c.inhoud.gevaar, c.ISO);
	    }
	    
		return null;
	}	
	
	public class Container
	{
		public ContainerInfo arrival, departure;
		public String ownerName;
		public int containerNumber;
		public Vector3f positie;
		public Vector3f afmetingen;
		public int gewichtLeeg, gewichtVol;
		public ContainerInhoud inhoud;
		public String ISO;
		
		public Container(ContainerInfo arrival, ContainerInfo departure, String ownerName, int containerNumber, Vector3f positie, Vector3f afmetingen, int gewichtLeeg, int gewichtVol, ContainerInhoud inhoud, String ISO)
		{
			this.arrival = arrival;
			this.departure = departure;
			this.ownerName = ownerName;
			this.containerNumber = containerNumber;
			this.positie = positie;
			this.afmetingen = afmetingen;
			this.gewichtLeeg = gewichtLeeg;
			this.gewichtVol = gewichtVol;
			this.inhoud = inhoud;
			this.ISO = ISO;
		}
	}
	
	public class ContainerInfo
	{
		public int day, month, year;
		public double arrivalTime, departureTime;
		public TravelType type;
		public String bedrijf;
		
		public ContainerInfo(int day, int month, int year, double arrivalTime, double departureTime, TravelType type, String bedrijf)
		{
			this.day = day;
			this.month = month;
			this.year = year;
			this.arrivalTime = arrivalTime;
			this.departureTime = departureTime;
			this.type = type;
			this.bedrijf = bedrijf;
		}
	}
	
	public enum TravelType
	{
		VRACHTAUTO,
		ZEESCHIP,
		BINNENSCHIP,
		TREIN;
		
		public static TravelType getTravelType(String s)
		{
			switch(s)
			{
				case "vrachtauto":
					return VRACHTAUTO;
				case "zeeschip":
					return ZEESCHIP;
				case "binnenschip":
					return BINNENSCHIP;
				case "trein":
					return TREIN;
				default:
					return TREIN;
			}
		}
	}

	public class ContainerInhoud
	{
		public String naam, soort, gevaar;
		public ContainerInhoud(String naam, String soort, String gevaar)
		{
			this.naam = naam;
			this.soort = soort;
			this.gevaar = gevaar;
		}
	}
}
