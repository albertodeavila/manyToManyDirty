package manytomany

class Person {

    String name

    static belongsTo = [Position]
    static hasMany = [positions: Position]
    static constraints = {
    }
}
