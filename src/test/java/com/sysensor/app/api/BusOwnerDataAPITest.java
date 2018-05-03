package com.sysensor.app.api;

import com.google.gson.Gson;
import com.sysensor.app.config.APIConfig;
import com.sysensor.app.model.BusOwner;
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
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class BusOwnerDataAPITest {

    @Autowired
    private MockMvc mock;

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
        Gson json = new Gson();
        String busOwnerJson = json.toJson(busOwner);


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
                .andExpect(jsonPath(".password").doesNotExist());

    }


}
