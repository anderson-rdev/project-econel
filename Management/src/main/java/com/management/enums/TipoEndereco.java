package com.management.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum TipoEndereco {
    ENTREGA, OUTRO, RESIDENCIAL, COMERCIAL, COBRANCA;

    @JsonCreator
    public static TipoEndereco fromString(String key) {
        return key == null ? null : TipoEndereco.valueOf(key.toUpperCase());
    }

    @JsonValue
    public String toValue() {
        return this.name();
    }
}
