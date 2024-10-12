package org.project.siskoweb.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.util.Strings;
import org.project.siskoweb.constant.BackEndUrl;
import org.project.siskoweb.model.request.FakultasReq;
import org.project.siskoweb.model.response.FakultasRes;
import org.project.siskoweb.model.response.Response;
import org.project.siskoweb.service.FakultasService;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class FakultasServiceImpl implements FakultasService {
    private final RestTemplate restTemplate;
    private final BackEndUrl backEndUrl;
    private final ObjectMapper objectMapper;

    @Override
    public List<FakultasRes> get() {
        try {
            var url = backEndUrl.fakultasUrl();
            ResponseEntity<Response> response = restTemplate.getForEntity(url, Response.class);
            if (response.getStatusCode() == HttpStatus.OK) {
                return (List<FakultasRes>)response.getBody().getData();
            }
        } catch (RestClientException e) {
            return Collections.emptyList();
        }
        return Collections.emptyList();
    }

    @Override
    public Optional<FakultasRes> getById(String id) {
        try {
            var url = Strings.concat(backEndUrl.fakultasUrl(), "/" + id);
            ResponseEntity<Response> response = restTemplate.getForEntity(url, Response.class);
            if (response.getStatusCode() == HttpStatus.OK) {
                byte[] json = objectMapper.writeValueAsBytes(Objects.requireNonNull(response).getBody().getData());
                FakultasRes result = objectMapper.readValue(json, FakultasRes.class);

                return Optional.of(result);
            }
        } catch (RestClientException e) {
            return Optional.empty();
        }catch (IOException e) {
            throw new RuntimeException(e);
        }
        return Optional.empty();
    }

    @Override
    public Optional<FakultasRes> save(FakultasReq request) {
        try {
            var url = backEndUrl.fakultasUrl();
            HttpEntity<FakultasReq> requestEntity = new HttpEntity<>(request);
            ResponseEntity<Response> response = restTemplate.postForEntity(url, requestEntity, Response.class);
            if (response.getStatusCode() == HttpStatus.OK) {
                byte[] json = objectMapper.writeValueAsBytes(Objects.requireNonNull(response).getBody().getData());
                FakultasRes result = objectMapper.readValue(json, FakultasRes.class);

                return Optional.of(result);
            }
        } catch (RestClientException e) {
            return Optional.empty();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return Optional.empty();
    }

    @Override
    public Optional<FakultasRes> update(String id, FakultasReq request) {
        try {
            var url = Strings.concat(backEndUrl.fakultasUrl(),"/" + id);
            HttpEntity<FakultasReq> requestEntity = new HttpEntity<>(request);
            ResponseEntity<Response> response = restTemplate.exchange(url, HttpMethod.PATCH, requestEntity, Response.class);
            if (response.getStatusCode() == HttpStatus.OK) {
                byte[] json = objectMapper.writeValueAsBytes(Objects.requireNonNull(response).getBody().getData());
                FakultasRes result = objectMapper.readValue(json, FakultasRes.class);

                return Optional.of(result);
            }
        } catch (RestClientException e) {
            return Optional.empty();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return Optional.empty();
    }

    @Override
    public Optional<FakultasRes> delete(String id) {
        try {
            var url = Strings.concat(backEndUrl.fakultasUrl(), "/" + id);
            ResponseEntity<Response> response = restTemplate.exchange(url, HttpMethod.DELETE, null, Response.class);
            if (response.getStatusCode() == HttpStatus.OK) {
                byte[] json = objectMapper.writeValueAsBytes(Objects.requireNonNull(response).getBody().getData());
                FakultasRes reslut = objectMapper.readValue(json, FakultasRes.class);

                return Optional.of(reslut);
            }
        } catch (RestClientException e) {
            return Optional.empty();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return Optional.empty();
    }
}
