package br.edu.ufcg.computacao.eureca.backend.api.http.request;

import br.edu.ufcg.computacao.eureca.backend.api.http.response.curriculum.CurriculumCodesResponse;
import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;

import java.util.Arrays;

@WebMvcTest(value = Curricula.class, secure = false)
public class CurriculaTest extends EndpointTest {

    private static final String CURRICULA_ENDPOINT = Curricula.ENDPOINT;

    @Test
    public void testGetCurriculumCodes() throws Exception {
        CurriculumCodesResponse response = new CurriculumCodesResponse(Arrays.asList("1990", "2017"));
        Mockito.doReturn(response).when(this.facade).getCurriculumCodes(Mockito.anyString(), Mockito.anyString());

        RequestBuilder request = this.getRequestBuilder(HttpMethod.GET, CURRICULA_ENDPOINT + "?courseCode=14102100");
        MvcResult result = this.mockMvc.perform(request).andReturn();

        int expectedResponseStatus = HttpStatus.OK.value();
        int actualResponseStatus = result.getResponse().getStatus();

        Assert.assertEquals(expectedResponseStatus, actualResponseStatus);
        Assert.assertEquals("{\"curriculumCodes\":[\"1990\",\"2017\"]}", result.getResponse().getContentAsString());
    }
}
