package com.pwc.ecasofond.service;

import com.pwc.ecasofond.request.body.Body;
import com.pwc.ecasofond.request.response.ApiResponse;
import com.pwc.ecasofond.request.response.Response;
import org.springframework.stereotype.Component;

@Component
public interface Service<T extends Response, U extends Body, V extends Body, W> {

    T convertToResponse(W w);

    ApiResponse<Iterable<T>> getAll();

    ApiResponse<T> get(Long id);

    ApiResponse<T> add(U u);

    ApiResponse<T> update(V u);

    ApiResponse<Boolean> delete(Long id);
}
