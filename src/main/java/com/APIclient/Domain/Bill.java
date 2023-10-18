package com.APIclient.Domain;

import com.APIclient.Controller.BillInput;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.time.Year;

@Entity
public class Bill {
    @Id
    private int code;
    private float total;
    private int mes;
    private int anyo;
    private String dni;

    public Bill(int code, float total, int mes, int anyo, String dni) throws WrongArgumentExceptcion, EmptyGapException {
        if(code < 1 || code > 99) throw new WrongArgumentExceptcion("Introduce un código del 1 al 99");
        else this.code = code;
        if(total < 0.1) throw new WrongArgumentExceptcion("Introduce un total válido");
        else this.total = total;
        if(mes < 1 || mes > 12) throw new WrongArgumentExceptcion("Introduce un mes válido");
        else this.mes = mes;
        if (anyo < 2000 || anyo > Year.now().getValue()) throw new WrongArgumentExceptcion("Introduce un año válido");
        else this.anyo = anyo;
        if(dni == null) throw new EmptyGapException("El dni no puede ser nulo");
        if(dni.trim().length() != 9) throw new WrongArgumentExceptcion("El dni introducido es incorrecto");
        if(dni.matches("^[0-9]{8}[a-zA-Z]$")) this.dni = dni;
    }

    public Bill() {
    }

    public static Bill getBill(BillInput bill) throws WrongArgumentExceptcion, EmptyGapException {
        return new Bill(bill.getCode(), bill.getTotal(), bill.getMonth(), bill.getYear(), bill.getDni());
    }

    public int getCode() {
        return code;
    }

    public float getTotal() {
        return total;
    }

    public int getMonth() {
        return mes;
    }

    public int getYear() {
        return anyo;
    }

    public String getDni() {
        return dni;
    }
}