package morningcheck

class ReservationNight {

    static constraints = {
        room(nullable: true)
    }

    Date date
    Room room
    Customer customer
    Reservation baseReservation

}
