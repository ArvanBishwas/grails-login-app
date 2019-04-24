package grails.login.app
import grails.web.servlet.mvc.GrailsParameterMap

import grails.gorm.transactions.Transactional

@Transactional
class StudentService {

    def save(GrailsParameterMap params) {
        Student student = new Student(params)
        def response = AppUtil.saveResponse(false, student)
        if (student.validate()) {
            student.save(flush: true)
            if (!student.hasErrors()){
                response.isSuccess = true
            }
        }
        return response
    }


    def update(Student student, GrailsParameterMap params) {
        student.properties = params
        def response = AppUtil.saveResponse(false, student)
        if (student.validate()) {
            student.save(flush: true)
            if (!student.hasErrors()){
                response.isSuccess = true
            }
        }
        return response
    }


    def getById(Serializable id) {
        return Student.get(id)
    }


    def list(GrailsParameterMap params) {
        params.max = params.max ?: GlobalConfig.itemsPerPage()
        List<Student> studentList = Student.createCriteria().list(params) {
            if (params?.colName && params?.colValue) {
                like(params.colName, "%" + params.colValue + "%")
            }
            if (!params.sort) {
                order("id", "desc")
            }
        }
        return [list: studentList, count: studentList.totalCount]
    }


    def delete(Student student) {
        try {
            student.delete(flush: true)
        } catch (Exception e) {
            println(e.getMessage())
            return false
        }
        return true
    }
}
