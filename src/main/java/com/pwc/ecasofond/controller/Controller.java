package com.pwc.ecasofond.controller;

import com.pwc.ecasofond.request.body.Body;
import com.pwc.ecasofond.request.response.ApiResponse;
import com.pwc.ecasofond.request.response.Response;
import org.springframework.http.ResponseEntity;

public interface Controller<T extends Response, U extends Body, V extends Body> {
    ResponseEntity<ApiResponse<Iterable<T>>> getAll();

    ResponseEntity<ApiResponse<T>> get(Long id);

    ResponseEntity<ApiResponse<T>> add(U requestBody);

    ResponseEntity<ApiResponse<T>> update(V requestBody);

    ResponseEntity<ApiResponse<Boolean>> delete(Long id);
}