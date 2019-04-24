package grails.login.app

class AppInitializationService {

    static initialize() {
        initStudent()
    }

    private static initStudent() {
        if (Student.count() == 0) {
            Student student = new Student()
            student.firstName = "System"
            student.lastName = "Administrator"
            student.email = "arvanbishwas@gmail.com"
            student.password = "12345"
            student.studentType = GlobalConfig.USER_TYPE.ADMINISTRATOR
            student.save(flash: true)
        }
    }
}
