package com.ontimize.hr.model.core.service;

import com.ontimize.hr.api.core.service.IOfferService;
import com.ontimize.hr.model.core.dao.OfferCandidateStatusDao;
import com.ontimize.hr.model.core.dao.OfferCandidatesDao;
import com.ontimize.hr.model.core.dao.OfferDao;
import com.ontimize.hr.model.core.dao.OfferStatusDao;
import com.ontimize.jee.common.dto.EntityResult;
import com.ontimize.jee.common.exceptions.OntimizeJEERuntimeException;
import com.ontimize.jee.common.security.PermissionsProviderSecured;
import com.ontimize.jee.server.dao.DefaultOntimizeDaoHelper;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Service;

@Service("OfferService")
@Lazy
public class OfferService implements IOfferService {

    @Autowired
    private OfferDao offerDao;
    @Autowired
    private OfferStatusDao offerStatusDao;
    @Autowired
    private OfferCandidatesDao offerCandidatesDao;
    @Autowired
    private OfferCandidateStatusDao offerCandidateStatusDao;
    @Autowired
    private DefaultOntimizeDaoHelper daoHelper;

    @Override
    @Secured({PermissionsProviderSecured.SECURED})
    public EntityResult offerQuery(Map<String, Object> keyMap, List<String> attrList)
            throws OntimizeJEERuntimeException {
        return this.daoHelper.query(this.offerDao, keyMap, attrList);
    }

    @Override
    @Secured({PermissionsProviderSecured.SECURED})
    public EntityResult offerInsert(Map<String, Object> attrMap) throws OntimizeJEERuntimeException {
        return this.daoHelper.insert(this.offerDao, attrMap);
    }

    @Override
    @Secured({PermissionsProviderSecured.SECURED})
    public EntityResult offerUpdate(Map<String, Object> attrMap, Map<String, Object> keyMap)
            throws OntimizeJEERuntimeException {
        return this.daoHelper.update(this.offerDao, attrMap, keyMap);
    }

    @Override
    @Secured({PermissionsProviderSecured.SECURED})
    public EntityResult offerDelete(Map<String, Object> keyMap) throws OntimizeJEERuntimeException {
        return this.daoHelper.delete(this.offerDao, keyMap);
    }

    @Override
    @Secured({PermissionsProviderSecured.SECURED})
    public EntityResult offerStatusQuery(Map<String, Object> keyMap, List<String> attrList)
            throws OntimizeJEERuntimeException {
        return this.daoHelper.query(this.offerStatusDao, keyMap, attrList);
    }

    @Override
    @Secured({PermissionsProviderSecured.SECURED})
    public EntityResult offerStatusInsert(Map<String, Object> attrMap) throws OntimizeJEERuntimeException {
        return this.daoHelper.insert(this.offerStatusDao, attrMap);
    }

    @Override
    @Secured({PermissionsProviderSecured.SECURED})
    public EntityResult offerStatusUpdate(Map<String, Object> attrMap, Map<String, Object> keyMap)
            throws OntimizeJEERuntimeException {
        return this.daoHelper.update(this.offerStatusDao, attrMap, keyMap);
    }

    @Override
    @Secured({PermissionsProviderSecured.SECURED})
    public EntityResult offerStatusDelete(Map<String, Object> keyMap) throws OntimizeJEERuntimeException {
        return this.daoHelper.delete(this.offerStatusDao, keyMap);
    }

    @Override
    @Secured({PermissionsProviderSecured.SECURED})
    public EntityResult offerCandidateQuery(Map<String, Object> keyMap, List<String> attrList)
            throws OntimizeJEERuntimeException {
        return this.daoHelper.query(this.offerCandidatesDao, keyMap, attrList);
    }

    @Override
    @Secured({PermissionsProviderSecured.SECURED})
    public EntityResult offerCandidateDetailsQuery(Map<String, Object> keyMap, List<String> attrList)
            throws OntimizeJEERuntimeException {
        return this.daoHelper.query(this.offerCandidatesDao, keyMap, attrList, OfferCandidatesDao.QUERY_OFFER_DETAILS);
    }

    @Override
    @Secured({PermissionsProviderSecured.SECURED})
    public EntityResult offerCandidateInsert(Map<String, Object> attrMap) throws OntimizeJEERuntimeException {
        return this.daoHelper.insert(this.offerCandidatesDao, attrMap);
    }

    @Override
    @Secured({PermissionsProviderSecured.SECURED})
    public EntityResult offerCandidateUpdate(Map<String, Object> attrMap, Map<String, Object> keyMap)
            throws OntimizeJEERuntimeException {
        return this.daoHelper.update(this.offerCandidatesDao, attrMap, keyMap);
    }

    @Override
    @Secured({PermissionsProviderSecured.SECURED})
    public EntityResult offerCandidateDelete(Map<String, Object> keyMap) throws OntimizeJEERuntimeException {
        return this.daoHelper.delete(this.offerCandidatesDao, keyMap);
    }

    @Override
    @Secured({PermissionsProviderSecured.SECURED})
    public EntityResult offerCandidateStatusQuery(Map<String, Object> keyMap, List<String> attrList)
            throws OntimizeJEERuntimeException {
        return this.daoHelper.query(this.offerCandidateStatusDao, keyMap, attrList);
    }

    @Override
    @Secured({PermissionsProviderSecured.SECURED})
    public EntityResult offerCandidateStatusInsert(Map<String, Object> attrMap) throws OntimizeJEERuntimeException {
        return this.daoHelper.insert(this.offerCandidateStatusDao, attrMap);
    }

    @Override
    @Secured({PermissionsProviderSecured.SECURED})
    public EntityResult offerCandidateStatusUpdate(Map<String, Object> attrMap, Map<String, Object> keyMap)
            throws OntimizeJEERuntimeException {
        return this.daoHelper.update(this.offerCandidateStatusDao, attrMap, keyMap);
    }

    @Override
    @Secured({PermissionsProviderSecured.SECURED})
    public EntityResult offerCandidateStatusDelete(Map<String, Object> keyMap) throws OntimizeJEERuntimeException {
        return this.daoHelper.delete(this.offerCandidateStatusDao, keyMap);
    }
}
