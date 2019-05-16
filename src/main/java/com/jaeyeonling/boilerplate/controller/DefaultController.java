package com.jaeyeonling.boilerplate.controller;

import com.jaeyeonling.boilerplate.exception.PlatformStatus;
import com.jaeyeonling.boilerplate.model.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
class DefaultController {
    @RequestMapping(path = "/health")
    private ResponseEntity healthCheck() {
        return Response.ok().build();
    }

    @RequestMapping
    private ResponseEntity notFound() {
        return PlatformStatus.NOT_FOUND.toResponse();
    }
}
