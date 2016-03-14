package morningcheck

import grails.transaction.Transactional
import org.joda.time.*
import org.joda.time.format.DateTimeFormatter
import org.joda.time.format.DateTimeFormat
import java.util.Date;

@Transactional
class ReservationService {


    def extractCustomer (ReservationWobook res){


        def cust =  Customer.findAllByEmail(res.customer_mail)

        if (!cust ){

            def customer =  new Customer(name: res.customer_name,
                                    surname:res.customer_surname,
                                    phone: res.customer_phone,
                                    email: res.customer_mail,
                                    language: res.customer_language,
                                    address:res.customer_mail,
                                zip:res.customer_zip,
                                country: res.customer_country)


            customer.save(flush:true)
            return customer
        }

        return cust

    }



   def extractReservation(){

       def allres = ReservationWobook.findAll{
           status=="1" || status =="4"
       }

       for (res in allres){

           def old=ReservationNight.findByRes_code(res.reservation_code)

           if(old == null) {

               DateTimeFormatter formatter = DateTimeFormat.forPattern("dd/MM/yyyy");
               DateTime start_dt = formatter.parseDateTime(res.date_arrival);
               DateTime end_dt = formatter.parseDateTime(res.date_departure);

               log.info("before customer extraction")
               def cust = extractCustomer(res)
               log.info(cust.email)

               List<String> roomList = Arrays.asList(res.rooms.split(","));

               for (DateTime date = start_dt; date.isBefore(end_dt); date = date.plusDays(1)) {

                   java.util.Date d = date.toDate()
                   log.info(date)


                   for (room in roomList) {
                       log.info("Fetching room " + room)
                       def r = Room.findById(room)
                       def res_night = new ReservationNight(date: d, baseReservation: res, customer: cust, room: r,res_code: res.reservation_code)
                       res_night.save(flush: true)
                       if (res_night.errors)
                           log.info(res_night.errors)
                   }

               }
           }

       }

   }
}
