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
package com.ontimize.hs.ws.core.rest;

import com.ontimize.hr.ws.core.rest.OfferRestController;
import com.ontimize.jee.common.db.SQLStatementBuilder.BasicExpression;
import java.util.Calendar;
import java.util.HashMap;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 *
 * @author mario
 */
public class OfferRestControllerTest {

    OfferRestController orc;

    @BeforeAll
    public static void setUpClass() {
    }

    @AfterAll
    public static void tearDownClass() {
    }

    @BeforeEach
    public void setUp() {
    }

    @AfterEach
    public void tearDown() {
        orc = new OfferRestController();
    }

    @Test
    public void searchBetweenYearsTest() {
        BasicExpression bex = orc.searchBetweenWithYear("DATE", 2021);
        HashMap<String, Object> map = new HashMap<>();
        Calendar cal = Calendar.getInstance();
        cal.set(2020, 5, 5, 0, 0, 0);
        map.put("DATE", cal.getTime());
        Assertions.assertFalse(bex.evaluate(map));
    }

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    // @Test
    // public void hello() {}
}
