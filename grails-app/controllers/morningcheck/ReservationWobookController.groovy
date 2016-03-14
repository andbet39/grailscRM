package morningcheck

import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

@Transactional(readOnly = true)
class ReservationWobookController {

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond ReservationWobook.list(params), model:[reservationWobookCount: ReservationWobook.count()]
    }

    def show(ReservationWobook reservationWobook) {
        respond reservationWobook
    }

    def create() {
        respond new ReservationWobook(params)
    }

    @Transactional
    def save(ReservationWobook reservationWobook) {
        if (reservationWobook == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (reservationWobook.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond reservationWobook.errors, view:'create'
            return
        }

        reservationWobook.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'reservationWobook.label', default: 'ReservationWobook'), reservationWobook.id])
                redirect reservationWobook
            }
            '*' { respond reservationWobook, [status: CREATED] }
        }
    }

    def edit(ReservationWobook reservationWobook) {
        respond reservationWobook
    }

    @Transactional
    def update(ReservationWobook reservationWobook) {
        if (reservationWobook == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (reservationWobook.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond reservationWobook.errors, view:'edit'
            return
        }

        reservationWobook.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'reservationWobook.label', default: 'ReservationWobook'), reservationWobook.id])
                redirect reservationWobook
            }
            '*'{ respond reservationWobook, [status: OK] }
        }
    }

    @Transactional
    def delete(ReservationWobook reservationWobook) {

        if (reservationWobook == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        reservationWobook.delete flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'reservationWobook.label', default: 'ReservationWobook'), reservationWobook.id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'reservationWobook.label', default: 'ReservationWobook'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
