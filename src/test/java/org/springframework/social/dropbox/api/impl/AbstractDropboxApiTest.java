package org.springframework.social.dropbox.api.impl;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.Before;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.social.test.client.MockRestServiceServer;


/**
 * User: Bryce Fischer
 * Date: 7/7/11
 * Time: 1:01 PM
 */
public abstract class AbstractDropboxApiTest {
    protected DropboxTemplate dropbox;
    protected MockRestServiceServer mockServer;
    protected HttpHeaders responseHeaders;

    @Before
    public void setUp() throws Exception {
        dropbox = new DropboxTemplate("API_KEY", "API_SECRET", "ACCESS_TOKEN", "ACCESS_TOKEN_SECRET", false);
        mockServer = MockRestServiceServer.createServer(dropbox.getRestTemplate());
        responseHeaders = new HttpHeaders();
        responseHeaders.setContentType(MediaType.APPLICATION_JSON);
    }

	protected Resource jsonResource(String filename) {
		return new ClassPathResource(filename + ".json");
	}
	
	protected Date fromDropboxDate(String date) {
		try {
			return new SimpleDateFormat("EEE, d MMM yyyy HH:mm:ss Z").parse(date);
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
}
