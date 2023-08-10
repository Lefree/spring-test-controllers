package ru.necroc.testapplication.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import ru.necroc.testapplication.service.TestDbService;

@RestController
public class TestController {

    private final TestDbService testDbService;

    public TestController(TestDbService testDbService) {
        this.testDbService = testDbService;
    }


    @GetMapping("/testDb/{id}")
    public void testJpaAndJdbc(@PathVariable long id) {
        testDbService.testDbMethods(id);
    }


    @GetMapping("/securityTest")
    @PreAuthorize("hasRole('ADMIN') and authentication.principal.username == 'admin'")
    public ResponseEntity<String> testSecurity() {
        return ResponseEntity.ok("Ok");
    }
}
