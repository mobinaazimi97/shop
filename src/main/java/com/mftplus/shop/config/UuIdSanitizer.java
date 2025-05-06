package com.mftplus.shop.config;
//This Class For : No dash for UuId in body

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

import java.io.IOException;
import java.util.UUID;

public class UuIdSanitizer extends JsonDeserializer<UUID> {
    @Override
    public UUID deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        String raw = p.getText().replaceAll("\\s+", "").toLowerCase();

        // اگر UUID بدون dash بود، dashها رو اضافه کن
        if (raw.matches("^[0-9a-f]{32}$")) {
            raw = raw.replaceAll(
                    "(\\p{XDigit}{8})(\\p{XDigit}{4})(\\p{XDigit}{4})(\\p{XDigit}{4})(\\p{XDigit}{12})",
                    "$1-$2-$3-$4-$5"
            );
        }

        return UUID.fromString(raw);
    }
}
