package com.hibernate.basic._04_mapsid.client;

import com.hibernate.basic._04_mapsid.service.MapsIdService;
import com.hibernate.basic._04_mapsid.service.impl.MapsIdServiceImpl;

public class MapsIdClient {
    public static void main(String[] args) {
        MapsIdService service = new MapsIdServiceImpl();
        service.saveCustomerWithPassport();
        service.getCustomer();
    }
}
