package com.lingxiao.thefirst.kotlin

fun main(args: Array<String>) {

}

open class Person(name: String) {
    constructor(name: String, age: Int) : this(name) {

    }
}

class Student(name: String, age: Int) : Person(name, age) {

    lateinit var school:String

//    init {
//        this.school = ""
//    }

    constructor(name: String, age: Int, school: String) : this(name, age) {
        this.school = school
    }

}
