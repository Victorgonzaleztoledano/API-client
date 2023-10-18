package com.APIclient.Controller;

import com.APIclient.Domain.EmptyGapException;
import com.APIclient.Domain.WrongArgumentExceptcion;

import java.util.Date;

public class ClientInput {
    private String dni;
    private String name;
    private Date date;
    private String country;
    private boolean premium;

    public ClientInput(String dni, String name, Date date, String country, boolean premium) throws WrongArgumentExceptcion, EmptyGapException {
        if(dni == null) throw new EmptyGapException("El dni no puede ser nulo");
        if(dni.trim().length() != 9) throw new WrongArgumentExceptcion("El dni introducido es incorrecto");
        if(dni.matches("^[0-9]{8}[a-zA-Z]$")) this.dni = dni;
        else throw new WrongArgumentExceptcion("El dni introducido es incorrecto");
        if(name == null) throw new EmptyGapException("El nombre no puede ser nulo");
        if(name.trim().length() < 1) throw new EmptyGapException("El nombre no puede estar vacío");
        if(name.matches("^[a-zA-Z]*$")) this.name = name;
        else throw new WrongArgumentExceptcion("El nombre solo debe contener letras");
        if(date == null) throw new EmptyGapException("La fecha no puede ser nula");
        else this.date = date;
        if(country == null) throw new EmptyGapException("El pais no puede ser nulo");
        if(country.trim().length() < 1) throw new EmptyGapException("El pais no puede estar vacío");
        if(country.matches("^[a-zA-Z]*$")) this.country = country;
        else throw new WrongArgumentExceptcion("El pais solo debe contener letras");
        this.premium = premium;
    }

    public String getDni() {
        return dni;
    }

    public String getName() {
        return name;
    }

    public Date getDate() {
        return date;
    }

    public String getCountry() {
        return country;
    }

    public boolean isPremium() {
        return premium;
    }
}
