package PCComms;

import java.awt.Point;
import java.util.ArrayList;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import pc.data.MapImpl;

public class MapParser {

    private MapImpl loadMap;
    private MapImpl saveMap = null;
    private boolean attribute = false;
    private boolean key = false;
    private boolean value = false;
    private boolean boundary = false;
    private boolean robotStatus = false;
    private boolean zones = false;
    private boolean areas = false;
    private boolean unExploredArea = false;
    private boolean disasterArea = false;
    private boolean rectangles = false;
    private boolean circles = false;
    private boolean structures = false;
    private boolean roads = false;
    private boolean intersections = false;
    private boolean obstacles = false;
    private boolean closures = false;  
    private boolean points = false;
    //
    private int x, y;
    private int x1, y1, x2, y2;
    private int w, h;
    private float radius;
    private String zoneStatus = "";
    private boolean validMap = false;
    //
    private String mapUnits = "";
    ArrayList<pointM> boundaryList = new ArrayList<pointM>();
    ArrayList<pointM> rectangleList = new ArrayList<pointM>();
    ArrayList<pointM> circleList = new ArrayList<pointM>();
    ArrayList<pointM> lineList = new ArrayList<pointM>();
    ArrayList<pointM> areaList = new ArrayList<pointM>();
    ArrayList<pointM> roadList = new ArrayList<pointM>();
    ArrayList<pointM> intersectionList = new ArrayList<pointM>();
    ArrayList<pointM> obstacleList = new ArrayList<pointM>();
    ArrayList<Point> disasterList = new ArrayList<Point>();

    public class pointM {

        int x, y;

        public pointM(int w, int h) {
            x = w;
            y = h;
        }
    }

    public MapImpl readXML(String filename) {
        try {
        	
        	MapImpl temp;
        	
            SAXParserFactory sf = SAXParserFactory.newInstance();
            SAXParser sp = sf.newSAXParser();

            DefaultHandler handler = new DefaultHandler() {

                public void startDocument() throws SAXException {
                    System.out.println("Start parsing Document :");
                }

                public void endDocument() throws SAXException {
                    System.out.println("Finish parsing Document");
                }

                public void startElement(String uri, String localName, String qName,
                        Attributes attributes) throws SAXException {

                    if (qName.equals("closure-map")) {
                        validMap = true;
                        loadMap = new MapImpl();
                        mapUnits = attributes.getValue(0);
                        //read the Units of the map as mm, cm, m...
                        System.out.println("mapUnits = " + mapUnits);

                    }
                    if (qName.equals("attribute")) {
                        attribute = true;
                    }
                    if (qName.equals("key")) {
                        key = true;
                    }

                    if (qName.equals("value")) {
                        value = true;
                    }

                    if (qName.equals("boundary")) {
                        boundary = true;
                    }

                    if (qName.equals("robot-status")) {

                        roboStat = true;
                    }

                    if (qName.equals("zone")) {
                        zones = true;
                        zoneStatus = attributes.getValue(0);
                        System.out.println("zoneStatus = " + zoneStatus);
                        if (zoneStatus.equals("unexplored")) {
                        	unexploredArea = true;
                        } else if (zoneStatus.equals("disaster")) {
                        	disasterArea = true;
                        }
                    }

                    if (qName.equals("structures")) {
                        structures = true;
                        // loadMap.point.aboveProb = 1;
                    }

                    if (qName.equals("road")) {
                        roads = true;
                        //loadMap.point.aboveProb = 1;
                    }

                    if (qName.equals("intersection")) {
                        intersections = true;
                    }

                    if (qName.equals("area")) {
                        areas = true;
                    }
                    
                    if (qName.equals("obstacle")) {
                        obstacles = true;
                    }
                    
                    if (qName.equals("closure")) {
                        closures = true;
                    }
                    
                    if (qName.equals("rect")) {
                    	rectangles = true;
                        x = Integer.parseInt(attributes.getValue(0));
                        y = Integer.parseInt(attributes.getValue(1));
                        w = Integer.parseInt(attributes.getValue(2));
                        h = Integer.parseInt(attributes.getValue(3));

                        System.out.println("rectangle corrdinates: " + "X: " + x + " Y: " + y + " size: " + "Width: "+ w + " Height: " + h);

                        if (boundary == true) {
                        	
                            loadMap = new MapImpl(w, h);
                            // do something here for boundary in MapImpl
                            System.out.println("boundary: " + "x= " + x + " y= " + y + " size: " + w + " * " + h);
                        } else if (areas == true) {
                        	if ((zones == true) && (unexploredArea == true)) {
                        		// do something here for unexplored area in MapImpl
                        	}
                        } else if (structures == true) {
                        	if (obstacles == true) {
                        		
                        		//do something for rectangle obstacles in MapImpl
                        	}
                        }
                    }
                    
                    if (qName.equals("line")) {
                        lines = true;
                        x1 = Integer.parseInt(attributes.getValue(0));
                        y1 = Integer.parseInt(attributes.getValue(1));
                        x2 = Integer.parseInt(attributes.getValue(2));
                        y2 = Integer.parseInt(attributes.getValue(3));

                        System.out.println("line corrdinates: " + "X1 " + x1 + " Y1 " + y1 + " X2 " + x2 + "  Y2 " + y2);

                        if (structures == true) {
                        	if (roads == true) {
                        		
                        		// do something for roads in MapImpl
                        		
                        	} else if (obstacles == true) {
                        		
                        		// do something for obstacles in MapImpl
                        		
                        	}
                        }
                    }

                    if (qName.equals("circle")) {
                        circles = true;
                        x = Integer.parseInt(attributes.getValue(0));
                        y = Integer.parseInt(attributes.getValue(1));
                        radius = Float.parseFloat(attributes.getValue(2));

                        System.out.println("circle corrdinates: " + "x: " + x + " y " + y + " radius " + radius);
                        
                        if (structures == true) {
                        	if ()
                        }
                    }

                    if (qName.equals("point")) {
                        points = true;
                        x = Integer.parseInt(attributes.getValue(0));
                        y = Integer.parseInt(attributes.getValue(1));

                        pointM pm = new pointM(x, y);

                        Point p = new Point();
                        p.x = x;
                        p.y = y;

                        if (roboStat == true) {

                            // add robot-status
                           loadMap.getMapPoint(x, y).isRobotLocation = true;
                           

                            System.out.println("robo-status: " + "x = " + x + " y = " + y);
                           
                        } else if (intersections == true) {
                        	
                        }
                           
                    }

                }

                public void endElement(String uri, String localName,
                        String qName) throws SAXException {

                    //System.out.println("End Element :" + qName);

                    if (qName.equals("closure-map")) {
                        validMap = false;
                    }

                    if (qName.equals("attribute")) {
                        attribute = false;
                    }

                    if (qName.equals("key")) {
                        key = false;
                    }

                    if (qName.equals("value")) {
                        value = false;
                    }

                    if (qName.equals("boundary")) {

                 


                        boundary = false;
                    }

                    if (qName.equals("robot-status")) {

                        roboStat = false;
                    }
                    if (qName.equals("zone")) {

                       
                    }

                    

                    if (qName.equals("line")) {
                        lines = false;


                    }

                    if (qName.equals("circle")) {
                        circles = false;

                    }

                    if (qName.equals("area")) {
                    }


                    if (qName.equals("area")) {

                        areas = false;
                    }

                    if (qName.equals("point")) {
                        points = false;
                    }

                }

                public void characters(char ch[], int start, int length) throws SAXException {

                    String s = new String(ch, start, length);

                    if (attri == true) {
                        if (validMap) {
                            if (key == true) {
                                String keyValue = s;
                                System.out.println("key:" + keyValue);
                            } else if (value == true) {
                                String keyDate = s;
                                validMap = false;

                                System.out.println("create date: " + keyDate);
                                // set map  arttibute 
                            }
                        }
                        } else if (roboStat) {
                            if (key == true) {

                                String robotFacing = s;

                                System.out.println("robotFacing direction: " + robotFacing);


                            } else if (value == true) {

                                double facingDegree = Double.parseDouble(s);

                                loadMap.setRobotFacing(facingDegree);

                                System.out.println("facingDegree: " + facingDegree);
                            }
                        } else if (areas) {

                            if (key == true) {

                                String wallAttribute1 = s;
                                //debug
                                // System.out.println("OK");
                                System.out.println("area Attribute1: " + wallAttribute1);

                            } else if (value == true) {
                                String wallAttribute2 = s;

                                System.out.println("area Attribute2: " + wallAttribute2);
                            }
                        } else if (zones) {
                            if (key == true) {

                                String zoneAttribute1 = s;

                                System.out.println(" zone Attribute1: " + zoneAttribute1);
                            } else if (value == true) {
                                String zoneAttribute2 = s;

                                System.out.println(" zone Attribute2: " + zoneAttribute2);
                            }
                        }
                    }
                }
            };

           // sp.parse("D:\\mapping_data.xml", handler);
             sp.parse(filename, handler);
            //return true;
        } catch (Exception e) {
            System.out.println("not a valid XML file" + e.toString());
            e.printStackTrace();
        }

        return loadMap;
        // return false;
    }   
    public static void main(String argv[]) {
        MapParser mp = new MapParser();

        mp.readXML("mapping_data.xml");
    }	

}
