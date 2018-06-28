package com.api.sptransconsumer.resource;

import com.api.sptransconsumer.services.AuthenticationService;
import com.api.sptransconsumer.services.ConsumerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/consume")
public class SPTransResource {

    @Autowired
    private AuthenticationService authenticationService;

    @Autowired
    private ConsumerService consumerService;

    @GetMapping
    public String index() { return "Testando"; }

    @PostMapping("/linha")
    public ResponseEntity getLinha(@RequestBody Body body) {

        ResponseEntity<String> response = consumerService.findLines(body.getTermoBusca(), body.getToken());

        return (response != null) ? response : null;
    }

    @PostMapping("linhaSentido")
    public ResponseEntity getLinhaSentido(@RequestBody Body body) {
        ResponseEntity<String> response = consumerService.findLinhasSentido(body.getTermoBusca(), body.getSentido(), body.getToken());

        return (response != null) ? response : null;
    }

}

class Body {
    private String token;
    private String termoBusca;
    private String sentido;

    public String getSentido() {
        return sentido;
    }

    public void setSentido(String sentido) {
        this.sentido = sentido;
    }

    public String getTermoBusca() {
        return termoBusca;
    }

    public void setTermoBusca(String uri) {
        this.termoBusca = uri;
    }

    public String getToken() {

        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
