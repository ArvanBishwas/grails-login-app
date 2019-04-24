package grails.login.app

class GlobalConfig {

    public static final def USER_TYPE = [
            ADMINISTRATOR: "ADMINISTRATOR",
            REGULAR_STUDENT: "REGULAR_STUDENT",
    ]


    public static Integer itemsPerPage() {
        return 6
    }

}
