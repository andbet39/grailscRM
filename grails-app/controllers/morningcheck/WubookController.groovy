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
    def wubookreservation(){
        log.info(params.date_from.toString())
        log.info(params.date_to.toString())


        wubookService.aquirereservation(params.date_from,params.date_to)
        redirect(controller: "wubook", action: "index")

    }

    def wubooknewreservation(){


        wubookService.aquirenewreservation()
        redirect(controller: "wubook", action: "index")

    }




}
