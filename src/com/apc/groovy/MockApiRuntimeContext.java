package com.apc.groovy;

import oracle.apiplatform.policies.sdk.context.*;

import java.io.IOException;
import java.util.List;
import java.util.Map;

public class MockApiRuntimeContext implements ApiRuntimeContext {

    public Body requestBody;
    public String requestURI;
    public Body apiResponseBody;

    public String getApiId() {
        return "mock_api";
    }

    public boolean isRequest() {
        return false;
    }

    public ApiRequest getApiRequest() {
        return new ApiRequest() {
            public Map<String, List<String>> getQueryParams() {
                return null;
            }

            public String getQueryString() {
                return null;
            }

            public String getMethod() {
                return "GET";
            }

            public String getPathInfo() {
                return null;
            }

            public String getRequestURI() {
                return requestURI;
            }

            public String getRemoteHost() {
                return null;
            }

            public Body getBody() throws IOException {
                return requestBody;
            }

            public String getHeader(String s) throws IOException {
                return null;
            }

            public Map<String, List<String>> getHeaders() throws IOException {
                return null;
            }
        };
    }

    public ApiResponse getApiResponse() {
        return new ApiResponse() {
            public ApiResponse setStatusCode(int i) {
                return null;
            }

            public int getStatusCode() {
                return 0;
            }

            public String getStatusText() {
                return null;
            }

            public ApiResponse setStatusText(String s) {
                return null;
            }

            public OutgoingMessage setBody(Body body) throws IOException {
                return null;
            }

            public Body getOrDefaultBody() throws IOException {
                return null;
            }

            public OutgoingMessage setHeader(String s, String s1) {
                return null;
            }

            public OutgoingMessage setHeaders(Map<String, List<String>> map) {
                return null;
            }

            public Map<String, List<String>> getOrDefaultHeaders() {
                return null;
            }

            public OutgoingMessage withoutHeader(String s) {
                return null;
            }

            public Body getBody() throws IOException {
                return apiResponseBody;
            }

            public String getHeader(String s) throws IOException {
                return null;
            }

            public Map<String, List<String>> getHeaders() throws IOException {
                return null;
            }
        };
    }

    public ServiceRequest getServiceRequest() {
        return new ServiceRequest() {
            public String getMethod() {
                return null;
            }

            public ServiceRequest setMethod(String s) {
                return null;
            }

            public String getOrDefaultMethod() {
                return null;
            }

            public Map<String, List<String>> getQueryParams() {
                return null;
            }

            public ServiceRequest setQueryParams(Map<String, List<String>> map) {
                return null;
            }

            public ServiceRequest setQueryParam(String s, String s1) {
                return null;
            }

            public String getPathInfo() {
                return null;
            }

            public ServiceRequest setPathInfo(String s) throws IOException {
                return null;
            }

            public ServiceRequest setRequestURL(String s) throws IOException {
                return null;
            }

            public String getRequestURL() {
                return null;
            }

            public Map<String, List<String>> getOrDefaultQueryParams() {
                return null;
            }

            public ServiceRequest withoutQueryParam(String s) {
                return null;
            }

            public String getServiceAccount() {
                return null;
            }

            public ServiceRequest setServiceAccount(String s) {
                return null;
            }

            public String getService() {
                return null;
            }

            public ServiceRequest setService(String s) {
                return null;
            }

            public boolean isUseProxy() {
                return false;
            }

            public ServiceRequest useProxy(boolean b) {
                return null;
            }

            public OutgoingMessage setBody(Body body) throws IOException {
                return null;
            }

            public Body getOrDefaultBody() throws IOException {
                return null;
            }

            public OutgoingMessage setHeader(String s, String s1) {
                return null;
            }

            public OutgoingMessage setHeaders(Map<String, List<String>> map) {
                return null;
            }

            public Map<String, List<String>> getOrDefaultHeaders() {
                return null;
            }

            public OutgoingMessage withoutHeader(String s) {
                return null;
            }

            public Body getBody() throws IOException {
                return null;
            }

            public String getHeader(String s) throws IOException {
                return null;
            }

            public Map<String, List<String>> getHeaders() throws IOException {
                return null;
            }
        };
    }

    public ServiceResponse getServiceResponse() {
        return null;
    }

    public ApplicationContext getApplicationContext() {
        return null;
    }

    public Object getAttribute(String s) {
        return null;
    }

    public void setAttribute(String s, Object o) {

    }

    public Object getEdr(String s) {
        return null;
    }

    public void setEdr(String s, Object o) {

    }

    public ExternalServiceCallout newCallout() {
        return null;
    }

    public void close() {

    }
}
