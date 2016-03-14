

class BootStrap {

    def socketService

    def init = { servletContext ->

        socketService.start()

    }
    def destroy = {
    }
}
