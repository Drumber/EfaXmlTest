package de.lars;

public class QueryUrl {

    String rootUrl;
    ParameterHolder ph;

    public QueryUrl(String efaRootUrl, ParameterHolder ph) {
        rootUrl = efaRootUrl;
        this.ph = ph;
        if(rootUrl.charAt(rootUrl.length()-1) != '/')
            rootUrl += '/';
    }

    //"http://www.efa-bw.de/nvbw/XML_TRIP_REQUEST2?sessionID=0&requestID=0&language=de&command=&execInst=&stateless=1&itdLPxx_frames=&useRealtime=1&locationServerActive=1&convertStopsPTKernel2LocationServer=1&convertAddressesITKernel2LocationServer=1&convertCoord2LocationServer=1&convertCrossingsITKernel2LocationServer=1&convertPOIsITKernel2LocationServer=1&anySigWhenPerfectNoOtherMatches=1&anyMaxSizeHitList=350&w_objPrefAm=2&w_regPrefAl=8&prMinQu=1&itOptionsActive=1&ptOptionsActive=1&prepareSpT=1&outputOptionsActive=1&coordListOutputFormat=STRING&type_origin=any&nameDefaultText_origin=Ort%2C+Haltestelle+oder+Adresse+oder+wichtiger+Punkt&nameInfo_origin=invalid&placeInfo_origin=invalid&typeInfo_origin=invalid&placeOMC_origin=&nameState_origin=empty&placeState_origin=empty&nameX_origin=&nameY_origin=&nameDisplay_origin=&typeDisplay_origin=&itdLPxx_id_origin=%3Aorigin&name_origin=Lahr+Bahnhof%2FZOB&type_destination=any&nameDefaultText_destination=Ort%2C+Haltestelle+oder+Adresse+oder+wichtiger+Punkt&nameInfo_destination=invalid&placeInfo_destination=invalid&typeInfo_destination=invalid&placeOMC_destination=&nameState_destination=empty&placeState_destination=empty&nameX_destination=&nameY_destination=&nameDisplay_destination=&typeDisplay_destination=&itdLPxx_id_destination=%3Adestination&name_destination=Offenburg+Bahnhof&itdDateDay=24&itdDateMonth=07&itdDateYear=2020&itdTimeHour=16&itdTimeMinute=35&itdTripDateTimeDepArr=dep&lineRestriction=400&routeType=LEASTTIME&changeSpeed=normal&type_via=any&nameDefaultText_via=Haltestelle%2C+Adresse+oder+wichtiger+Punkt&nameInfo_via=invalid&placeInfo_via=invalid&typeInfo_via=invalid&placeOMC_via=&nameState_via=empty&placeState_via=empty&nameX_via=&nameY_via=&nameDisplay_via=&typeDisplay_via=&itdLPxx_id_via=%3Avia&anyObjFilter_via=46&name_via=Haltestelle%2C+Adresse+oder+wichtiger+Punkt&place_via=&poi_main_via=1&poi_sub_via=1&poi_point_via=1&dwellTime=&dwellTimeHour=00&dwellTimeMinute=00&type_notVia=any&nameDefaultText_notVia=Haltestelle&nameInfo_notVia=invalid&placeInfo_notVia=invalid&typeInfo_notVia=invalid&placeOMC_notVia=&nameState_notVia=empty&placeState_notVia=empty&nameX_notVia=&nameY_notVia=&nameDisplay_notVia=&typeDisplay_notVia=&itdLPxx_id_notVia=%3AnotVia&anyObjFilter_notVia=2&name_notVia=Haltestelle&includedMeans=checkbox&inclMOT_0=1&inclMOT_1=1&inclMOT_3=1&inclMOT_2=1&inclMOT_4=1&inclMOT_5=1&inclMOT_6=1&inclMOT_7=1&inclMOT_8=1&inclMOT_9=1&inclMOT_10=1&inclMOT_11=1&maxChanges=9&useProxFootSearch=on&trITDepMOT=100&trITDepMOTvalue100=15&trITDepMOTvalue101=60&trITDepMOTvalue104=10&trITArrMOT=100&trITArrMOTvalue100=15&trITArrMOTvalue101=60&trITArrMOTvalue104=10";


    public String buildTripRequest() {
        // docs: https://www.opendata-oepnv.de/fileadmin/Dokumentationen_etc/VRR/Schnittstellendokumentation_EFA-XML_1_.pdf
        return rootUrl += "XML_TRIP_REQUEST2?sessionID=0&requestID=0&language=de&useRealtime=1" +
                "&anySigWhenPerfectNoOtherMatches=1&anyMaxSizeHitList=20&prMinQu=1" +
                "&itOptionsActive=1&ptOptionsActive=1&coordListOutputFormat=STRING&type_origin=any" +
                "&nameInfo_origin=invalid&placeInfo_origin=invalid&typeInfo_origin=invalid" +
                "&nameInfo_destination=invalid&placeInfo_destination=invalid&typeInfo_destination=invalid" +
                "&itdLPxx_id_destination=%3Adestination&type_via=any" +
                "&includedMeans=checkbox&inclMOT_0=1&inclMOT_1=1&inclMOT_3=1&inclMOT_2=1&inclMOT_4=1&inclMOT_5=1" +
                "&inclMOT_6=1&inclMOT_7=1&inclMOT_8=1&inclMOT_9=1&inclMOT_10=1&inclMOT_11=1&type_destination=any" +

                /* Start und End Station */
                format("&name_origin=%s", ph.getStartStationEncoded()) +
                format("&name_destination=%s", ph.getDestinationStationEncoded()) +
                /* Zeit */
                format("&itdDateDay=%d&itdDateMonth=%d&itdDateYear=%d&itdTimeHour=%d&itdTimeMinute=%d",
                        ph.day, ph.month, ph.year, ph.hour, ph.minute) +

                /* Abfahrtszeit=dep Ankunftszeit=arr */
                "&itdTripDateTimeDepArr=dep" +
                /* Zugtyp Filter: "alle=400" "ausser ICE=401" "Verbund ohne Aufschlag=402" "Verbund & Nahverk.=403" */
                "&lineRestriction=400" +
                /* Fahrtoptimierung Kriterium: "wenig Umstieg=leastinterchange" "schnelle=lasttime" "wenig Fussweg=leastwalking" */
                "&routeType=LEASTTIME" +
                /* Umsteigezeit: "fast, normal, slow" | max Umstiege: "0, 1, 2, 9" */
                "&changeSpeed=normal&maxChanges=9" +
                /* beruecksichtige nahe gelege Haltestellen */
                "&useProxFootSearch=on" +
                /* Transportmitteltyp fuer Weg zu Start und von Ende */
                "&trITDepMOT=100&trITDepMOTvalue100=15&trITDepMOTvalue101=60&trITDepMOTvalue104=10&trITArrMOT=100" +
                "&trITArrMOTvalue100=15&trITArrMOTvalue101=60&trITArrMOTvalue104=10";

    }

    /**
     * wrapper method for {@link java.lang.String#format}
     */
    private static String format(String s, Object... args) {
        return String.format(s, args);
    }

    public static class UrlBuilder {

        private String rootUrl;
        private ParameterHolder ph;

        public UrlBuilder(String efaRootUrl) {
            rootUrl = efaRootUrl;
            ph = new ParameterHolder();
        }

        public UrlBuilder startStation(String start) {
            ph.startStation = start;
            return this;
        }

        public UrlBuilder destinationStation(String destination) {
            ph.destinationStation = destination;
            return this;
        }

        public UrlBuilder time(int day, int month, int year, int hour, int minute) {
            ph.day = day;
            ph.month = month;
            ph.year = year;
            ph.hour = hour;
            ph.minute = minute;
            return this;
        }

        public QueryUrl build() {
            return new QueryUrl(rootUrl, ph);
        }

    }

}
