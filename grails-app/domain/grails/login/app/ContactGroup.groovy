package grails.login.app

class ContactGroup {

    Integer id
    String name
    Student student

    Date dateCreated
    Date lastUpdated

    static belongsTo = Contact
    static hasMany = [contact: Contact]

    static constraints = {
        name(blank: false, nullable: false)
        student(nullable: true)
    }

    static mapping = {
        version(false)
    }
}
