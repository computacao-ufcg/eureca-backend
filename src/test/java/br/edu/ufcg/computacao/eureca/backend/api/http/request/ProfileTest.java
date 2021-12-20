package br.edu.ufcg.computacao.eureca.backend.api.http.request;

import br.edu.ufcg.computacao.eureca.backend.api.http.response.profile.ProfileResponse;
import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;

@WebMvcTest(value = Profile.class, secure = false)
public class ProfileTest extends EndpointTest {

    private static final String PROFILE_ENDPOINT = Profile.ENDPOINT;

    @Test
    public void testGetProfile() throws Exception {
        ProfileResponse response = new ProfileResponse("14102100", "C. Computação");
        Mockito.doReturn(response).when(this.facade).getProfile(Mockito.anyString());

        RequestBuilder request = this.getRequestBuilder(HttpMethod.GET, PROFILE_ENDPOINT);
        MvcResult result = this.mockMvc.perform(request).andReturn();

        int expectedResponseStatus = HttpStatus.OK.value();
        int actualResponseStatus = result.getResponse().getStatus();

        Assert.assertEquals(expectedResponseStatus, actualResponseStatus);
        Assert.assertEquals("{\"courseCode\":\"14102100\",\"courseName\":\"C. Computação\"}", result.getResponse().getContentAsString());
    }
}
