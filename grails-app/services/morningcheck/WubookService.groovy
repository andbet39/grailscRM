package morningcheck

import grails.transaction.Transactional


import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.apache.tools.ant.types.optional.ScriptSelector;
import org.apache.xmlrpc.client.XmlRpcClient;
import org.apache.xmlrpc.client.XmlRpcClientConfigImpl;

import java.io.StringReader;
import java.io.Reader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


@Transactional
class WubookService {

    static transactional = true

    def token
    def lcode='1377875938';



    def aquireToken(){

        if(token == null) {
            XmlRpcClientConfigImpl config = new XmlRpcClientConfigImpl();
            config.setServerURL(new URL("https://wubook.net/xrws"));
            XmlRpcClient client = new XmlRpcClient();
            client.setConfig(config);


            Vector<String> params = new Vector<String>();
            params.addElement("AT035");
            params.addElement("Luglio2015");
            params.addElement("bamboo:rome");

            Object[] objects = (Object[]) client.execute(config, "acquire_token", params);

            log.info(objects[1])

            token = objects[1]
        }
     }

    def aquirerooms(){

        aquireToken()

        XmlRpcClientConfigImpl config = new XmlRpcClientConfigImpl();
        config.setServerURL(new URL("https://wubook.net/xrws"));
        XmlRpcClient client = new XmlRpcClient();
        client.setConfig(config);


        Vector<String> params = new Vector<String>();
        params.addElement(token);
        params.addElement(lcode);
        params.addElement("0");

        Object[] objects = (Object[]) client.execute(config, "fetch_rooms", params);



        for(room in objects[1]){
            log.info(room.id)
            def newroom = new Room(room)
            log.info(newroom)

            newroom.save(flush:true);
            log.error(newroom.errors.toString())
        }


    }
    def aquirereservation(){

        aquireToken()

        XmlRpcClientConfigImpl config = new XmlRpcClientConfigImpl();
        config.setServerURL(new URL("https://wubook.net/xrws"));
        XmlRpcClient client = new XmlRpcClient();
        client.setConfig(config);


        Vector<String> params = new Vector<String>();
        params.addElement(token);
        params.addElement(lcode);
        params.addElement("01/03/2016");
        params.addElement("30/04/2016");
        params.addElement("0");
        params.addElement("0");

        Object[] objects = (Object[]) client.execute(config, "fetch_bookings", params);


        for(reserv in objects[1]){
            log.info(reserv.rooms.toString())
            reserv.rooms_occupancies="";
            reserv.modified_reservations="";
            reserv.booked_rooms=""

            def reservation= new ReservationWobook(reserv)
            reservation.save(flush:true)
            log.error(reservation.errors.toString())

        }


    }

    def parseCSV(String csv) {

        StringReader stringreader = null;

        CSVParser csvFileParser = null;


        try {


            stringreader = new StringReader(csv)

            csvFileParser = new CSVParser(stringreader, CSVFormat.EXCEL);

            List csvRecords = csvFileParser.getRecords();

            for (int i = 1; i < csvRecords.size(); i++) {
                CSVRecord record = csvRecords.get(i);

                def res = new Reservation(wubookcode :record.get(0),
                                          origincode :record.get(1),
                                          reservationDate:record.get(2),
                                          arrivalDate:record.get(3),
                                          departureDate:record.get(4),
                                            guestName:record.get(5),
                                            guestEmail:record.get(6),
                                            guestPhone:record.get(7),
                                            arrivalHour:record.get(8),
                                            price: record.get(9),
                                            roomsAmount:record.get(10),
                                            status:record.get(11),
                                            notes:record.get(12),
                                            origin:record.get(13),
                                            discount:record.get(14),
                                            bid:record.get(15))
                log.info(res)
                res.save()

            }

        }
        catch (Exception e) {
            System.out.println("Error in CsvFileReader !!!");
            e.printStackTrace();
        }

        log.info("serviceMethod")

    }
}
