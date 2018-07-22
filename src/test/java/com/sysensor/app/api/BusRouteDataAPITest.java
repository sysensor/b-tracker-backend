package com.sysensor.app.api;

import com.google.gson.Gson;
import com.sysensor.app.TestConst;
import com.sysensor.app.config.APIConfig;
import com.sysensor.app.model.BusRoute;
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
public class BusRouteDataAPITest {

    Gson JSON = new Gson();
    @Autowired
    private MockMvc mock;

    @Test
    public void busRouteDataAPIShouldReturnTwoBusRoutes() throws Exception {

        String userName = "admin";
        String userPassword = "admin";
        String userAuthorization = "Basic " + Base64.getEncoder().encodeToString((userName + ":" + userPassword).getBytes());


        this.mock.perform(get(APIConfig.DATA_API_BUS_ROUTE)
                .accept(MediaType.APPLICATION_JSON_VALUE)
                .header("Authorization", userAuthorization))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$._embedded.bus-route").value(IsCollectionWithSize.hasSize(2)))
                .andExpect(jsonPath("$._embedded.bus-route.[?(@._links.self.href=='http://localhost/data/bus-route/" + TestConst.BUS_ROUTE_ONE_UUID + "')].name").value("RT120"))
                .andExpect(jsonPath("$._embedded.bus-route.[?(@._links.self.href=='http://localhost/data/bus-route/" + TestConst.BUS_ROUTE_TWO_UUID + "')].name").value("RT138"));
    }

    @Test
    public void busRouteDataAPIShouldCreateAndDeleteTheBusRoutes() throws Exception {

        String userName = "admin";
        String userPassword = "admin";
        String userAuthorization = "Basic " + Base64.getEncoder().encodeToString((userName + ":" + userPassword).getBytes());

        BusRoute busRoute = new BusRoute();
        busRoute.setName("RT567");
        busRoute.setStart("Kohuwala");
        busRoute.setDestination("Piliyandala");

        String busRouteJson = JSON.toJson(busRoute);

        System.out.println("====" + busRouteJson);
        List<String> selfList = new ArrayList<>();

        //Create BusRoute
        this.mock.perform(post(APIConfig.DATA_API_BUS_ROUTE)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .accept(MediaType.APPLICATION_JSON_VALUE)
                .header("Authorization", userAuthorization)
                .content(busRouteJson))
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(jsonPath(".name").value("RT567"))
                .andDo((result) -> {
                    JSONObject json = new JSONObject(result.getResponse().getContentAsString());
                    //Capture the returned SELF URL for the delete operation
                    selfList.add(json.getJSONObject("_links").getJSONObject("self").getString("href"));
                });

        //Check the BusRoute list
        this.mock.perform(get(APIConfig.DATA_API_BUS_ROUTE)
                .accept(MediaType.APPLICATION_JSON_VALUE)
                .header("Authorization", userAuthorization))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$._embedded.bus-route").value(IsCollectionWithSize.hasSize(3)));

        //Delete the BusRoute
        this.mock.perform(delete(selfList.get(0))
                .accept(MediaType.APPLICATION_JSON_VALUE)
                .header("Authorization", userAuthorization))
                .andDo(print())
                .andExpect(status().isNoContent());

        //Check the BusRoute list
        this.mock.perform(get(APIConfig.DATA_API_BUS_ROUTE)
                .accept(MediaType.APPLICATION_JSON_VALUE)
                .header("Authorization", userAuthorization))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$._embedded.bus-route").value(IsCollectionWithSize.hasSize(2)));

    }

}
