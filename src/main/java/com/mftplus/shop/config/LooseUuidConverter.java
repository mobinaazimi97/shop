package com.mftplus.shop.config;
//This Class For : No dash for UuId in URL


import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class LooseUuidConverter implements Converter<String, UUID> {
    @Override
    public UUID convert(String source) {
        if (source == null || source.isBlank()) {
            return null;
        }

        String cleaned = source.replaceAll("\\s+", "").toLowerCase();

        // اگر UUID بدون dash بود، dashها رو اضافه کن
        if (cleaned.matches("^[0-9a-f]{32}$")) {
            cleaned = cleaned.replaceAll(
                    "([0-9a-f]{8})([0-9a-f]{4})([0-9a-f]{4})([0-9a-f]{4})([0-9a-f]{12})",
                    "$1-$2-$3-$4-$5"
            );
        }

        return UUID.fromString(cleaned);
    }
}
