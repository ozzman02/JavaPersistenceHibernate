package com.hibernate.basic.mapsid.client;

import com.hibernate.basic.mapsid.service.MapsIdService;
import com.hibernate.basic.mapsid.service.impl.MapsIdServiceImpl;

public class MapsIdClient {
    public static void main(String[] args) {
        MapsIdService service = new MapsIdServiceImpl();
        service.saveCustomerWithPassport();
        service.getCustomer();
    }
}
