package com.api.sptransconsumer.services;

import com.api.sptransconsumer.dto.Links;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.ResponseEntity;

@Service
public class AuthenticationService {

    private HttpStatus status;
    private String apiCredentials;

    public ResponseEntity authenticateSPTransApi(String token) {
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json");
        headers.add("Accept", "*/*");

        String link = Links.LINK.concat(Links.URI_AUTENTICATE).concat(token);

        HttpEntity<String> requestEntity = new HttpEntity<String>("", headers);
        ResponseEntity<String> responseEntity = restTemplate.exchange(link, HttpMethod.POST, requestEntity, String.class);
        this.setStatus(responseEntity.getStatusCode());


        return responseEntity;
    }

    public String getApiCredentials(String token) {
        String apiCredentials = this.authenticateSPTransApi(token).getHeaders().getFirst("Set-Cookie");

        return apiCredentials;
    }

    private void setApiCredentials(String apiCredentials) {
        this.apiCredentials = apiCredentials;
    }

    public HttpStatus getStatus() {
        return status;
    }

    public void setStatus(HttpStatus status) {
        this.status = status;
    }

}
