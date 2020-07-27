package de.lars;

import org.json.JSONArray;
import org.json.JSONObject;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.XMLReaderFactory;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class EfaXmlTest {

    public static void main(String[] args) throws SAXException, IOException, ParserConfigurationException, TransformerException {
        String urlString = "http://www.efa-bw.de/nvbw/XML_STOPFINDER_REQUEST?sessionID=0&requestID=0&language=DE&coordOutputFormat=WGS84&place_sf=Gelsenkirchen&placeState_sf=empty&type_sf=stop&name_sf=Lukaskirche&nameState_sf=empty&itdDateYear=2020&itdDateMonth=7&itdDateDay=24&itdTimeHour=11&itdTimeMinute=9&coordOutputFormatTail=0";

//        urlString = "http://www.efa-bw.de/nvbw/XML_TRIP_REQUEST2?sessionID=0&requestID=0&language=de&command=&execInst=&stateless=1&itdLPxx_frames=&useRealtime=1&locationServerActive=1&convertStopsPTKernel2LocationServer=1&convertAddressesITKernel2LocationServer=1&convertCoord2LocationServer=1&convertCrossingsITKernel2LocationServer=1&convertPOIsITKernel2LocationServer=1&anySigWhenPerfectNoOtherMatches=1&anyMaxSizeHitList=350&w_objPrefAm=2&w_regPrefAl=8&prMinQu=1&itOptionsActive=1&ptOptionsActive=1&prepareSpT=1&outputOptionsActive=1&coordListOutputFormat=STRING&type_origin=any&nameDefaultText_origin=Ort%2C+Haltestelle+oder+Adresse+oder+wichtiger+Punkt&nameInfo_origin=invalid&placeInfo_origin=invalid&typeInfo_origin=invalid&placeOMC_origin=&nameState_origin=empty&placeState_origin=empty&nameX_origin=&nameY_origin=&nameDisplay_origin=&typeDisplay_origin=&itdLPxx_id_origin=%3Aorigin&name_origin=Lahr+Bahnhof%2FZOB&type_destination=any&nameDefaultText_destination=Ort%2C+Haltestelle+oder+Adresse+oder+wichtiger+Punkt&nameInfo_destination=invalid&placeInfo_destination=invalid&typeInfo_destination=invalid&placeOMC_destination=&nameState_destination=empty&placeState_destination=empty&nameX_destination=&nameY_destination=&nameDisplay_destination=&typeDisplay_destination=&itdLPxx_id_destination=%3Adestination&name_destination=Offenburg+Bahnhof&itdDateDay=24&itdDateMonth=07&itdDateYear=2020&itdTimeHour=16&itdTimeMinute=35&itdTripDateTimeDepArr=dep&lineRestriction=400&routeType=LEASTTIME&changeSpeed=normal&type_via=any&nameDefaultText_via=Haltestelle%2C+Adresse+oder+wichtiger+Punkt&nameInfo_via=invalid&placeInfo_via=invalid&typeInfo_via=invalid&placeOMC_via=&nameState_via=empty&placeState_via=empty&nameX_via=&nameY_via=&nameDisplay_via=&typeDisplay_via=&itdLPxx_id_via=%3Avia&anyObjFilter_via=46&name_via=Haltestelle%2C+Adresse+oder+wichtiger+Punkt&place_via=&poi_main_via=1&poi_sub_via=1&poi_point_via=1&dwellTime=&dwellTimeHour=00&dwellTimeMinute=00&type_notVia=any&nameDefaultText_notVia=Haltestelle&nameInfo_notVia=invalid&placeInfo_notVia=invalid&typeInfo_notVia=invalid&placeOMC_notVia=&nameState_notVia=empty&placeState_notVia=empty&nameX_notVia=&nameY_notVia=&nameDisplay_notVia=&typeDisplay_notVia=&itdLPxx_id_notVia=%3AnotVia&anyObjFilter_notVia=2&name_notVia=Haltestelle&includedMeans=checkbox&inclMOT_0=1&inclMOT_1=1&inclMOT_3=1&inclMOT_2=1&inclMOT_4=1&inclMOT_5=1&inclMOT_6=1&inclMOT_7=1&inclMOT_8=1&inclMOT_9=1&inclMOT_10=1&inclMOT_11=1&maxChanges=9&useProxFootSearch=on&trITDepMOT=100&trITDepMOTvalue100=15&trITDepMOTvalue101=60&trITDepMOTvalue104=10&trITArrMOT=100&trITArrMOTvalue100=15&trITArrMOTvalue101=60&trITArrMOTvalue104=10";
//
//        URL url = new URL(urlString);
//        URLConnection conn = url.openConnection();
//
//        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
//        DocumentBuilder builder = factory.newDocumentBuilder();
//        Document doc = builder.parse(conn.getInputStream());
//
//        TransformerFactory transformerFactory= TransformerFactory.newInstance();
//        Transformer xform = transformerFactory.newTransformer();
//
//        // that’s the default xform; use a stylesheet to get a real one
//        xform.transform(new DOMSource(doc), new StreamResult(System.out));

        test();
    }

    static void test() {
        Scanner scanner = new Scanner(System.in);
        String rootUrl = "http://www.efa-bw.de/nvbw/";

        // input from console
        System.out.print("Start Station: ");
        String start = scanner.nextLine();
        System.out.print("End Station: ");
        String end = scanner.nextLine();
        System.out.print("Tag: ");
        int day = scanner.nextInt();
        System.out.print("Monat: ");
        int month = scanner.nextInt();
        System.out.print("Jahr: ");
        int year = scanner.nextInt();
        System.out.print("Stunde: ");
        int hour = scanner.nextInt();
        System.out.print("Minute: ");
        int minute = scanner.nextInt();

        QueryUrl qurl = new QueryUrl.UrlBuilder(rootUrl)
                .startStation(start)
                .destinationStation(end)
                .time(day, month, year, hour, minute)
                .build();

        String urlString = qurl.buildTripRequest();

        try {
            System.out.println("Parsing XML from " + urlString);
            Document doc = parseXML(urlString);
            System.out.println("Response...");
            printXML(doc);
            System.out.println("\n\nVersuche Zugdaten aus XML zu parsen...");

            TripParameterHolder[][] tph = printRoutes(doc);

            if(tph != null && tph.length > 0) {
                System.out.print("Fahrtinformation von Index: ");
                int tripIndex = scanner.nextInt();
                if(tripIndex >= tph.length) {
                    System.err.println("Invalid index.");
                    return;
                }
                TripParameterHolder[] paramHolder = tph[tripIndex];

                for(int i = 0; i < paramHolder.length; i++) {
                    String stopTimesUrl = qurl.buildTripStopTimesRequest(paramHolder[i]);

                    System.out.println("Versuche Fahrtinfo JSON anzufordern: " + stopTimesUrl);
                    String json = parseJSON(stopTimesUrl);
                    System.out.println(json);

                    System.out.println("Versuche Fahrtverlauf aus JSON zu parsen...");
                    printJSONStopTimes(json);

                    if(i+1 < paramHolder.length)
                        System.out.println("\n----------\n");
                }
            }

        } catch (IOException | ParserConfigurationException | SAXException e) {
            e.printStackTrace();
        }
    }

    static Document parseXML(String urlString) throws IOException, ParserConfigurationException, SAXException {
        URL url = new URL(urlString);
        URLConnection conn = url.openConnection();

        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        return builder.parse(conn.getInputStream());
    }

    static String parseJSON(String urlString) throws IOException {
        URL url = new URL(urlString);
        URLConnection conn = url.openConnection();

        BufferedReader bf = new BufferedReader(new InputStreamReader(conn.getInputStream(), StandardCharsets.UTF_8));
        StringBuilder sb = new StringBuilder();
        int cp;
        while ((cp = bf.read()) != -1) {
            sb.append((char) cp);
        }
        return sb.toString();
    }

    static void printXML(Document doc) {
        try {
            TransformerFactory transformerFactory= TransformerFactory.newInstance();
            Transformer xform = transformerFactory.newTransformer();
            xform.transform(new DOMSource(doc), new StreamResult(System.out));
        } catch (TransformerException e) {
            e.printStackTrace();
        }
    }

    static TripParameterHolder[][] printRoutes(Document doc) {
        doc.getDocumentElement().normalize();

        if(!checkValidity(doc)) {
            System.err.println("Konnte Fahrten nicht laden. Bitte versuche es erneut.");
            return null;
        }

        // get route list
        NodeList routes = doc.getElementsByTagName("itdRoute");
        // save parameter of trips
        TripParameterHolder[][] tph = new TripParameterHolder[routes.getLength()][];

        for(int i = 0; i < routes.getLength(); i++) {
            System.out.println("====================");
            Node routeNode = routes.item(i);                        // <itdRouteList>
                                                                    //      <itdRoute>...
            if(routeNode.getNodeType() == Node.ELEMENT_NODE) {
                Element re = (Element) routeNode; // itdRoute element

                System.out.println("[" + i + "] Umsteigen: " + re.getAttribute("changes") + "; " +
                        "Dauer: " + re.getAttribute("publicDuration"));

                NodeList routeList = re.getElementsByTagName("itdPartialRoute");
                for(int pr = 0; pr < routeList.getLength(); pr++) {
                    if(routeList.item(pr).getNodeType() == Node.ELEMENT_NODE) {

                        // <itdPartialRoute>
                        Element partialRoute = (Element) routeList.item(pr);

                        // transport type
                        Element transp = (Element) partialRoute.getElementsByTagName("itdMeansOfTransport").item(0);
                        // delay time
                        Element itdRBLControlled = (Element) partialRoute.getElementsByTagName("itdRBLControlled").item(0);
                        // foot path info
                        Element footPathInfo = (Element) partialRoute.getElementsByTagName("itdFootPathInfo").item(0);

                        // save trip line params
                        tph[i] = new TripParameterHolder[routeList.getLength()];
                        tph[i][pr] = new TripParameterHolder();
                        tph[i][pr].line = transp.getAttribute("stateless");
                        tph[i][pr].tripCode = pint(transp.getAttribute("tC"));

                        // print route overview
                        String delay = "";
                        if(itdRBLControlled != null)
                            delay = itdRBLControlled.getAttribute("delayMinutes") + " Min. Verspätung";
                        System.out.println("  "+ transp.getAttribute("name") + " Richtung " + transp.getAttribute("destination") +
                                " (Dauer: " + partialRoute.getAttribute("timeMinute") + " Min.) " + delay);

                        // itdPoint origin + destination > STATIONS
                        NodeList itdPoints = partialRoute.getElementsByTagName("itdPoint");
                        for(int p = 0; p < itdPoints.getLength(); p++) {
                            if(itdPoints.item(p).getNodeType() == Node.ELEMENT_NODE) {

                                // Station <itdPoint>
                                Element point = (Element) itdPoints.item(p);

                                // time
                                String time = "";
                                Node targetDateTimeN = point.getElementsByTagName("itdDateTimeTarget").item(0);
                                if(targetDateTimeN.getNodeType() == Node.ELEMENT_NODE) {
                                    Element targetTime = (Element) targetDateTimeN;
                                    Element timeEl = (Element) targetTime.getElementsByTagName("itdTime").item(0);
                                    Element dateEl = (Element) targetTime.getElementsByTagName("itdDate").item(0);

                                    time = timeEl.getAttribute("hour") + ":" + timeEl.getAttribute("minute");

                                    // save line params
                                    if(p == 0) {
                                        tph[i][pr].stopID = pint(point.getAttribute("stopID"));
                                        tph[i][pr].hour = pint(timeEl.getAttribute("hour"));
                                        tph[i][pr].minute = pint(timeEl.getAttribute("minute"));
                                        tph[i][pr].day = pint(dateEl.getAttribute("day"));
                                        tph[i][pr].month = pint(dateEl.getAttribute("month"));
                                        tph[i][pr].year = pint(dateEl.getAttribute("year"));
                                    }
                                    if(p == itdPoints.getLength()-1) {
                                        // save last stop ID
                                        tph[i][pr].lastStopID = pint(point.getAttribute("stopID"));
                                    }
                                }
                                // print
                                System.out.println("    > " + time + "\t" + point.getAttribute("name") + " " +
                                        point.getAttribute("pointType") + " " + point.getAttribute("plannedPlatformName"));

                            }
                        }

                        // print foot path if exists
                        if(footPathInfo != null) {
                            System.out.println("  ! Fussweg: " + footPathInfo.getAttribute("duration") + " Min.");
                        }

                        if(pr + 1 < routeList.getLength()) {
                            System.out.println("  -----");
                        }
                    }

                }

            }
        }
        return tph;
    }

    static boolean checkValidity(Document doc) {
        boolean identified = false;
        // get all <itdOdv> | ODV = Origin-Destination-Via
        NodeList odvList = doc.getElementsByTagName("itdOdv");
        for(int i = 0; i < odvList.getLength(); i++) {
            if(odvList.item(i).getNodeType() == Node.ELEMENT_NODE) {
                Element odv = (Element) odvList.item(i);
                String usage = odv.getAttribute("usage");
                // check if origin and destination state is 'identified'
                if(usage != null && (usage.equals("origin") || usage.equals("destination"))) {

                    Element odvName = (Element) odv.getElementsByTagName("itdOdvName").item(0);
                    if(odvName != null) {
                        String odvState = odvName.getAttribute("state");
                        if(odvState != null && odvState.equals("identified")) {
                            identified = true;
                            continue;
                        }
                        System.out.println("TTESSST " + identified);
                        identified = false;

                        // station is not identified, print proposed locations
                        Element odvInput = (Element) odvName.getElementsByTagName("odvNameInput").item(0);
                        if(odvInput != null)
                            System.err.println("Kein Treffer für \"" + odvInput.getTextContent() + "\".");

                        NodeList odvElementList = odvName.getElementsByTagName("odvNameElem");
                        if(odvElementList.getLength() > 0) {
                            String[] proposedLocs = new String[odvElementList.getLength()];
                            for(int el = 0; el < odvElementList.getLength(); el++) {
                                if(odvElementList.item(el).getNodeType() == Node.ELEMENT_NODE) {
                                    Element element = (Element) odvElementList.item(el);
                                    proposedLocs[el] = element.getTextContent();
                                }
                            }
                            System.err.println("Vorschläge: " + String.join("\n", proposedLocs));
                        }
                    }

                }
            }
        }
        return identified;
    }

    static void printJSONStopTimes(String jsonString) {
        JSONObject jsonRoot = new JSONObject(jsonString);
        JSONArray arrParameters = jsonRoot.getJSONArray("parameters");

        int lastStopID = -1;
        int firstStopID = pint(jsonRoot.getJSONObject("vehicleCallAtStop").getString("stopID"));

        for(int i = 0; i < arrParameters.length(); i++) {
            JSONObject objParam = arrParameters.getJSONObject(i);
            if(objParam.has("name") && objParam.getString("name").equals("lastStopID"))
                lastStopID = pint(objParam.getString("value"));
        }

        JSONArray arrStopSeq = jsonRoot.getJSONArray("stopSeq");
        boolean targetStops = false;
        for(int i = 0; i < arrStopSeq.length(); i++) {

            JSONObject objStop = arrStopSeq.getJSONObject(i);
            JSONObject objRef = objStop.getJSONObject("ref");
            int id = pint(objRef.getString("id"));
            if(id == firstStopID)
                targetStops = true;

            String stopName = objStop.getString("name");
            String time = ">";
            if(!targetStops)
                time = " ";

            SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd HH:mm");
            try {
                String timeStr = objRef.has("arrDateTime") ? objRef.getString("arrDateTime") : objRef.getString("depDateTime");
                Date date = sdf.parse(timeStr);
                sdf = new SimpleDateFormat("HH:mm");
                time += sdf.format(date);
            } catch (ParseException e) {
                e.printStackTrace();
            }

            System.out.println(time + " " + stopName);

            if(id == lastStopID)
                targetStops = false;
        }

    }

    /** parse int */
    private static int pint(String s) {
        return Integer.parseInt(s);
    }

}
