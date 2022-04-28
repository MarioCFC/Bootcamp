/*
 * Copyright 2022 Imatia Innovation.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.ontimize.hr.model.core.dao;

import com.ontimize.jee.server.dao.common.ConfigurationFile;
import com.ontimize.jee.server.dao.jdbc.OntimizeJdbcDaoSupport;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Repository;

@Repository("CandidateDao")
@Lazy
@ConfigurationFile(configurationFile = "dao/CandidateDao.xml", configurationFilePlaceholder = "dao/placeholders.properties")
public class CandidateDao extends OntimizeJdbcDaoSupport {

    public static final String ATTR_ID = "ID";
    public static final String ATTR_PHOTO = "PHOTO";
    public static final String ATTR_NAME = "NAME";
    public static final String ATTR_SURNAME = "SURNAME";
    public static final String ATTR_BIRTHDAY = "BIRTHDAY";
    public static final String ATTR_DNI = "DNI";
    public static final String ATTR_PHONE = "PHONE";
    public static final String ATTR_EMAIL = "EMAIL";
    public static final String ATTR_EDUCATION = "EDUCATION";
    public static final String ATTR_SPECIALTIES = "SPECIALTIES";
    public static final String ATTR_STATUS = "STATUS";
    public static final String ATTR_ORIGIN = "ORIGIN";
    public static final String ATTR_WAGE_LEVEL = "WAGE_LEVEL";
    public static final String ATTR_EXPERIENCE_LEVEL = "EXPERIENCE_LEVEL";
    public static final String ATTR_PROFILE = "PROFILE";
    public static final String ATTR_COMMENT = "COMMENT";
    public static final String ATTR_LINKEDIN = "LINKEDIN";

}
