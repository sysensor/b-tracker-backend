package com.sysensor.app.config;


public class APIConfig {

    public static final String API = "/api";
    public static final String CROSS_ORIGIN_URL = "*";

    public static final String DATA = "/data";

    public static final String BUS_OWNER = "bus-owner";
    public static final String DATA_API_BUS_OWNER = DATA + "/" + BUS_OWNER;

    public static final String BUS = "bus";
    public static final String DATA_API_BUS = DATA + "/" + BUS;

    public static final String BUS_ROUTE = "bus-route";
    public static final String DATA_API_BUS_ROUTE = DATA + "/" + BUS_ROUTE;

    public static final String TICKET = "ticket";
    public static final String DATA_API_TICKET = DATA + "/" + TICKET;

    public static final String PASSENGER = "passenger";
    public static final String DATA_API_PASSENGER = DATA + "/" + PASSENGER;

    public static final String USER = "user";
    public static final String DATA_API_USER = DATA + "/" + USER;

    public static final String TIME_KEEPER = "time-keeper";
    public static final String DATA_API_TIME_KEEPER = DATA + "/" + TIME_KEEPER;

}
