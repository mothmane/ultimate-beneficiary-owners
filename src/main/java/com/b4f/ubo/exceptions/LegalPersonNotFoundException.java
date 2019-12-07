package com.b4f.ubo.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.UUID;

@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "No Natural Person or Busines entity is avalaible for the id that you provided")
public class LegalPersonNotFoundException extends RuntimeException {

    private UUID id;

    public LegalPersonNotFoundException(UUID id) {
        super("No Natural Person or Busines entity  having id : " + id+" found");
        this.id = id;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }
}
