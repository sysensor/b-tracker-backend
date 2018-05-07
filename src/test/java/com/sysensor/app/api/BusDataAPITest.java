package com.sysensor.app.api;

import com.google.gson.Gson;
import com.sysensor.app.config.APIConfig;
import com.sysensor.app.model.Bus;
import org.hamcrest.collection.IsCollectionWithSize;
import org.json.JSONObject;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class BusDataAPITest {

    @Autowired
    private MockMvc mock;
    Gson JSON = new Gson();

    @Test
    public void busDataAPIShouldReturnOneBus() throws Exception {

        String userName = "admin";
        String userPassword = "admin";
        String userAuthorization = "Basic " + Base64.getEncoder().encodeToString((userName + ":" + userPassword).getBytes());


        this.mock.perform(get(APIConfig.DATA_API_BUS)
                .accept(MediaType.APPLICATION_JSON_VALUE)
                .header("Authorization", userAuthorization)
        ).andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$._embedded.bus").value(IsCollectionWithSize.hasSize(3)))
                .andExpect(jsonPath("$._embedded.bus.[?(@._links.self.href=='http://localhost/data/bus/4028818462642c730162642c8d040008')].registration_no").value("RT120"))
                .andExpect(jsonPath("$._embedded.bus.[?(@._links.self.href=='http://localhost/data/bus/4028818462642c730162642c8d040009')].registration_no").value("RT130"))
                .andExpect(jsonPath("$._embedded.bus.[?(@._links.self.href=='http://localhost/data/bus/4028818462642c730162642c8d040010')].registration_no").value("RT400"));
    }

    @Test
    public void busDataAPIShouldCreateAndDeleteTheBus() throws Exception {
        String busOwnerUUID = "4028818462642c730162642c8d040001";

        String userName = "admin";
        String userPassword = "admin";
        String userAuthorization = "Basic " + Base64.getEncoder().encodeToString((userName + ":" + userPassword).getBytes());

        Bus bus = new Bus();
        bus.setRegistration_no("RT333");

        String busJson = JSON.toJson(bus);
        JSONObject busFinalJson = new JSONObject(busJson);
        busFinalJson.put("busOwner", APIConfig.DATA_API_BUS_OWNER + "/" + busOwnerUUID);

        System.out.println("====" + busFinalJson.toString());
        List<String> selfList = new ArrayList<>();

        //Create BUS
        this.mock.perform(post(APIConfig.DATA_API_BUS)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .accept(MediaType.APPLICATION_JSON_VALUE)
                .header("Authorization", userAuthorization)
                .content(busFinalJson.toString())
        ).andDo(print())
                .andExpect(status().isCreated())
                .andExpect(jsonPath(".registration_no").value("RT333"))
                .andDo((result) -> {
                    JSONObject json = new JSONObject(result.getResponse().getContentAsString());
                    //Capture the returned SELF URL for the delete operation
                    selfList.add(json.getJSONObject("_links").getJSONObject("self").getString("href"));
                });

        //Check the BUS list
        this.mock.perform(get(APIConfig.DATA_API_BUS)
                .accept(MediaType.APPLICATION_JSON_VALUE)
                .header("Authorization", userAuthorization)
        ).andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$._embedded.bus").value(IsCollectionWithSize.hasSize(4)));

        //Delete the BUS
        this.mock.perform(delete(selfList.get(0))
                .accept(MediaType.APPLICATION_JSON_VALUE)
                .header("Authorization", userAuthorization)
        ).andDo(print())
                .andExpect(status().isNoContent());

        //Check the BUS list
        this.mock.perform(get(APIConfig.DATA_API_BUS)
                .accept(MediaType.APPLICATION_JSON_VALUE)
                .header("Authorization", userAuthorization)
        ).andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$._embedded.bus").value(IsCollectionWithSize.hasSize(3)));


    }

}
