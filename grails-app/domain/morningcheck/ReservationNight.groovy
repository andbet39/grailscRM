package morningcheck

class ReservationNight {

    static constraints = {
        room(nullable: true)
    }
    int res_code
    Date date
    Room room
    Customer customer
    ReservationWobook baseReservation

}
