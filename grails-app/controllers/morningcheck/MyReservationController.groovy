package morningcheck

import oracle.jdbc.driver.DatabaseError

class MyReservationController {

    def index() {}

    def todaypresent(){
        def date = new Date()

        def today = ReservationNight.findAllByDateBetween(date-240,date)

        respond model:['todayList':today]
    }
}
