package com.example.secured_app.model

import javax.persistence.*

@Entity
@Table(name = "users")
class User(username: String, password: String, email: String, roles: MutableList<Role>, active: Int = 0) {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long = 0

    var username: String = username

    var password: String = password

    var email: String = email

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
        name = "users_roles",
        joinColumns = arrayOf(JoinColumn(name = "user_id")),
        inverseJoinColumns = arrayOf(JoinColumn(name = "role_id"))
    )
    var roles: MutableList<Role> = roles

    var active: Int = active

    override fun toString(): String {
        return "User(id=$id, username='$username', password='$password', email='$email', roles=$roles, active=$active)"
    }


}