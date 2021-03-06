/*-
 * ============LICENSE_START=======================================================
 * VID ASDC Client
 * ================================================================================
 * Copyright (C) 2017 AT&T Intellectual Property. All rights reserved.
 * ================================================================================
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
 * ============LICENSE_END=========================================================
 */

package org.openecomp.vid.asdc;

import java.io.IOException;
import java.io.InputStream;
import java.net.URISyntaxException;

import org.codehaus.jackson.map.ObjectMapper;
import org.json.JSONObject;
import org.json.JSONTokener;
import org.junit.Before;
import org.junit.Test;

import org.openecomp.vid.asdc.memory.InMemoryAsdcClient;

/**
 * The Class InMemoryClientTest.
 */
public class InMemoryClientTest extends BaseClientTest {

	/** The catalog. */
	private JSONObject catalog;

	/**
	 * Sets the up.
	 *
	 * @throws URISyntaxException the URI syntax exception
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	@Before
	public void setUp() throws URISyntaxException, IOException {
		final InputStream asdcCatalogFile = getClass().getClassLoader().getResourceAsStream("catalog.json");
		
		final JSONTokener tokener = new JSONTokener(asdcCatalogFile);
		
		catalog = new JSONObject(tokener);
	}
	
	/**
	 * Test resources.
	 *
	 * @throws AsdcCatalogException the asdc catalog exception
	 */
	@Test
	public void testResources() throws AsdcCatalogException {
		
		runResourceTests(new InMemoryAsdcClient.Builder().catalog(catalog).mapper(new ObjectMapper()).build());
	}
	
	/**
	 * Test services.
	 *
	 * @throws AsdcCatalogException the asdc catalog exception
	 * @throws URISyntaxException the URI syntax exception
	 */
	@Test
	public void testServices() throws AsdcCatalogException, URISyntaxException {

		runServiceTests(new InMemoryAsdcClient.Builder().catalog(catalog).build());
	}
}
