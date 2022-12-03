package com.proyecto.sistemagestion.enums;

public enum Enum_Rol {
    ROLE_USER ("Operativo"),
    ROLE_ADMIN ("Administrador");

    private String name;
    Enum_Rol(String name){
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
