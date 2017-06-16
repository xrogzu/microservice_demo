package me.zhongmingmao.fallback;

import org.springframework.cloud.netflix.zuul.filters.route.ZuulFallbackProvider;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.stereotype.Component;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

@Component
public class UserConsumerFallback implements ZuulFallbackProvider {
    
    private static final String SERVICE_ID = "user-consumer";
    
    @Override
    public String getRoute() {
        return SERVICE_ID; // 表明为微服务user-consumer提供fallback，即Eureka Server中的service-id
    }
    
    @Override
    public ClientHttpResponse fallbackResponse() {
        return new ClientHttpResponse() {
            @Override
            public HttpStatus getStatusCode() throws IOException {
                return HttpStatus.OK;
            }
            
            @Override
            public int getRawStatusCode() throws IOException {
                return getStatusCode().value(); // 200
            }
            
            @Override
            public String getStatusText() throws IOException {
                return getStatusCode().getReasonPhrase(); // OK
            }
            
            @Override
            public void close() {
            }
            
            @Override
            public InputStream getBody() throws IOException {
                return new ByteArrayInputStream(
                        String.format("Microservice[%s] is not available, Please try Again later.", SERVICE_ID)
                                .getBytes());
            }
            
            @Override
            public HttpHeaders getHeaders() {
                HttpHeaders headers = new HttpHeaders();
                headers.setContentType(MediaType.TEXT_HTML);
                return headers;
            }
        };
    }
}
