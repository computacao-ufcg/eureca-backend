package br.edu.ufcg.computacao.eureca.backend.api.http.request;

import br.edu.ufcg.computacao.eureca.backend.core.ApplicationFacade;
import br.edu.ufcg.computacao.eureca.backend.util.TestUtils;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.mockito.BDDMockito;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import org.powermock.modules.junit4.PowerMockRunnerDelegate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;

@RunWith(PowerMockRunner.class)
@PowerMockRunnerDelegate(SpringRunner.class)
@PrepareForTest(ApplicationFacade.class)
public abstract class EndpointTest {

    @Autowired
    protected MockMvc mockMvc;

    protected ApplicationFacade facade;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        this.facade = Mockito.spy(ApplicationFacade.getInstance());
        PowerMockito.mockStatic(ApplicationFacade.class);
        BDDMockito.given(ApplicationFacade.getInstance()).willReturn(facade);
    }

    protected RequestBuilder getRequestBuilder(HttpMethod method, String url, HttpHeaders headers, String content) {
        HttpHeaders header = headers == null ? TestUtils.getTokenHeaders() : headers;
        return TestUtils.createRequestBuilder(method, url, header, content);
    }
}
