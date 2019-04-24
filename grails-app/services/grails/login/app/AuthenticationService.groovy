package grails.login.app

class AuthenticationService {

    private static final String AUTHORIZED = "AUTHORIZED"

    def setStudentAuthorization(Student student) {
        def authorization = [isLoggedIn: true, student: student]
        AppUtil.getAppSession()[AUTHORIZED] = authorization
    }

    def doLogin(String email, String password){
        password = password.encodeAsMD5()
        Student student = Student.findByEmailAndPassword(email, password)
        if (student){
            setStudentAuthorization(student)
            return true
        }
        return false
    }

    boolean isAuthenticated(){
        def authorization = AppUtil.getAppSession()[AUTHORIZED]
        if (authorization && authorization.isLoggedIn){
            return true
        }
        return false
    }


    def getStudent(){
        def authorization = AppUtil.getAppSession()[AUTHORIZED]
        return authorization?.student
    }


    def getStudentName(){
        def student = getStudent()
        return "${student.firstName} ${student.lastName}"
    }

    def isAdministratorStudent(){
        def student = getStudent()
        if (student && student.studentType == GlobalConfig.USER_TYPE.ADMINISTRATOR){
            return true
        }
        return false
    }
}
