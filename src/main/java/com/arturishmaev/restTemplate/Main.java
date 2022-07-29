package com.arturishmaev.restTemplate;

import com.arturishmaev.restTemplate.communicator.RestCommunicator;
import com.arturishmaev.restTemplate.config.AppConfig;
import com.arturishmaev.restTemplate.model.User;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;

public class Main {

    private static final String URL = "http://94.198.50.185:7081/api/users";

    public static void main(String[] args) {

        String code = "";

        ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
        RestCommunicator communicator = context.getBean("restCommunicator", RestCommunicator.class);

        HttpHeaders headers = new HttpHeaders();
        headers.add("Cookie", getSessionIdFromHeaders(communicator.getHttpHeaders(URL)));

        User user = new User(3L, "James", "Brown", (byte) 25);

        ResponseEntity<String> responseSave = communicator.addUser(URL, user, headers);
        code = code.concat(responseSave.getBody());

        user.setName("Thomas");
        user.setLastName("Shelby");

        ResponseEntity<String> responseUpdate = communicator.updateUser(URL, user, headers);
        code = code.concat(responseUpdate.getBody());

        ResponseEntity<String> responseDelete = communicator.deleteUser(URL + "/" + user.getId(), headers);
        code = code.concat(responseDelete.getBody());

        System.out.println(code);

    }

    private static String getSessionIdFromHeaders(HttpHeaders httpHeaders) {
        String jsessionid = httpHeaders.get("Set-Cookie").get(0).substring(0, "jsessionid=".length() + 32);
        return jsessionid;
    }
}

