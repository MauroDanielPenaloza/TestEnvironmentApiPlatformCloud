// ** Comienzo de bloque para test

import com.apc.groovy.ApiContextMockFactory
import oracle.apiplatform.policies.sdk.context.ApiRuntimeContext
ApiRuntimeContext context = ApiContextMockFactory.apiRuntimeContextE1()

// ** finaliza bloque para test

Runnable runneable =  new Runnable (){

    @com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement(localName = "dat:DataServiceRq")
    static class DataServiceRq {
        @com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty(localName = "dat:nombre")
        public String nombre
        @com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty(localName = "dat:valor")
        public String valor
        @com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty(localName = "dat:nroTransaccion")
        public String nroTransaccion
        @com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty(localName = "dat:nombreApp")
        public String nombreApp
        @com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty(localName = "dat:nombreService")
        public String nombreService
        @com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty(localName = "dat:nombreOperation")
        public String nombreOperation
        @com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty(localName = "dat:codEnt")
        public String codEnt

    }

    static class MsgResponseRest {
        @com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty(localName = "User")
        String user
        @com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty(localName = "Pass")
        String pass
        @com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty(localName = "Mensaje")
        String mensaje

    }

    String makeRequestBody(){
        com.fasterxml.jackson.dataformat.xml.XmlMapper map = new com.fasterxml.jackson.dataformat.xml.XmlMapper()
        MsgResponseRest aResponse = map.readValue(context.getApiResponse().getBody().asString(), MsgResponseRest.class)
        DataServiceRq payload = new DataServiceRq()
        payload.nombre = aResponse.user
        payload.valor=aResponse.pass
        payload.nroTransaccion= aResponse.mensaje
        payload.nombreApp = context.getApiRequest().getRequestURI()
        payload.nombreService = new com.fasterxml.jackson.databind.ObjectMapper().readValue( context.getApiRequest().getBody().asString(), LinkedHashMap.class).get("ID")
        payload.nombreOperation = null
        payload.codEnt = new Date().toString()//TODO Preguntar -> Que es dateTime IN???

        String stringPay = "<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:dat=\"http://farmalink.com.ar/applicationService/V1/DataServiceSvc\">    \n" +
                "\t<soapenv:Header/>\n" +
                "\t<soapenv:Body>"
        stringPay = stringPay + (map.writeValueAsString(payload))
        stringPay += "</soapenv:Body>\n" +
                "</soapenv:Envelope> "
        return stringPay;
    }
    void run() {
        try{
            String stringPay = makeRequestBody()
            String url = "http://192.168.100.134:8001/DataServiceSvc/DataServiceSvc?wsdl"
            javax.ws.rs.client.Client c = javax.ws.rs.client.ClientBuilder.newClient();
            c.property(org.glassfish.jersey.client.ClientProperties.CONNECT_TIMEOUT, 4000)
            javax.ws.rs.core.Response r = c.target(url)
                    .request(javax.ws.rs.core.MediaType.APPLICATION_XML)
                    .post(javax.ws.rs.client.Entity.entity(stringPay, javax.ws.rs.core.MediaType.APPLICATION_XML))
            println "Status en llamada callout: " + r.getStatus()
            println "Response: "+r.readEntity(String.class)
        } catch (Exception e) {
            println "Error al intentar invocar callout"
            e.printStackTrace()
        }

    }

}

runneable.run()