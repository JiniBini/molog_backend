package com.jblog.molog.entity

import javax.persistence.*

@Entity
class User(name: String, email: String, password: String) {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null

    @Column(nullable = false)
    var name: String = name

    @Column(nullable = false, unique = true)
    var email: String = email

    @Column(nullable = false)
    var password: String = password
}