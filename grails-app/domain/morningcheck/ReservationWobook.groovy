package morningcheck

class ReservationWobook {
    static mapping = {
        reservation_code generator: 'assigned', name: "reservation_code", type: 'long'
    }

    static constraints = {
        status nullable:true
        channel_reservation_code nullable:true
        id_channel  nullable:true
        fount  nullable:true
        modified_reservations nullable:true
        was_modified nullable:true
        amount nullable:true
        booked_rate nullable:true
        orig_amount nullable:true
        amount_reason nullable:true
        date_received nullable:true
        date_arrival nullable:true
        date_departure nullable:true
        arrival_hour nullable:true
        boards  nullable:true
        status_reason nullable:true
         sessionSeed nullable:true
        customer_city nullable:true
        customer_country nullable:true
        customer_mail nullable:true
        customer_name nullable:true
        customer_surname nullable:true
        customer_notes  nullable:true
        customer_phone nullable:true
        customer_address  nullable:true
        customer_language  nullable:true
        customer_zip  nullable:true
        rooms nullable:true
        roomnight nullable:true
        room_opportunities nullable:true
        opportunities nullable:true
        dayprices nullable:true
        special_offer nullable:true
        addons_list nullable:true
        rooms_occupancies nullable:true
        discount nullable:true
        mandatory_costs nullable:true
        payment_gateway_fee nullable:true
        forced_price nullable:true
        booked_rooms nullable:true
        ancillary nullable:true
        device nullable:true

    }


    long reservation_code
    String status
    String channel_reservation_code
    String id_channel
    String fount
    String modified_reservations
    String was_modified
    String amount
    String booked_rate
    String orig_amount
    String amount_reason
    String date_received
    String date_arrival
    String date_departure
    String arrival_hour
    String boards
    String status_reason
    int men
    int children
    String sessionSeed
    String customer_city
    String customer_country
    String customer_mail
    String customer_name
    String customer_surname
    String customer_notes
    String customer_phone
    String customer_address
    String customer_language
    String customer_zip
    String rooms
    String roomnight
    String room_opportunities
    String opportunities
    String dayprices
    String special_offer
    String addons_list
    String rooms_occupancies
    String discount
    String mandatory_costs
    String payment_gateway_fee
    String forced_price
    String booked_rooms
    String ancillary
    String device

}
