package com.example.secured_app.config

import org.apache.catalina.Context
import org.apache.tomcat.util.descriptor.web.SecurityCollection
import org.apache.tomcat.util.descriptor.web.SecurityConstraint
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory

class MyServletContainer : TomcatServletWebServerFactory() {
    //enable SSL traffic
    override fun postProcessContext(context: Context?) {
        val securityConstraint = SecurityConstraint()
        securityConstraint.userConstraint = "CONFIDENTIAL"
        val collection = SecurityCollection()
        collection.addPattern("/*")
        securityConstraint.addCollection(collection)
        if (context != null) {
            context.addConstraint(securityConstraint)
        }
    }
}