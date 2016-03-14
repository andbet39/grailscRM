package morningcheck

import grails.converters.JSON
import oracle.jdbc.driver.DatabaseError

class MyReservationController {

    def index() {}

    def todaypresent(){

        Calendar cal = Calendar.getInstance();
        cal.setTime(new Date());
        cal.add(Calendar.DATE, -3);
        Date dateBefore1Days = cal.getTime();

        def today = ReservationNight.findAllByDateBetween(dateBefore1Days,new Date())

        respond today,model:['todayList':today]

    }
}
