package com.comida.enums;

public enum ComidaSabor {
    SALGADA("salgada"),
    DOCE("doce"),
    AMARGA("amarga");


    private final String comidaSabor;
    ComidaSabor(String comidaSabor) {
        this.comidaSabor = comidaSabor;
    }

    public String getComidaSabor() {
        return comidaSabor;
    }
}
