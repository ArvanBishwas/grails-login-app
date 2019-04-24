package grails.login.app

class Contact {


    Integer id
    String name
    String image
    Student student

    Date dateCreated
    Date lastUpdated

    Set<ContactDetails> contactDetails
    Set<ContactGroup> contactGroup


    static hasMany = [contactDetails: ContactDetails, contactGroup: ContactGroup]

    static constraints = {
        image(nullable: true, blank: true)
    }

    static mapping = {
        version(false)
        contactDetails(cascade: 'all-delete-orphan')
    }
}
