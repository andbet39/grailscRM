package morningcheck

class Room {
    static mapping = {
        id generator: 'assigned', name: "id", type: 'long'
    }
    static constraints = {
        shortname nullable:true
        occupancy nullable: true
         board nullable:true
        boards nullable:true
        woodoo nullable:true
        dec_avail nullable:true
        min_price nullable:true
        max_price nullable:true
    }



    long id
    String name
    String shortname
    String occupancy
    int men
    int children
    int subroom
    int anchorate
    float price
    int availability
    String board
    String boards
    String woodoo
    String dec_avail
    String  min_price
    String max_price


}
