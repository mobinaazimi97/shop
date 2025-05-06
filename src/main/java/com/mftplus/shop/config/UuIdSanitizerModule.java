package com.mftplus.shop.config;
//This Class For : No dash for UuId in body

import com.fasterxml.jackson.databind.module.SimpleModule;

import java.util.UUID;

public class UuIdSanitizerModule extends SimpleModule {

    public UuIdSanitizerModule() {
        super("UuIdSanitizerModule");
        this.addDeserializer(UUID.class, new UuIdSanitizer());
    }

}
