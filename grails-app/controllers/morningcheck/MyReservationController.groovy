package morningcheck

import grails.converters.JSON
import oracle.jdbc.driver.DatabaseError
import org.apache.xmlrpc.client.XmlRpcClient
import org.apache.xmlrpc.client.XmlRpcClientConfigImpl

import java.text.DateFormat
import java.text.SimpleDateFormat
import org.joda.time.format.DateTimeFormatter
import org.joda.time.format.DateTimeFormat
import org.joda.time.*

class MyReservationController {

    def index() {}


    def weeklyactivity(){

        LocalDate now = new LocalDate()
        LocalDate monday = now.withDayOfWeek(DateTimeConstants.MONDAY);
        LocalDate sunday = now.withDayOfWeek(DateTimeConstants.SUNDAY);

        List<ReservationNight> checkin = new ArrayList<>()
        List<ReservationNight> checkout = new ArrayList<>()

        def breakfast_price  = HotelParam.findByType("BREAKFAST_PRICE")

        def queryall = ReservationNight.where {
            date >= monday  &&  date <= sunday
         }

        DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");

        def alldays = queryall.list()
        def n_colazioni = 0

        for(dayres in alldays){
            log.info(formatter.format(dayres.date))

            //calcolo il checkin
            if( dayres.baseReservation.date_arrival == formatter.format(dayres.date) ){
                checkin.add(dayres)
            }

            //calcolo il checkout
            Calendar cal = Calendar.getInstance();
            cal.setTime(dayres.date);
            cal.add(Calendar.DATE, 1);
            log.info("-"+  formatter.format(cal.getTime()) )

            if( dayres.baseReservation.date_departure == formatter.format(cal.getTime()) ){
                checkout.add(dayres)
                log.info("Added Checkout")
            }

            //calcolo numero di colazioni
            n_colazioni += dayres.baseReservation.men + dayres.baseReservation.children

        }

        respond checkin,model:['checkin':checkin,
                               'checkout':checkout,
                               'n_colazioni':n_colazioni,
                               'costo_colazioni':n_colazioni*breakfast_price.value.toFloat()
        ]
    }

    def todayactivity(){

        Calendar cal = Calendar.getInstance();
        cal.setTime(new Date());
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);

        log.info(cal.time.toString())

        cal.add(Calendar.DATE, -2);
        Date fromMeno1 = cal.getTime();

        cal.add(Calendar.DATE, 2);
        Date from = cal.getTime();

        cal.add(Calendar.DATE, 1);
        Date to = cal.getTime();

        DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        log.info("Date fromMenu1 " + fromMeno1.toString())
        log.info("Date from " + from.toString())
        log.info("Date to " +  to.toString())

        def today = ReservationNight.findAllByDateBetween(from,to)

        def query_ci = ReservationNight.where {
            date >= fromMeno1  &&  date < to
            baseReservation.date_arrival == formatter.format(from)
        }


        def query_co = ReservationNight.where {
            date < from && date > fromMeno1
            baseReservation.date_departure == formatter.format(from)
        }

        def checkin = query_ci.list()
        def checkout = query_co.list()

        respond today,model:['todayList':today, 'checkin':checkin,'checkout':checkout]

    }


    def montlypresence(){


        def montly = ReservationNight.findAllByDateBetween(dateBefore1Days,new Date())


    }
}
