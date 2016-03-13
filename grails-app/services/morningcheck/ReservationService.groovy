package morningcheck

import grails.transaction.Transactional
import org.joda.time.*
import org.joda.time.format.DateTimeFormatter
import org.joda.time.format.DateTimeFormat
import java.util.Date;

@Transactional
class ReservationService {


    def extractCustomer (Reservation res){


        def cust =  Customer.findAllByEmail(res.guestEmail)

        if (!cust ){

            def customer =  new Customer(name: res.guestName,phone: res.guestPhone,email: res.guestEmail)

            customer.save(flush:true)
            return customer
        }

        return cust

    }



   def extractReservation(){

       def allres = Reservation.findAll{
           status=="Accepted" || status =="Confirmed"
       }

       for (res in allres){


           DateTimeFormatter formatter =  DateTimeFormat.forPattern("dd/MM/yyyy");
           DateTime start_dt = formatter.parseDateTime(res.arrivalDate);
           DateTime end_dt = formatter.parseDateTime(res.departureDate);


           def cust =  Customer.findByEmail(res.guestEmail)

           if (cust == null){

               def new_cust =  new Customer(name: res.guestName,phone: res.guestPhone,email: res.guestEmail,nationality: "",language: "",last_name: "")

               new_cust.save(flush:true)

               cust=new_cust
               log.info(cust.name)
           }

           for (DateTime date = start_dt; date.isBefore(end_dt); date = date.plusDays(1))
           {

               java.util.Date d = date.toDate()
               log.info(date)


                def res_night =  new ReservationNight(date:d,baseReservation: res,customer:cust)
                res_night.save(flush:true)
               if(res_night.errors)
               log.info(res_night.errors)
           }

       }

   }
}
