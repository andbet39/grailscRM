package morningcheck

class WubookController {

    def wubookService

    def index() {}


    def wubooktoken(){
        wubookService.aquireToken()
        redirect(controller: "wubook", action: "index")

    }
    def wubookroom(){
        wubookService.aquirerooms()
        redirect(controller: "wubook", action: "index")

    }
    def wubookreseervation(){
        wubookService.aquirereservation()
        redirect(controller: "wubook", action: "index")

    }
}
