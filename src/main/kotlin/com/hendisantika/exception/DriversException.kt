package com.hendisantika.exception

/**
 * Created by hendisantika on 15/02/17.
 */
class DriversException : Exception {
    val errorMessage: String = ""

    constructor(errorMessage: String) : super(errorMessage) { }

    constructor() : super() {}

    companion object {
        private val serialVersionUID = 1L
    }
}