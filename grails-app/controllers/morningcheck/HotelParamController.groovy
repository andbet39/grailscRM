package morningcheck

import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

@Transactional(readOnly = true)
class HotelParamController {

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond HotelParam.list(params), model:[hotelParamCount: HotelParam.count()]
    }

    def show(HotelParam hotelParam) {
        respond hotelParam
    }

    def create() {
        respond new HotelParam(params)
    }

    @Transactional
    def save(HotelParam hotelParam) {
        if (hotelParam == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (hotelParam.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond hotelParam.errors, view:'create'
            return
        }

        hotelParam.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'hotelParam.label', default: 'HotelParam'), hotelParam.id])
                redirect hotelParam
            }
            '*' { respond hotelParam, [status: CREATED] }
        }
    }

    def edit(HotelParam hotelParam) {
        respond hotelParam
    }

    @Transactional
    def update(HotelParam hotelParam) {
        if (hotelParam == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (hotelParam.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond hotelParam.errors, view:'edit'
            return
        }

        hotelParam.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'hotelParam.label', default: 'HotelParam'), hotelParam.id])
                redirect hotelParam
            }
            '*'{ respond hotelParam, [status: OK] }
        }
    }

    @Transactional
    def delete(HotelParam hotelParam) {

        if (hotelParam == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        hotelParam.delete flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'hotelParam.label', default: 'HotelParam'), hotelParam.id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'hotelParam.label', default: 'HotelParam'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
