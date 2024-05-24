package taewookim.demo.controller;

import org.apache.coyote.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import taewookim.demo.service.Service;

@RestController
public class JsonController {

    private final Service service;

    public JsonController() {
        service = new Service();
    }

    @GetMapping("/users")
    public ResponseEntity<?> getUsers() {
        return ResponseEntity.ok(service.getUsers());
    }

    @GetMapping

}
