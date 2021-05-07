package br.edu.ufcg.computacao.eureca.backend.api.http.request;

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

@WebMvcTest(value = PublicKey.class, secure = false)
public class PublicKeyTest extends EndpointTest {

    private static final String PUBLIC_KEY = PublicKey.ENDPOINT;

    @Test
    public void testGetPublicKey() throws Exception {
        String response = "public key";
        Mockito.doReturn(response).when(this.facade).getPublicKey();

        RequestBuilder request = RequestFactory.createRequestBuilder(HttpMethod.GET, PUBLIC_KEY, new HttpHeaders(), "");

        MvcResult result = this.mockMvc.perform(request).andReturn();

        Assertions.assertEquals(HttpStatus.OK.value(), result.getResponse().getStatus());
        Assertions.assertEquals("{\"publicKey\":\"public key\"}", result.getResponse().getContentAsString());
    }
}
