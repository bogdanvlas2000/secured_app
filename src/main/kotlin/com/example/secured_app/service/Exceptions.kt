package com.example.secured_app.service

class UsernameAlreadyExists(msg: String) : RuntimeException(msg)

class EmailAlreadyExists(msg: String) : RuntimeException(msg)
