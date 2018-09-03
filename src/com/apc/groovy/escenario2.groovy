// ** Comienzo de bloque para test

import com.apc.groovy.ApiContextMockFactory
import oracle.apiplatform.policies.sdk.context.ApiRuntimeContext
ApiRuntimeContext context = ApiContextMockFactory.apiRuntimeContextE1()

// ** finaliza bloque para test

Runnable runneable =  new Runnable (){

    static class DataServiceRq {
        @com.fasterxml.jackson.annotation.JsonProperty("nombre")
        public String nombre
        @com.fasterxml.jackson.annotation.JsonProperty("valor")
        public String valor
        @com.fasterxml.jackson.annotation.JsonProperty("nroTransaccion")
        public String nroTransaccion
        @com.fasterxml.jackson.annotation.JsonProperty("nombreApp")
        public String nombreApp
        @com.fasterxml.jackson.annotation.JsonProperty("nombreService")
        public String nombreService
        @com.fasterxml.jackson.annotation.JsonProperty("nombreOperation")
        public String nombreOperation
        @com.fasterxml.jackson.annotation.JsonProperty("codEnt")
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
        com.fasterxml.jackson.databind.ObjectMapper objectMapper = new com.fasterxml.jackson.databind.ObjectMapper()
        return objectMapper.writeValueAsString(payload)
    }
    void run() {
        try{
            String stringPay = makeRequestBody()

            println "Request Callout Body: "
            println(stringPay)
            String url = "http://192.168.100.134:8001/DataServiceSvc/RestDataServiceSvc"
            println "Request URL: " + url


            javax.ws.rs.client.Client c = javax.ws.rs.client.ClientBuilder.newClient();
            c.property(org.glassfish.jersey.client.ClientProperties.CONNECT_TIMEOUT, 4000)
            javax.ws.rs.core.Response r = c.target(url)
                    .request(javax.ws.rs.core.MediaType.APPLICATION_XML)
                    .post(javax.ws.rs.client.Entity.entity(stringPay, javax.ws.rs.core.MediaType.APPLICATION_JSON))
            println "Status en llamada callout: " + r.getStatus()
            println "Response: "+r.readEntity(String.class)
        } catch (Exception e) {
            println "Error al intentar invocar callout"
            e.printStackTrace()
        }

    }

}

runneable.run()