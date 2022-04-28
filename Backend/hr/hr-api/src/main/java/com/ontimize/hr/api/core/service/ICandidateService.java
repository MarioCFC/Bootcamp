package com.ontimize.hr.api.core.service;

import com.ontimize.jee.common.dto.EntityResult;
import com.ontimize.jee.common.exceptions.OntimizeJEERuntimeException;
import java.util.List;
import java.util.Map;

public interface ICandidateService {

    // CANDIDATE
    public EntityResult candidateQuery(Map<String, Object> keyMap, List<String> attrList) throws OntimizeJEERuntimeException;

    public EntityResult candidateInsert(Map<String, Object> attrMap) throws OntimizeJEERuntimeException;

    public EntityResult candidateUpdate(Map<String, Object> attrMap, Map<String, Object> keyMap) throws OntimizeJEERuntimeException;

    public EntityResult candidateDelete(Map<String, Object> keyMap) throws OntimizeJEERuntimeException;

}
