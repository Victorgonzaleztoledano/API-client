package com.APIclient.Service;

import com.APIclient.Controller.BillCodTotalOutput;
import com.APIclient.Controller.BillCompleteOutput;
import com.APIclient.Controller.BillInput;
import com.APIclient.Domain.Bill;
import com.APIclient.Domain.EmptyGapException;
import com.APIclient.Domain.WrongArgumentExceptcion;
import com.APIclient.Repository.BillRepository;
import com.APIclient.Repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BillService {
    @Autowired
    private BillRepository billRepository;
    @Autowired
    private ClientRepository clientRepository;

    public void addBill(BillInput billInput) throws AlreadyExistsException, WrongArgumentExceptcion, EmptyGapException {
        if (billRepository.existsById(billInput.getCode()))
            throw new AlreadyExistsException("El código de factura ya existe");
        else {
            Bill bill = Bill.getBill(billInput);
            billRepository.save(bill);
        }
    }

    public List<BillCompleteOutput> readBills() throws WrongArgumentExceptcion, EmptyGapException {
        List<Bill> bills = billRepository.findAll();
        List<BillCompleteOutput> billsComplete = new ArrayList<>();
        for (Bill bill : bills) {
            billsComplete.add(BillCompleteOutput.getBillComplete(bill));
        }
        return billsComplete;
    }
    public List<BillCodTotalOutput> readBillsById(String dni) throws NotFoundException, WrongArgumentExceptcion {
        if(clientRepository.existsById(dni)){
            List<Bill> bills = billRepository.findByDni(dni);
            List<BillCodTotalOutput> billsComplete = new ArrayList<>();
            for(Bill bill : bills){
                billsComplete.add(BillCodTotalOutput.getBill(bill));
            }
            return billsComplete;
        }
       else throw new NotFoundException("El dni introducido no está registrado");
    }
    public List<BillCompleteOutput> readBillsByMesYAnyo(int mes, int anyo) throws WrongArgumentExceptcion, EmptyGapException {
        List<Bill> bills = billRepository.findByMesAndAnyo(mes, anyo);
        List<BillCompleteOutput> billsComplete = new ArrayList<>();
        for(Bill bill : bills){
            billsComplete.add(BillCompleteOutput.getBillComplete(bill));
        }
        return billsComplete;
    }
}
