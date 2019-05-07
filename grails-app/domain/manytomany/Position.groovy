package manytomany

class Position {

    String name

    static hasMany = [persons: Person]

    static constraints = {
    }
}
