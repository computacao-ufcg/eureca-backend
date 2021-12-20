package br.edu.ufcg.computacao.eureca.backend.api.http.request;

import br.edu.ufcg.computacao.eureca.backend.api.http.response.VersionResponse;
import br.edu.ufcg.computacao.eureca.backend.constants.SystemConstants;
import br.edu.ufcg.computacao.eureca.common.util.BuildNumberHolder;
import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;

@WebMvcTest(value = Version.class, secure = false)
public class VersionTest extends EndpointTest {

    private static final String VERSION_ENDPOINT = Version.ENDPOINT;

    @Test
    public void testGetVersion() throws Exception {
        String buildNumber = BuildNumberHolder.getInstance().getBuildNumber();
        String versionNumber = SystemConstants.API_VERSION_NUMBER + "-" + buildNumber;
        VersionResponse response = new VersionResponse(versionNumber);
        Mockito.doReturn(response).when(this.facade).getVersion();

        RequestBuilder request = this.getRequestBuilder(HttpMethod.GET, VERSION_ENDPOINT);
        MvcResult result = this.mockMvc.perform(request).andReturn();

        int expectedResponseStatus = HttpStatus.OK.value();
        int actualResponseStatus = result.getResponse().getStatus();

        Assert.assertEquals(expectedResponseStatus, actualResponseStatus);
        Assert.assertEquals("{\"version\":\"1.0.0-no-build-number\"}", result.getResponse().getContentAsString());
    }
}
