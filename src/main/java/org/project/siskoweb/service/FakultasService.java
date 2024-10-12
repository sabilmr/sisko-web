package org.project.siskoweb.service;

import org.project.siskoweb.model.request.FakultasReq;
import org.project.siskoweb.model.response.FakultasRes;

import java.util.List;
import java.util.Optional;

public interface FakultasService {
    List<FakultasRes> get();
    Optional<FakultasRes> getById(String id);
    Optional<FakultasRes> save(FakultasReq request);
    Optional<FakultasRes> update(String id, FakultasReq request);
    Optional<FakultasRes> delete(String id);
}
