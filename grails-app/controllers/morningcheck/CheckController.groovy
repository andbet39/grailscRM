package morningcheck

import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

@Transactional(readOnly = true)
class CheckController {

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond Check.list(params), model:[checkCount: Check.count()]
    }

    def show(Check check) {
        respond check
    }

    def create() {
        respond new Check(params)
    }

    @Transactional
    def save(Check check) {
        if (check == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (check.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond check.errors, view:'create'
            return
        }

        check.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'check.label', default: 'Check'), check.id])
                redirect check
            }
            '*' { respond check, [status: CREATED] }
        }
    }

    def edit(Check check) {
        respond check
    }

    @Transactional
    def update(Check check) {
        if (check == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (check.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond check.errors, view:'edit'
            return
        }

        check.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'check.label', default: 'Check'), check.id])
                redirect check
            }
            '*'{ respond check, [status: OK] }
        }
    }

    @Transactional
    def delete(Check check) {

        if (check == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        check.delete flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'check.label', default: 'Check'), check.id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'check.label', default: 'Check'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
