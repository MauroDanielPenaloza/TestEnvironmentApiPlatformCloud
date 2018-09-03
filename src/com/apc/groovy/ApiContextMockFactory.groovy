package com.apc.groovy

import oracle.apiplatform.policies.sdk.context.ApiRuntimeContext
import oracle.apiplatform.policies.sdk.context.Body
import org.json.JSONArray
import org.json.JSONObject
import org.w3c.dom.Node

import javax.mail.internet.ContentType

class ApiContextMockFactory {
    static ApiRuntimeContext apiRuntimeContextE1(){
        ApiRuntimeContext context = new MockApiRuntimeContext();

        String responseTest = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" +
                "<ns1:Response xmlns:ns1=\"http://ejemplo.com.ar/applicationService/V1/GetSecure\" xmlns:soap-env=\"http://schemas.xmlsoap.org/soap/envelope/\">\n" +
                "    <User>UsuarioPrueba</User>\n" +
                "    <Pass>Welcome1</Pass>\n" +
                "    <Mensaje>Respuesta Servicio RestGetSecure</Mensaje>\n" +
                "</ns1:Response>"
        String requestbody = "{\n" +
                " \"ID\" : \"ID11\"\n" +
                "} "
        context.requestURI = "/Escenario1/Mock"
        context.requestBody = new Body() {
            InputStream asStream() throws IOException {
                return null
            }

            String asString() throws IOException {
                return requestbody
            }

            @Override
            JSONObject asJSONObject() throws IOException {
                return new JSONObject(requestbody)
            }

            JSONArray asJSONArray() throws IOException {
                return null
            }

            Node asNode() throws IOException {
                return null
            }

            javax.mail.internet.ContentType getContentType() {
                return null
            }
        }
        context.apiResponseBody = new Body(){

            InputStream asStream() throws IOException {
                return null
            }

            String asString() throws IOException {
                return responseTest
            }

            JSONObject asJSONObject() throws IOException {
                return null
            }

            JSONArray asJSONArray() throws IOException {
                return null
            }

            Node asNode() throws IOException {
                return null
            }

            ContentType getContentType() {
                return null
            }
        }
        return context;
    }

    static ApiRuntimeContext apiRuntimeContextE3() {
        ApiRuntimeContext context = new MockApiRuntimeContext();

        String responseTest = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" +
                "<ns1:Response xmlns:ns1=\"http://ejemplo.com.ar/applicationService/V1/GetSecure\" xmlns:soap-env=\"http://schemas.xmlsoap.org/soap/envelope/\">\n" +
                "    <User>UsuarioPrueba</User>\n" +
                "    <Pass>Welcome1</Pass>\n" +
                "    <Mensaje>Respuesta Servicio RestGetSecure</Mensaje>\n" +
                "</ns1:Response>"
        String requestbody = "<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:get=\"http://ejemplo.com.ar/applicationService/V1/GetSecure\"> " +
                "<soapenv:Header/>\n" +
                "\t<soapenv:Body>\n" +
                "\t\t<get:Request>\n" +
                "\t\t\t<ID>ID11</ID>       \n" +
                "\t\t</get:Request>\n" +
                "\t</soapenv:Body>\n" +
                "</soapenv:Envelope>"
        context.requestURI = "/Escenario1/Mock"
        context.requestBody = new Body() {
            InputStream asStream() throws IOException {
                return null
            }

            String asString() throws IOException {
                return requestbody
            }

            @Override
            JSONObject asJSONObject() throws IOException {
                return null
            }

            JSONArray asJSONArray() throws IOException {
                return null
            }

            Node asNode() throws IOException {
                return null
            }

            javax.mail.internet.ContentType getContentType() {
                return null
            }
        }
        context.apiResponseBody = new Body(){

            InputStream asStream() throws IOException {
                return null
            }

            String asString() throws IOException {
                return responseTest
            }

            JSONObject asJSONObject() throws IOException {
                return null
            }

            JSONArray asJSONArray() throws IOException {
                return null
            }

            Node asNode() throws IOException {
                return null
            }

            ContentType getContentType() {
                return null
            }
        }

        return context
    }
}
