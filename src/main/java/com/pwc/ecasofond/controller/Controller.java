package com.pwc.ecasofond.controller;

import com.pwc.ecasofond.request.body.Body;
import org.springframework.http.ResponseEntity;

public interface Controller<T, U extends Body, V extends Body> {
    ResponseEntity<Iterable<T>> getAll();

    ResponseEntity<T> get(Long id);

    ResponseEntity<T> add(U requestBody);

    ResponseEntity<T> update(V requestBody);

    ResponseEntity<Boolean> delete(Long id);
}