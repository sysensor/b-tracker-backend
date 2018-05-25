package com.sysensor.app.api;

import com.google.gson.Gson;
import com.sysensor.app.TestConst;
import com.sysensor.app.config.APIConfig;
import com.sysensor.app.model.Ticket;
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

import java.math.BigDecimal;
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
public class TicketDataAPITest {

    Gson JSON = new Gson();
    @Autowired
    private MockMvc mock;

    @Test
    public void ticketDataAPIShouldReturnTwoTickets() throws Exception {

        String userName = "admin";
        String userPassword = "admin";
        String userAuthorization = "Basic " + Base64.getEncoder().encodeToString((userName + ":" + userPassword).getBytes());


        this.mock.perform(get(APIConfig.DATA_API_TICKET)
                .accept(MediaType.APPLICATION_JSON_VALUE)
                .header("Authorization", userAuthorization)
        ).andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$._embedded.ticket").value(IsCollectionWithSize.hasSize(2)))
                .andExpect(jsonPath("$._embedded.ticket.[?(@._links.self.href=='http://localhost/data/ticket/" + TestConst.TICKET_ONE_UUID + "')].price").value(100.25))
                .andExpect(jsonPath("$._embedded.ticket.[?(@._links.self.href=='http://localhost/data/ticket/" + TestConst.TICKET_ONE_UUID + "')].start").value("Piliyandala"))
                .andExpect(jsonPath("$._embedded.ticket.[?(@._links.self.href=='http://localhost/data/ticket/" + TestConst.TICKET_ONE_UUID + "')].destination").value("Colombo"))
                .andExpect(jsonPath("$._embedded.ticket.[?(@._links.self.href=='http://localhost/data/ticket/" + TestConst.TICKET_ONE_UUID + "')].status").value(true));

    }

    @Test
    public void ticketDataAPIShouldCreateAndDeleteTheTicket() throws Exception {

        String userName = "admin";
        String userPassword = "admin";
        String userAuthorization = "Basic " + Base64.getEncoder().encodeToString((userName + ":" + userPassword).getBytes());

        Ticket ticket = new Ticket();
        ticket.setStart("Madapatha");
        ticket.setDestination("Piliyandala");
        ticket.setPrice(new BigDecimal(12.50));
        ticket.setStatus(true);

        String ticketJson = JSON.toJson(ticket);

        System.out.println("====" + ticketJson);
        List<String> selfList = new ArrayList<>();

        //Create Ticket
        this.mock.perform(post(APIConfig.DATA_API_TICKET)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .accept(MediaType.APPLICATION_JSON_VALUE)
                .header("Authorization", userAuthorization)
                .content(ticketJson)
        ).andDo(print())
                .andExpect(status().isCreated())
                .andExpect(jsonPath(".price").value(12.50))
                .andDo((result) -> {
                    JSONObject json = new JSONObject(result.getResponse().getContentAsString());
                    //Capture the returned SELF URL for the delete operation
                    selfList.add(json.getJSONObject("_links").getJSONObject("self").getString("href"));
                });

        //Check the Ticket list
        this.mock.perform(get(APIConfig.DATA_API_TICKET)
                .accept(MediaType.APPLICATION_JSON_VALUE)
                .header("Authorization", userAuthorization)
        ).andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$._embedded.ticket").value(IsCollectionWithSize.hasSize(3)));

        //Delete the Ticket
        this.mock.perform(delete(selfList.get(0))
                .accept(MediaType.APPLICATION_JSON_VALUE)
                .header("Authorization", userAuthorization)
        ).andDo(print())
                .andExpect(status().isNoContent());

        //Check the Ticket list
        this.mock.perform(get(APIConfig.DATA_API_TICKET)
                .accept(MediaType.APPLICATION_JSON_VALUE)
                .header("Authorization", userAuthorization)
        ).andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$._embedded.ticket").value(IsCollectionWithSize.hasSize(2)));
    }

}
