package morningcheck


import grails.rest.*

@Resource(uri='/messages',formats=['json'])
class Message {

    def socketService
    static constraints = {
    }

    String userName
    String message

    def afterInsert = {
        socketService.broadCast(this);
        log.info( "data inserted" )
    }

}
