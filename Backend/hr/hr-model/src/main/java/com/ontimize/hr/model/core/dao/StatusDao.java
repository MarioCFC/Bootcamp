package com.ontimize.hr.model.core.dao;

import com.ontimize.jee.server.dao.common.ConfigurationFile;
import com.ontimize.jee.server.dao.jdbc.OntimizeJdbcDaoSupport;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Repository;

@Repository("StatusDao")
@Lazy
@ConfigurationFile(configurationFile = "dao/StatusDao.xml", configurationFilePlaceholder = "dao/placeholders.properties")
public class StatusDao extends OntimizeJdbcDaoSupport {

    public static final String ATTR_ID = "ID";
    public static final String ATTR_DESCRIPTION = "DESCRIPTION";
}
