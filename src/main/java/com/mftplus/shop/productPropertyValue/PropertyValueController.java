package com.mftplus.shop.productPropertyValue;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/values")
public class PropertyValueController {

    private final PropertyValueService propertyValueService;

    public PropertyValueController(PropertyValueService propertyValueService) {
        this.propertyValueService = propertyValueService;
    }

}
