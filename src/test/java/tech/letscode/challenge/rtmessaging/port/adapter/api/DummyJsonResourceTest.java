package tech.letscode.challenge.rtmessaging.port.adapter.api;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
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

    @Test
    public void shouldReturn200() throws Exception
    {
        //given
        MockHttpServletRequestBuilder request = get("/dummies");

        //expect
        this.mockMvc.perform(request).andExpect(status().isOk());
    }
}