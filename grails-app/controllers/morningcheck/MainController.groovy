package morningcheck

import myutil.myCSVUtils

class MainController {

    def wubookService
    def socketService
    def reservationService

    def index() {


        respond "Hello"
    }


    def extractReservation(){

        reservationService.extractReservation();
        redirect(controller: "reservation", action: "index")

    }


    def loadcsv(){
        respond "";
    }

    def importcsv() {

        String csv = params.csv

        wubookService.parseCSV(csv);

        respond "";
    }

    def socket(){
        respond "socket"
    }

    def angular()
    {
        respond "angular"
    }

    def sendMessage(Message message){

        message.save()
        socketService.broadCast(message)

        respond message
    }

}
