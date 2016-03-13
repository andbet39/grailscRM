package morningcheck

class Customer {

    static constraints = {
        last_name nullable:true
        nationality nullable:true
        language nullable:true

    }

    static mapping = {

    }

    String name
    String last_name
    String email
    String phone
    String nationality
    String language
    
}
