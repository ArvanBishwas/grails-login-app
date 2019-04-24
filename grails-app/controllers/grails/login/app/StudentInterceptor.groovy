package grails.login.app


class StudentInterceptor {

    AuthenticationService authenticationService

    boolean before() {
        if (authenticationService.isAdministratorStudent()){
            return true
        }
        flash.message = AppUtil.infoMessage("You are not Authorized for this Action.", false)
        redirect(controller: "dashboard", action: "index")
        return false
    }

}
