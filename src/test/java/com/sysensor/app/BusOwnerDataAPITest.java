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

        this.mock.perform(get("/" + APIConfig.DATA_API_BUS_OWNER)
                .accept(MediaType.APPLICATION_JSON_VALUE)
        ).andDo(print()).andExpect(status().isOk())
                .andExpect(jsonPath("$._embedded.bus-owner").value(IsCollectionWithSize.hasSize(3)))
                .andExpect(jsonPath("$._embedded.bus-owner.[?(@.id==1)].name").value("Raju"))
                .andExpect(jsonPath("$._embedded.bus-owner.[?(@.id==1)].address").value("Colombo"))
                .andExpect(jsonPath("$._embedded.bus-owner.[?(@.id==1)].phone").value("0773005672"))
                .andExpect(content().string(CoreMatchers.containsString("{\n" +
                        "  \"_embedded\" : {\n" +
                        "    \"bus-owner\" : [ {\n" +
                        "      \"name\" : \"Raju\",\n" +
                        "      \"address\" : \"Colombo\",\n" +
                        "      \"phone\" : \"0773005672\",\n" +
                        "      \"id\" : 1,\n" +
                        "      \"_links\" : {\n" +
                        "        \"self\" : {\n" +
                        "          \"href\" : \"http://data/bus-owner/1\"\n" +
                        "        },\n" +
                        "        \"busOwner\" : {\n" +
                        "          \"href\" : \"http://data/bus-owner/1\"\n" +
                        "        }\n" +
                        "      }\n" +
                        "    }, {\n" +
                        "      \"name\" : \"Bandara\",\n" +
                        "      \"address\" : \"Gall\",\n" +
                        "      \"phone\" : \"0713005675\",\n" +
                        "      \"id\" : 2,\n" +
                        "      \"_links\" : {\n" +
                        "        \"self\" : {\n" +
                        "          \"href\" : \"http://data/bus-owner/2\"\n" +
                        "        },\n" +
                        "        \"busOwner\" : {\n" +
                        "          \"href\" : \"http://data/bus-owner/2\"\n" +
                        "        }\n" +
                        "      }\n" +
                        "    }, {\n" +
                        "      \"name\" : \"Selvam\",\n" +
                        "      \"address\" : \"Matara\",\n" +
                        "      \"phone\" : \"0793005675\",\n" +
                        "      \"id\" : 3,\n" +
                        "      \"_links\" : {\n" +
                        "        \"self\" : {\n" +
                        "          \"href\" : \"http://data/bus-owner/3\"\n" +
                        "        },\n" +
                        "        \"busOwner\" : {\n" +
                        "          \"href\" : \"http://data/bus-owner/3\"\n" +
                        "        }\n" +
                        "      }\n" +
                        "    } ]\n" +
                        "  },\n" +
                        "  \"_links\" : {\n" +
                        "    \"self\" : {\n" +
                        "      \"href\" : \"http://data/bus-owner\"\n" +
                        "    },\n" +
                        "    \"profile\" : {\n" +
                        "      \"href\" : \"http://data/profile/bus-owner\"\n" +
                        "    }\n" +
                        "  }\n" +
                        "}")));
    }

}
