package nhl.containing.server.util;

import java.io.File;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.jme3.math.Vector3f;

/**
 * Reads XML Files
 *
 * @author Arjen
 */
public class XMLFileReader {

    /**
     * retrieves all the containers from a certain XML file
     *
     * @param path string of the xml file location ("../XMLFILES/xml1.xml")
     * @return ArrayList of all the containersS
     */
    public ArrayList<Container> getContainers(String path) {
        ArrayList<Container> containers = new ArrayList<Container>();
        try {
            File fXmlFile = new File(path);
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(fXmlFile);

            doc.getDocumentElement().normalize();

            NodeList nList = doc.getElementsByTagName("record");

            for (int temp = 0; temp < nList.getLength(); temp++) {

                Node nNode = nList.item(temp);

                if (nNode.getNodeType() == Node.ELEMENT_NODE) {
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

                    arrival = new ContainerInfo(Integer.parseInt(eElement.getElementsByTagName("datum").item(0).getChildNodes().item(1).getTextContent()), Integer.parseInt(eElement
                            .getElementsByTagName("datum").item(0).getChildNodes().item(3).getTextContent()), Integer.parseInt(eElement.getElementsByTagName("datum").item(0).getChildNodes().item(5)
                            .getTextContent()), Double.parseDouble(eElement.getElementsByTagName("tijd").item(0).getChildNodes().item(1).getTextContent()), Double.parseDouble(eElement
                            .getElementsByTagName("tijd").item(0).getChildNodes().item(3).getTextContent()), TravelType.getTravelType(eElement.getElementsByTagName("soort_vervoer").item(0)
                            .getTextContent()), eElement.getElementsByTagName("bedrijf").item(0).getTextContent());

                    departure = new ContainerInfo(Integer.parseInt(eElement.getElementsByTagName("datum").item(1).getChildNodes().item(1).getTextContent()), Integer.parseInt(eElement
                            .getElementsByTagName("datum").item(1).getChildNodes().item(3).getTextContent()), Integer.parseInt(eElement.getElementsByTagName("datum").item(1).getChildNodes().item(5)
                            .getTextContent()), Double.parseDouble(eElement.getElementsByTagName("tijd").item(1).getChildNodes().item(1).getTextContent()), Double.parseDouble(eElement
                            .getElementsByTagName("tijd").item(1).getChildNodes().item(3).getTextContent()), TravelType.getTravelType(eElement.getElementsByTagName("soort_vervoer").item(1)
                            .getTextContent()), eElement.getElementsByTagName("bedrijf").item(1).getTextContent());

                    ownerName = eElement.getElementsByTagName("eigenaar").item(0).getChildNodes().item(1).getTextContent();
                    containerNumber = Integer.parseInt(eElement.getElementsByTagName("eigenaar").item(0).getChildNodes().item(3).getTextContent());

                    positie = new Vector3f(Float.parseFloat(eElement.getElementsByTagName("positie").item(0).getChildNodes().item(1).getTextContent()), Float.parseFloat(eElement
                            .getElementsByTagName("positie").item(0).getChildNodes().item(3).getTextContent()), Float.parseFloat(eElement.getElementsByTagName("positie").item(0).getChildNodes()
                            .item(5).getTextContent()));

                    String[] x = eElement.getElementsByTagName("afmetingen").item(0).getChildNodes().item(1).getTextContent().split("'");

                    String xLoc = "";

                    if (x.length > 1) {
                        xLoc = x[0].concat("." + x[1]);
                    } else {
                        xLoc = x[0];
                    }

                    String[] y = eElement.getElementsByTagName("afmetingen").item(0).getChildNodes().item(3).getTextContent().split("'");

                    String yLoc = "";

                    if (y.length > 1) {
                        yLoc = y[0].concat("." + y[1]);
                    } else {
                        yLoc = y[0];
                    }

                    String[] z = eElement.getElementsByTagName("afmetingen").item(0).getChildNodes().item(5).getTextContent().split("'");

                    String zLoc = "";

                    if (z.length > 1) {
                        zLoc = z[0].concat("." + z[1]);
                    } else {
                        zLoc = z[0];
                    }

                    afmetingen = new Vector3f(Float.parseFloat(xLoc), Float.parseFloat(yLoc), Float.parseFloat(zLoc));
                    gewichtLeeg = Integer.parseInt(eElement.getElementsByTagName("gewicht").item(0).getChildNodes().item(1).getTextContent());
                    gewichtVol = Integer.parseInt(eElement.getElementsByTagName("gewicht").item(0).getChildNodes().item(3).getTextContent());
                    inhoud = new ContainerInhoud(eElement.getElementsByTagName("inhoud").item(1).getChildNodes().item(1).getTextContent(), eElement.getElementsByTagName("inhoud").item(1)
                            .getChildNodes().item(3).getTextContent(), eElement.getElementsByTagName("inhoud").item(1).getChildNodes().item(5).getTextContent());
                    ISO = eElement.getElementsByTagName("ISO").item(0).getTextContent();
                    containers.add(new Container(arrival, departure, ownerName, containerNumber, positie, afmetingen, gewichtLeeg, gewichtVol, inhoud, ISO));

                    System.out.println(temp);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        for (Container c : containers) {
            ContainerInfo info1 = c.arrival;
            ContainerInfo info2 = c.departure;
            System.out.printf("AANKOMST: dag: %s maand: %s jaar: %s AankomstTijd: %s VertrekTijd: %s Soort Vervoer: %s Bedrijf: %s \n"
                    + "DEPARTURE: dag: %s maand: %s jaar: %s AankomstTijd: %s VertrekTijd: %s Soort Vervoer: %s Bedrijf: %s \n"
                    + "Overige Info: eigenaar: %s containerNummer: %s Positie: %s %s %s Afmetingen: %s %s %s \n" + "Meer Overige: gewicht leeg: %s gewicht vol: %s inhoud: %s %s %s ISO: %s \n \n",
                    info1.day, info1.month, info1.year, info1.arrivalTime, info1.departureTime, info1.type, info1.bedrijf, info2.day, info2.month, info2.year, info2.arrivalTime, info2.departureTime,
                    info2.type, info2.bedrijf, c.ownerName, c.containerNumber, c.positie.x, c.positie.y, c.positie.z, c.afmetingen.x, c.afmetingen.y, c.afmetingen.z, c.gewichtLeeg, c.gewichtVol,
                    c.inhoud.naam, c.inhoud.soort, c.inhoud.gevaar, c.ISO);
        }

        return containers;
    }

    /**
     * Class for the container (TODO: To be removed for a new object that is
     * cleaner)
     *
     * @author Arjen
     */
    public class Container {

        private ContainerInfo arrival, departure;
        private String ownerName;
        private int containerNumber;
        private Vector3f positie;
        private Vector3f afmetingen;
        private int gewichtLeeg, gewichtVol;
        private ContainerInhoud inhoud;
        private String ISO;

        public Container(ContainerInfo arrival, ContainerInfo departure, String ownerName, int containerNumber, Vector3f positie, Vector3f afmetingen, int gewichtLeeg, int gewichtVol,
                ContainerInhoud inhoud, String ISO) {
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

        public Vector3f getAfmetingen() {
            return afmetingen;
        }

        public ContainerInfo getArrival() {
            return arrival;
        }

        public int getContainerNumber() {
            return containerNumber;
        }

        public ContainerInfo getDeparture() {
            return departure;
        }

        public int getGewichtLeeg() {
            return gewichtLeeg;
        }

        public int getGewichtVol() {
            return gewichtVol;
        }

        public String getISO() {
            return ISO;
        }

        public ContainerInhoud getInhoud() {
            return inhoud;
        }

        public String getOwnerName() {
            return ownerName;
        }

        public Vector3f getPositie() {
            return positie;
        }
        
    }

    /**
     * info of the container (1 for arrival, 1 for departure)
     *
     * @author Arjen
     *
     */
    public class ContainerInfo {

        private int day, month, year;
        private double arrivalTime, departureTime;
        private TravelType type;
        private String bedrijf;

        public ContainerInfo(int day, int month, int year, double arrivalTime, double departureTime, TravelType type, String bedrijf) {
            this.day = day;
            this.month = month;
            this.year = year;
            this.arrivalTime = arrivalTime;
            this.departureTime = departureTime;
            this.type = type;
            this.bedrijf = bedrijf;
        }

        public double getArrivalTime() {
            return arrivalTime;
        }

        public String getBedrijf() {
            return bedrijf;
        }

        public int getDay() {
            return day;
        }

        public double getDepartureTime() {
            return departureTime;
        }

        public int getMonth() {
            return month;
        }

        public TravelType getType() {
            return type;
        }

        public int getYear() {
            return year;
        }
        
    }

    /**
     * the type the container has to travel with
     *
     * @author Arjen
     *
     */
    public enum TravelType {

        VRACHTAUTO, ZEESCHIP, BINNENSCHIP, TREIN;

        public static TravelType getTravelType(String s) {
            switch (s) {
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

    /**
     * the product in the container
     *
     * @author Arjen
     */
    public class ContainerInhoud {

        private String naam, soort, gevaar;

        public ContainerInhoud(String naam, String soort, String gevaar) {
            this.naam = naam;
            this.soort = soort;
            this.gevaar = gevaar;
        }

        public String getGevaar() {
            return gevaar;
        }

        public String getNaam() {
            return naam;
        }

        public String getSoort() {
            return soort;
        }
        
    }
}
