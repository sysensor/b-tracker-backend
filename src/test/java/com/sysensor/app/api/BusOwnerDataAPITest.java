package com.sysensor.app.api;

import com.google.gson.Gson;
import com.sysensor.app.config.APIConfig;
import com.sysensor.app.model.BusOwner;
import com.sysensor.app.repository.BusOwnerRepo;
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
public class BusOwnerDataAPITest {

    @Autowired
    private MockMvc mock;
    @Autowired
    private BusOwnerRepo busOwnerRepo;

    Gson JSON = new Gson();

    @Test
    public void busOwnerDataAPIShouldReturnThreeOwners() throws Exception {

        String userName = "admin";
        String userPassword = "admin";
        String userAuthorization = "Basic " + Base64.getEncoder().encodeToString((userName + ":" + userPassword).getBytes());


        this.mock.perform(get(APIConfig.DATA_API_BUS_OWNER)
                .accept(MediaType.APPLICATION_JSON_VALUE)
                .header("Authorization", userAuthorization)
        ).andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$._embedded.bus-owner").value(IsCollectionWithSize.hasSize(3)))
                .andExpect(jsonPath("$._embedded.bus-owner.[?(@._links.self.href=='http://localhost/data/bus-owner/4028818462642c730162642c8d040001')].name").value("Raju"))
                .andExpect(jsonPath("$._embedded.bus-owner.[?(@._links.self.href=='http://localhost/data/bus-owner/4028818462642c730162642c8d040001')].address").value("Colombo"))
                .andExpect(jsonPath("$._embedded.bus-owner.[?(@._links.self.href=='http://localhost/data/bus-owner/4028818462642c730162642c8d040001')].phone").value("0773005672"))
                .andExpect(jsonPath("$._embedded.bus-owner.[?(@._links.self.href=='http://localhost/data/bus-owner/4028818462642c730162642c8d040001')].username").value("raju"))
                .andExpect(jsonPath("$._embedded.bus-owner.[?(@._links.self.href=='http://localhost/data/bus-owner/4028818462642c730162642c8d040001')]._links.busList.href").value("http://localhost/data/bus-owner/4028818462642c730162642c8d040001/busList"));
    }

    @Test
    public void busOwnerDataAPIShouldCreateTheBusOwners() throws Exception {

        String userName = "admin";
        String userPassword = "admin";
        String userAuthorization = "Basic " + Base64.getEncoder().encodeToString((userName + ":" + userPassword).getBytes());

        BusOwner busOwner = new BusOwner();
        busOwner.setName("Malinda");
        busOwner.setAddress("Madapatha");
        busOwner.setPhone("7656789826");
        busOwner.setUsername("malinda");
        busOwner.setPassword("Test123");

        String busOwnerJson = JSON.toJson(busOwner);
        List<String> selfList = new ArrayList<>();

        this.mock.perform(post(APIConfig.DATA_API_BUS_OWNER)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .accept(MediaType.APPLICATION_JSON_VALUE)
                .header("Authorization", userAuthorization)
                .content(busOwnerJson)
        ).andDo(print())
                .andExpect(status().isCreated())
                .andExpect(jsonPath(".name").value("Malinda"))
                .andExpect(jsonPath(".address").value("Madapatha"))
                .andExpect(jsonPath(".phone").value("7656789826"))
                .andExpect(jsonPath(".username").value("malinda"))
                .andExpect(jsonPath(".password").doesNotExist())
                .andDo((result) -> {
                    JSONObject json = new JSONObject(result.getResponse().getContentAsString());
                    //Capture the returned SELF URL for the delete operation
                    selfList.add(json.getJSONObject("_links").getJSONObject("self").getString("href"));
                });

        this.mock.perform(delete(selfList.get(0))
                .accept(MediaType.APPLICATION_JSON_VALUE)
                .header("Authorization", userAuthorization)
        ).andDo(print())
                .andExpect(status().isNoContent());

    }

    @Test
    public void busOwnerDataAPIShouldDeleteTheBusOwners() throws Exception {

        String userName = "admin";
        String userPassword = "admin";
        String userAuthorization = "Basic " + Base64.getEncoder().encodeToString((userName + ":" + userPassword).getBytes());

        BusOwner busOwner = new BusOwner();
        busOwner.setName("Malinda");
        busOwner.setAddress("Madapatha");
        busOwner.setPhone("7656789826");
        busOwner.setUsername("malinda");
        busOwner.setPassword("Test123");

        String busOwnerJson = JSON.toJson(busOwner);
        List<String> selfList = new ArrayList<>();

        this.mock.perform(post(APIConfig.DATA_API_BUS_OWNER)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .accept(MediaType.APPLICATION_JSON_VALUE)
                .header("Authorization", userAuthorization)
                .content(busOwnerJson)
        ).andDo(print())
                .andExpect(status().isCreated())
                .andExpect(jsonPath(".name").value("Malinda"))
                .andExpect(jsonPath(".address").value("Madapatha"))
                .andExpect(jsonPath(".phone").value("7656789826"))
                .andExpect(jsonPath(".username").value("malinda"))
                .andExpect(jsonPath(".password").doesNotExist())
                .andExpect(jsonPath("._links.self.href").isNotEmpty())
                .andDo((result) -> {
                    JSONObject json = new JSONObject(result.getResponse().getContentAsString());
                    //Capture the returned SELF URL for the delete operation
                    selfList.add(json.getJSONObject("_links").getJSONObject("self").getString("href"));
                });


        this.mock.perform(delete(selfList.get(0))
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .accept(MediaType.APPLICATION_JSON_VALUE)
                .header("Authorization", userAuthorization)
        ).andDo(print())
                .andExpect(status().isNoContent());

    }


}
