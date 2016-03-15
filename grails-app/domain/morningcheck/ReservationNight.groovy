package morningcheck

class ReservationNight {

    static constraints = {
        room(nullable: true)
    }

    int res_code
    Date date


    static belongsTo = [baseReservation:ReservationWobook , customer:Customer, room:Room]

}
