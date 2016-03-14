package morningcheck

import javax.xml.crypto.Data

class Check {
    static mapping = {
        table 'checks'
    }

    static constraints = {
        query  widget: 'textarea'
    }

    //static belongsTo = Database
    String name
    String description
    String query

    //Database db
}
