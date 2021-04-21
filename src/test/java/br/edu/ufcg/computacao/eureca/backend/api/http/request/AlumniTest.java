package br.edu.ufcg.computacao.eureca.backend.api.http.request;

import br.edu.ufcg.computacao.eureca.backend.api.http.CommonKeys;
import br.edu.ufcg.computacao.eureca.backend.api.http.response.AlumniDigestResponse;
import br.edu.ufcg.computacao.eureca.backend.util.RequestFactory;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.mockito.Mockito;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.ArrayList;
import java.util.Collection;

@WebMvcTest(value = Alumni.class, secure = false)
public class AlumniTest extends EndpointTest {

    private static final String ALUMNI_ENDPOINT = Alumni.ENDPOINT;

    @Test
    public void getAlumniTest() throws Exception {
        Collection<AlumniDigestResponse> response = new ArrayList<>();
        Mockito.doReturn(response).when(this.facade).getAlumniBasicData(Mockito.anyString(), Mockito.anyString(), Mockito.anyString());

        HttpHeaders headers = RequestFactory.getTokenHeaders();
        RequestBuilder request = RequestFactory.createRequestBuilder(HttpMethod.GET, ALUMNI_ENDPOINT, headers, "");

        MvcResult result = this.mockMvc.perform(request).andExpect(MockMvcResultMatchers.status().isOk()).andReturn();

        int expectedStatus = HttpStatus.OK.value();

        Assertions.assertEquals(expectedStatus, result.getResponse().getStatus());
        Assertions.assertEquals("[]", result.getResponse().getContentAsString());
    }
}
