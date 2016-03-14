package morningcheck

class Reservation {

    static constraints = {
        origincode nullable : true
        reservationDate nullable: true
        bid nullable: true
        arrivalHour nullable: true
        notes nullable: true
        discount nullable: true
        roomsAmount nullable: true
        guestEmail nullable: true
        guestPhone nullable: true

    }

    static mapping =  {
        notes type: 'text'

    }

    String wubookcode
    String origincode
    String reservationDate
    String arrivalDate
    String departureDate
    String guestName
    String guestEmail
    String guestPhone
    String arrivalHour
    String price
    String roomsAmount
    String status
    String notes
    String origin
    String discount
    String bid
}
