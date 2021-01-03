package com.example.secured_app

import com.example.secured_app.config.MyServletContainer
import org.apache.catalina.connector.Connector
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory
import org.springframework.context.annotation.Bean

@SpringBootApplication
class SecuredAppApplication

fun main(args: Array<String>) {
    runApplication<SecuredAppApplication>(*args)
}

@Bean
fun servletContainer(): MyServletContainer {
    val tomcat = MyServletContainer()
    //add HTTP to HTTPS redirect
    tomcat.addAdditionalTomcatConnectors(httpToHttpsRedirectConnector())
    return tomcat
}

fun httpToHttpsRedirectConnector(): Connector {
    val connector = Connector(TomcatServletWebServerFactory.DEFAULT_PROTOCOL)
    connector.scheme = "http"
    connector.port = 8082
    connector.secure = false
    connector.redirectPort = 8443
    return connector
}