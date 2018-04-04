package com.sysensor.app;

import com.sysensor.app.config.APIConfig;
import org.hamcrest.CoreMatchers;
import org.hamcrest.collection.IsCollectionWithSize;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Base64;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class BusOwnerDataAPITest {

    @Autowired
    private MockMvc mock;

    @Test
    public void busOwnerDataRepositoryShouldReturnThreeOwners() throws Exception {

        String userName = "admin";
        String userPassword = "admin";
        String userAuthorization = "Basic " + Base64.getEncoder().encodeToString((userName + ":" + userPassword).getBytes());


        this.mock.perform(get(APIConfig.DATA_API_BUS_OWNER)
                .accept(MediaType.APPLICATION_JSON_VALUE)
                .header("Authorization", userAuthorization)
        ).andDo(print()).andExpect(status().isOk())
                .andExpect(jsonPath("$._embedded.bus-owner").value(IsCollectionWithSize.hasSize(3)))
                .andExpect(jsonPath("$._embedded.bus-owner.[?(@._links.self.href=='http://localhost/data/bus-owner/4028818462642c730162642c8d040001')].name").value("Raju"))
                .andExpect(jsonPath("$._embedded.bus-owner.[?(@._links.self.href=='http://localhost/data/bus-owner/4028818462642c730162642c8d040001')].address").value("Colombo"))
                .andExpect(jsonPath("$._embedded.bus-owner.[?(@._links.self.href=='http://localhost/data/bus-owner/4028818462642c730162642c8d040001')].phone").value("0773005672"))
                .andExpect(content().string(CoreMatchers.containsString("{\n" +
                        "  \"_embedded\" : {\n" +
                        "    \"bus-owner\" : [ {\n" +
                        "      \"name\" : \"Raju\",\n" +
                        "      \"address\" : \"Colombo\",\n" +
                        "      \"phone\" : \"0773005672\",\n" +
                        "      \"_links\" : {\n" +
                        "        \"self\" : {\n" +
                        "          \"href\" : \"http://localhost/data/bus-owner/4028818462642c730162642c8d040001\"\n" +
                        "        },\n" +
                        "        \"busOwner\" : {\n" +
                        "          \"href\" : \"http://localhost/data/bus-owner/4028818462642c730162642c8d040001\"\n" +
                        "        }\n" +
                        "      }\n" +
                        "    }, {\n" +
                        "      \"name\" : \"Bandara\",\n" +
                        "      \"address\" : \"Gall\",\n" +
                        "      \"phone\" : \"0713005675\",\n" +
                        "      \"_links\" : {\n" +
                        "        \"self\" : {\n" +
                        "          \"href\" : \"http://localhost/data/bus-owner/4028818462642c730162642c8d040002\"\n" +
                        "        },\n" +
                        "        \"busOwner\" : {\n" +
                        "          \"href\" : \"http://localhost/data/bus-owner/4028818462642c730162642c8d040002\"\n" +
                        "        }\n" +
                        "      }\n" +
                        "    }, {\n" +
                        "      \"name\" : \"Selvam\",\n" +
                        "      \"address\" : \"Matara\",\n" +
                        "      \"phone\" : \"0793005675\",\n" +
                        "      \"_links\" : {\n" +
                        "        \"self\" : {\n" +
                        "          \"href\" : \"http://localhost/data/bus-owner/4028818462642c730162642c8d040003\"\n" +
                        "        },\n" +
                        "        \"busOwner\" : {\n" +
                        "          \"href\" : \"http://localhost/data/bus-owner/4028818462642c730162642c8d040003\"\n" +
                        "        }\n" +
                        "      }\n" +
                        "    } ]\n" +
                        "  },\n" +
                        "  \"_links\" : {\n" +
                        "    \"self\" : {\n" +
                        "      \"href\" : \"http://localhost/data/bus-owner\"\n" +
                        "    },\n" +
                        "    \"profile\" : {\n" +
                        "      \"href\" : \"http://localhost/data/profile/bus-owner\"\n" +
                        "    }\n" +
                        "  }")));
    }

}
