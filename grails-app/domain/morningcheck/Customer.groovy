package morningcheck

class Customer {

    static constraints = {
        surname nullable:true
        nationality nullable:true
        language nullable:true
        city nullable:true
        zip nullable:true
        address nullable:true
        phone nullable:true
        country nullable:true
    }

    static mapping = {

    }

    String name
    String surname
    String email
    String phone
    String nationality
    String language
    String zip
    String address
    String city
    String country
    
}
