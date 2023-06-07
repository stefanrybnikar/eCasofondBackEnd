package com.pwc.ecasofond.service;

import com.pwc.ecasofond.request.body.Body;
import org.springframework.stereotype.Component;

@Component
public interface Service<T, U extends Body, V extends Body> {
    Iterable<T> getAll();

    T get(Long id);

    T add(U u);

    T update(V u);

    Boolean delete(Long id);
}
