package com.pwc.ecasofond.service;

import com.pwc.ecasofond.request.body.Body;
import com.pwc.ecasofond.request.response.Response;
import org.springframework.stereotype.Component;

@Component
public interface Service<T extends Response, U extends Body, V extends Body, W> {

    T convertToResponse(W w);

    Iterable<T> getAll();

    T get(Long id);

    T add(U u);

    T update(V u);

    Boolean delete(Long id);
}
