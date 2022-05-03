package com.ontimize.hr.model.core.dao;

import com.ontimize.jee.server.dao.common.ConfigurationFile;
import com.ontimize.jee.server.dao.jdbc.OntimizeJdbcDaoSupport;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Repository;

@Repository("ProfileDao")
@Lazy
@ConfigurationFile(configurationFile = "dao/ProfileDao.xml", configurationFilePlaceholder = "dao/placeholders.properties")
public class ProfileDao extends OntimizeJdbcDaoSupport {

    public static final String ATTR_ID = "ID";
    public static final String ATTR_DESCRIPTION = "DESCRIPTION";
}
