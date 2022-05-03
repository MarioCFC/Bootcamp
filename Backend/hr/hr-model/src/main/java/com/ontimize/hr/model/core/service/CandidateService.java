package com.ontimize.hr.model.core.service;

import com.ontimize.hr.api.core.service.ICandidateService;
import com.ontimize.hr.model.core.dao.CandidateDao;
import com.ontimize.hr.model.core.dao.EducationDao;
import com.ontimize.hr.model.core.dao.ExperienceLevelDao;
import com.ontimize.hr.model.core.dao.OriginDao;
import com.ontimize.hr.model.core.dao.ProfileDao;
import com.ontimize.hr.model.core.dao.StatusDao;
import com.ontimize.jee.common.dto.EntityResult;
import com.ontimize.jee.common.exceptions.OntimizeJEERuntimeException;
import com.ontimize.jee.server.dao.DefaultOntimizeDaoHelper;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("CandidateService")
@Lazy
public class CandidateService implements ICandidateService {

    /**
     * It stores the master service. When you mark it with the @Autowired
     * notation, it links automatically without using any setter
     */
    @Autowired
    private MasterService masterService;

    @Autowired
    private CandidateDao candidateDao;
    @Autowired
    private DefaultOntimizeDaoHelper daoHelper;
    /**
     * This method adds a new candidate. It removes from the parameters those
     * items not directly belonging to the table of candidates in case of that
     * are of the {@link String} type. Look for the identifier of this data in
     * case that they exist or adds them to the corresponding tables, and
     * associates them with the correct identifier. Then add these new elements
     * to the list of applicant's items and performs the insert of a new
     * candidate
     *
     *
     * @param attrMap A {@link Map} containing the data of the new candidate to
     * be inserted.
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public EntityResult candidateInsert(Map<String, Object> attrMap) throws OntimizeJEERuntimeException {
        Map<String, Object> nonCandidateData = removeNonRelatedData(attrMap, CandidateDao.ATTR_EDUCATION,
                CandidateDao.ATTR_EXPERIENCE_LEVEL, CandidateDao.ATTR_ORIGIN, CandidateDao.ATTR_PROFILE,
                CandidateDao.ATTR_STATUS);
        this.insertNonRelatedData(nonCandidateData);
        attrMap.putAll(nonCandidateData);
        return this.daoHelper.insert(this.candidateDao, attrMap);
    }

    /**
     * This method updates a candidate. It removes from the parameters those
     * items not directly belonging to the table of candidates in case of that
     * are of the {@link String} type. Look for the identifier of this data in
     * case that they exist or adds them to the corresponding tables, and
     * associates them with the correct identifier. Then add these new elements
     * to the list of applicant's items and performs the update of a candidate
     *
     *
     * @param attrMap A {@link Map} containing the data of the candidate to be
     * updated.
     * @param keyMap A {@link Map} containing the identification of the
     * candidate to be updated.
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public EntityResult candidateUpdate(Map<String, Object> attrMap, Map<String, Object> keyMap)
            throws OntimizeJEERuntimeException {
        Map<String, Object> nonCandidateData = removeNonRelatedData(attrMap, CandidateDao.ATTR_EDUCATION,
                CandidateDao.ATTR_EXPERIENCE_LEVEL, CandidateDao.ATTR_ORIGIN, CandidateDao.ATTR_PROFILE,
                CandidateDao.ATTR_STATUS);
        this.insertNonRelatedData(nonCandidateData);
        attrMap.putAll(nonCandidateData);
        return this.daoHelper.update(this.candidateDao, attrMap, keyMap);
    }

    /**
     * It removes from a map all the keys and values of the map that match those
     * keys in their following parameters and returns a map with the extracted
     * keys and values
     *
     * @param attrMap A {@link Map} with all keys
     * @param attrToExclude Multiple {@link String} that can be found as key of
     * {@code attrMap}
     * @return A {@link Map} with all of the keys and values removed from
     * {@code attrMap}
     */
    private Map<String, Object> removeNonRelatedData(Map<String, Object> attrMap, String... attrToExclude) {
        HashMap<String, Object> data = new HashMap<String, Object>();
        for (String attr : attrToExclude) {
            if (attrMap.containsKey(attr) && attrMap.get(attr) instanceof String) {
                data.put(attr, attrMap.remove(attr));
            }
        }
        return data;
    }

    /**
     * Checks if the data stored in the map passed by parameter exists in the
     * database table corresponding to the {@link MasterService} service. If so,
     * it replaces the value with the corresponding identifier. In case it does
     * not exist, it stores that value and substitutes the element's value in
     * the map with its identifier
     *
     * @param nonCandidateData Values to be inserted in the tables managed by
     * the {@link MasterService}
     */
    private void insertNonRelatedData(Map<String, Object> nonCandidateData) {
        for (Entry<String, Object> entry : nonCandidateData.entrySet()) {
            Map<String, Object> data = new HashMap<String, Object>();

            List<String> attr = new ArrayList<String>();
            EntityResult toret, query;
            switch (entry.getKey()) {
                case CandidateDao.ATTR_EDUCATION:
                    data.put(EducationDao.ATTR_DESCRIPTION, entry.getValue());
                    attr.add(EducationDao.ATTR_ID);
                    query = this.masterService.educationQuery(data, attr);
                    if (query.calculateRecordNumber() > 0) {
                        entry.setValue(query.getRecordValues(0).get(EducationDao.ATTR_ID));
                    } else {
                        toret = this.masterService.educationInsert(data);
                        entry.setValue(toret.get(EducationDao.ATTR_ID));
                    }
                    break;
                case CandidateDao.ATTR_EXPERIENCE_LEVEL:
                    data.put(ExperienceLevelDao.ATTR_DESCRIPTION, entry.getValue());
                    attr.add(ExperienceLevelDao.ATTR_ID);
                    query = this.masterService.experienceLevelQuery(data, attr);
                    if (query.calculateRecordNumber() > 0) {
                        entry.setValue(query.getRecordValues(0).get(ExperienceLevelDao.ATTR_ID));
                    } else {
                        toret = this.masterService.experienceLevelInsert(data);
                        entry.setValue(toret.get(ExperienceLevelDao.ATTR_ID));
                    }
                    break;
                case CandidateDao.ATTR_ORIGIN:
                    data.put(OriginDao.ATTR_DESCRIPTION, entry.getValue());
                    attr.add(OriginDao.ATTR_ID);
                    query = this.masterService.originQuery(data, attr);
                    if (query.calculateRecordNumber() > 0) {
                        entry.setValue(query.getRecordValues(0).get(OriginDao.ATTR_ID));
                    } else {
                        toret = this.masterService.originInsert(data);
                        entry.setValue(toret.get(OriginDao.ATTR_ID));
                    }
                    break;
                case CandidateDao.ATTR_PROFILE:
                    data.put(ProfileDao.ATTR_DESCRIPTION, entry.getValue());
                    attr.add(ProfileDao.ATTR_ID);
                    query = this.masterService.profileQuery(data, attr);
                    if (query.calculateRecordNumber() > 0) {
                        entry.setValue(query.getRecordValues(0).get(ProfileDao.ATTR_ID));
                    } else {
                        toret = this.masterService.profileInsert(data);
                        entry.setValue(toret.get(ProfileDao.ATTR_ID));
                    }
                    break;
                case CandidateDao.ATTR_STATUS:
                    data.put(StatusDao.ATTR_DESCRIPTION, entry.getValue());
                    attr.add(StatusDao.ATTR_ID);
                    query = this.masterService.statusQuery(data, attr);
                    if (query.calculateRecordNumber() > 0) {
                        entry.setValue(query.getRecordValues(0).get(StatusDao.ATTR_ID));
                    } else {
                        toret = this.masterService.statusInsert(data);
                        entry.setValue(toret.get(StatusDao.ATTR_ID));
                    }
                    break;
                default:
                    break;
            }
        }
    }

    @Override
    public EntityResult candidateQuery(Map<String, Object> keyMap, List<String> attrList)
            throws OntimizeJEERuntimeException {
        return this.daoHelper.query(this.candidateDao, keyMap, attrList);
    }

    @Override
    public EntityResult candidateDelete(Map<String, Object> keyMap) throws OntimizeJEERuntimeException {
        return this.daoHelper.delete(this.candidateDao, keyMap);
    }
}
