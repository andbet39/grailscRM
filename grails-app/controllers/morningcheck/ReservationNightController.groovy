package morningcheck

import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

@Transactional(readOnly = true)
class ReservationNightController {

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond ReservationNight.list(params), model:[reservationNightCount: ReservationNight.count()]
    }

    def show(ReservationNight reservationNight) {
        respond reservationNight
    }

    def create() {
        respond new ReservationNight(params)
    }

    @Transactional
    def save(ReservationNight reservationNight) {
        if (reservationNight == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (reservationNight.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond reservationNight.errors, view:'create'
            return
        }

        reservationNight.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'reservationNight.label', default: 'ReservationNight'), reservationNight.id])
                redirect reservationNight
            }
            '*' { respond reservationNight, [status: CREATED] }
        }
    }

    def edit(ReservationNight reservationNight) {
        respond reservationNight
    }

    @Transactional
    def update(ReservationNight reservationNight) {
        if (reservationNight == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (reservationNight.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond reservationNight.errors, view:'edit'
            return
        }

        reservationNight.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'reservationNight.label', default: 'ReservationNight'), reservationNight.id])
                redirect reservationNight
            }
            '*'{ respond reservationNight, [status: OK] }
        }
    }

    @Transactional
    def delete(ReservationNight reservationNight) {

        if (reservationNight == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        reservationNight.delete flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'reservationNight.label', default: 'ReservationNight'), reservationNight.id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'reservationNight.label', default: 'ReservationNight'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
