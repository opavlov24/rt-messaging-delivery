package tech.letscode.challenge.rtmessaging.port.adapter.api;

import java.util.UUID;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import tech.letscode.challenge.rtmessaging.application.DummyApplicationService;
import tech.letscode.challenge.rtmessaging.domain.model.Dummy;

import static java.util.Collections.singletonList;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * @author Oleg Pavlov <oleg.pavlov@aol.com>
 */
@RunWith(SpringRunner.class)
@WebMvcTest(DummyJsonResource.class)
public class DummyJsonResourceTest
{
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private DummyApplicationService dummyApplicationService;

    @Test
    public void shouldGetTheListOfDummyJsons() throws Exception
    {
        //given
        PageRequest pageRequest = new PageRequest(0, 10);
        Page<Dummy> page = new PageImpl<>(
                singletonList(new Dummy(UUID.randomUUID().toString(), "message")), pageRequest, 1);
        when(this.dummyApplicationService.dummies(any(PageRequest.class))).thenReturn(page);
        MockHttpServletRequestBuilder request = get("/dummies");

        //expect
        this.mockMvc.perform(request)
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("$._embedded.dummies").isArray())
                    .andExpect(jsonPath("$._embedded.dummies[*].id").exists())
                    .andExpect(jsonPath("$._embedded.dummies[*].message").exists());
    }

    @Test
    public void shouldCreateANewDummy() throws Exception
    {
        //given
        MockHttpServletRequestBuilder request = post("/dummies")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content("{ \"message\": \"some message\" }");

        //expect
        this.mockMvc.perform(request).andExpect(status().isAccepted());

        //and
        verify(this.dummyApplicationService).createDummy(any());
    }

    @Test
    public void shouldReturn409IfMessageIsNotSpecified() throws Exception
    {
        //given
        MockHttpServletRequestBuilder request = post("/dummies")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content("{}");

        //expect
        this.mockMvc.perform(request).andExpect(status().isBadRequest());
    }
}