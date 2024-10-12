package org.project.siskoweb.service;

import lombok.RequiredArgsConstructor;
import org.springframework.web.client.RestTemplate;

@RequiredArgsConstructor
public class BaseService<T> {
    private final RestTemplate restTemplate;
    private final Class<T> typeParameterClass;
}
