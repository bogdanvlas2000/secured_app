package com.example.secured_app.model

import javax.persistence.*

@Entity
@Table(name = "roles")
class Role(name: String) {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    val id: Long = 0

    val name: String = name
    override fun toString(): String {
        return name
    }


}