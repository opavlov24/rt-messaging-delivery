package tech.letscode.challenge.rtmessaging;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.DEFINED_PORT;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * @author Oleg Pavlov <oleg.pavlov@aol.com>
 */
@AutoConfigureMockMvc
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = DEFINED_PORT, properties = "server.port=8080")
public class ApplicationFuncTest
{

    private static final long SLEEP_TIME_IN_MS = 2000;

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void createDummy() throws Exception
    {
        //given
        MockHttpServletRequestBuilder request = MockMvcRequestBuilders.post("/dummies")
                                                                      .contentType(MediaType.APPLICATION_JSON_UTF8)
                                                                      .content("{ \"message\": \"Hello\" }");

        //when
        this.mockMvc.perform(request).andExpect(status().isAccepted());

        //and
        Thread.sleep(SLEEP_TIME_IN_MS);

        //then
        this.mockMvc.perform(MockMvcRequestBuilders.get("/dummies"))
                    .andExpect(jsonPath("$._embedded.dummies").exists());

    }
}
