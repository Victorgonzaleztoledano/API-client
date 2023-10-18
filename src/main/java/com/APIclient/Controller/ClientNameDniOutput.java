package com.APIclient.Controller;

import com.APIclient.Domain.Client;
import com.APIclient.Domain.EmptyGapException;
import com.APIclient.Domain.WrongArgumentExceptcion;

public class ClientNameDniOutput {
    private String name;
    private String dni;

    public ClientNameDniOutput(String name, String dni) throws WrongArgumentExceptcion, EmptyGapException {
        if(dni == null) throw new EmptyGapException("El dni no puede ser nulo");
        if(dni.trim().length() != 9) throw new WrongArgumentExceptcion("El dni introducido es incorrecto");
        if(dni.matches("^[0-9]{8}[a-zA-Z]$")) this.dni = dni;
        else throw new WrongArgumentExceptcion("El dni introducido es incorrecto");
        if(name == null) throw new EmptyGapException("El nombre no puede ser nulo");
        if(name.trim().length() < 1) throw new EmptyGapException("El nombre no puede estar vacío");
        if(name.matches("^[a-zA-Z]*$")) this.name = name;
        else throw new WrongArgumentExceptcion("El nombre solo debe contener letras");
    }

    public static ClientNameDniOutput getClient(Client client) throws WrongArgumentExceptcion, EmptyGapException {
        return new ClientNameDniOutput(client.getName(), client.getDni());
    }

    public String getName() {
        return name;
    }

    public String getDni() {
        return dni;
    }
}
