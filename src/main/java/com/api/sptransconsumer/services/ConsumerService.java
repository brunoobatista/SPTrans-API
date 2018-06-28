package com.api.sptransconsumer.services;

import com.api.sptransconsumer.dto.Links;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class ConsumerService {

    private HttpStatus status;

    @Autowired
    private AuthenticationService authenticationService;

    public ResponseEntity findLines(String uri, String token) {
        String apiCredentials = authenticationService.getApiCredentials(token);

        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json");
        headers.add("Accept", "*/*");
        headers.add("Cookie", apiCredentials);
        HttpEntity<String> requestEntity = new HttpEntity<String>("", headers);

        String link = Links.LINK.concat(Links.LINHA_BUSCAR).concat(uri);

        ResponseEntity<String> responseEntity = restTemplate.exchange(link, HttpMethod.GET, requestEntity, String.class);
        this.setStatus(responseEntity.getStatusCode());

        return responseEntity;
    }

    public ResponseEntity findLinhasSentido(String uri, String sentido, String token) {
        String apiCredentials = authenticationService.getApiCredentials(token);

        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json");
        headers.add("Accept", "*/*");
        headers.add("Cookie", apiCredentials);
        HttpEntity<String> requestEntity = new HttpEntity<String>("", headers);

        String link = (Links.LINK.concat(Links.LINHA_BUSCAR_LINHA_SENTIDO[0]).concat(uri))
                        .concat(Links.LINHA_BUSCAR_LINHA_SENTIDO[1].concat(sentido));

        ResponseEntity<String> responseEntity = restTemplate.exchange(link, HttpMethod.GET, requestEntity, String.class);
        this.setStatus(responseEntity.getStatusCode());

        return responseEntity;
    }

    public HttpStatus getStatus() {
        return status;
    }

    public void setStatus(HttpStatus status) {
        this.status = status;
    }

}
