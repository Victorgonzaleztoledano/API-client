package com.APIclient.Controller;

import com.APIclient.Domain.Bill;
import com.APIclient.Domain.WrongArgumentExceptcion;

public class BillCodTotalOutput {
    private int code;
    private float total;

    public BillCodTotalOutput(int code, float total) throws WrongArgumentExceptcion {
        if(code < 1 || code > 99) throw new WrongArgumentExceptcion("Introduce un código del 1 al 99");
        else this.code = code;
        if(total < 0.1) throw new WrongArgumentExceptcion("Introduce un total válido");
        else this.total = total;
    }

    public static BillCodTotalOutput getBill(Bill bill) throws WrongArgumentExceptcion {
        return new BillCodTotalOutput(bill.getCode(), bill.getTotal());
    }

    public int getCode() {
        return code;
    }

    public float getTotal() {
        return total;
    }
}
