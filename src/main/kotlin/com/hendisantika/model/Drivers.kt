package com.hendisantika.model

import java.io.Serializable
import javax.persistence.*

/**
 * Created by hendisantika on 15/02/17.
 */
@Entity
@Table(name = "drivers")
data class Drivers(@Id @GeneratedValue(strategy = GenerationType.AUTO) var id: Long? = 0,
                   @Column(nullable = false) var latitude: Double? = 0.0,
                   @Column(nullable = false) var longitude: Double? = 0.0,
                   @Column(nullable = false) var distance: Int? = 0) : Serializable {

    protected constructor() : this(id = null, latitude = null, longitude = null, distance = null) {}
}

