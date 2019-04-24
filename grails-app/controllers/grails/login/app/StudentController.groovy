package grails.login.app

class StudentController {

    StudentService studentService

    def index() {
        def response = studentService.list(params)
        [studentList: response.list, total:response.count]
    }

    def details(Integer id) {
        def response = studentService.getById(id)
        if (!response){
            redirect(controller: "student", action: "index")
        }else{
            [student: response]
        }
    }

    def create() {
        [student: flash.redirectParams]
    }

    def save() {
        def response = studentService.save(params)
        if (!response.isSuccess) {
            flash.redirectParams = response.model
            flash.message = AppUtil.infoMessage(g.message(code: "unable.to.save"), false)
            redirect(controller: "student", action: "create")
        }else{
            flash.message = AppUtil.infoMessage(g.message(code: "saved"))
            redirect(controller: "student", action: "index")
        }
    }


    def edit(Integer id) {
        if (flash.redirectParams) {
            [student: flash.redirectParams]
        } else {
            def response = studentService.getById(id)
            if (!response) {
                flash.message = AppUtil.infoMessage(g.message(code: "invalid.entity"), false)
                redirect(controller: "student", action: "index")
            } else {
                [student: response]
            }
        }
    }


    def update() {
        def response = studentService.getById(params.id)
        if (!response){
            flash.message = AppUtil.infoMessage(g.message(code: "invalid.entity"), false)
            redirect(controller: "student", action: "index")
        }else{
            response = studentService.update(response, params)
            if (!response.isSuccess){
                flash.redirectParams = response.model
                flash.message = AppUtil.infoMessage(g.message(code: "unable.to.update"), false)
                redirect(controller: "student", action: "edit")
            }else{
                flash.message = AppUtil.infoMessage(g.message(code: "updated"))
                redirect(controller: "student", action: "index")
            }
        }
    }

    def delete(Integer id) {
        def response = studentService.getById(id)
        if (!response){
            flash.message = AppUtil.infoMessage(g.message(code: "invalid.entity"), false)
            redirect(controller: "student", action: "index")
        }else{
            response = studentService.delete(response)
            if (!response){
                flash.message = AppUtil.infoMessage(g.message(code: "unable.to.delete"), false)
            }else{
                flash.message = AppUtil.infoMessage(g.message(code: "deleted"))
            }
            redirect(controller: "student", action: "index")
        }
    }

}
