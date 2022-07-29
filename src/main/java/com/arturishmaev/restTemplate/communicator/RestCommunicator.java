package com.arturishmaev.restTemplate.communicator;

import com.arturishmaev.restTemplate.model.User;
import lombok.Getter;
import lombok.Setter;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Setter
@Getter
@Component
public class RestCommunicator {

    private final RestTemplate restTemplate;

    public RestCommunicator(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public HttpHeaders getHttpHeaders(String url) {
        return restTemplate.exchange(url, HttpMethod.GET, null, new ParameterizedTypeReference<List<User>>() {
        }).getHeaders();
    }

    public ResponseEntity<String> addUser(String url, User user, HttpHeaders headers) {
        return restTemplate.exchange(url, HttpMethod.POST, new HttpEntity<>(user, headers), String.class);
    }

    public ResponseEntity<String> updateUser(String url, User user, HttpHeaders headers) {
        return restTemplate.exchange(url, HttpMethod.PUT, new HttpEntity<>(user, headers), String.class);
    }

    public ResponseEntity<String> deleteUser(String url, HttpHeaders headers) {
        return restTemplate.exchange(url, HttpMethod.DELETE, new HttpEntity<>(headers), String.class);
    }
}
