package com.APIclient.Controller;

import com.APIclient.Domain.Bill;
import com.APIclient.Domain.EmptyGapException;
import com.APIclient.Domain.WrongArgumentExceptcion;
import com.APIclient.Service.AlreadyExistsException;
import com.APIclient.Service.BillService;
import com.APIclient.Service.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class BillController {
    @Autowired
    private BillService billService;

    @GetMapping("/bills")
    public ResponseEntity<List<BillCompleteOutput>> readClients() {
        try {
            return ResponseEntity.ok(billService.readBills());
        } catch (WrongArgumentExceptcion e) {
            System.out.println(e.getMessage());
            return ResponseEntity.badRequest().build();
        } catch (EmptyGapException e) {
            System.out.println(e.getMessage());
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/bills")
    public ResponseEntity addBill(@RequestBody BillInput billInput) {
        try {
            billService.addBill(billInput);
            return ResponseEntity.status(HttpStatus.CREATED).build();
        } catch (AlreadyExistsException e) {
            System.out.println(e.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).build();
        } catch (WrongArgumentExceptcion e) {
            System.out.println(e.getMessage());
            return ResponseEntity.badRequest().build();
        } catch (EmptyGapException e) {
            System.out.println(e.getMessage());
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/bills/{dni}")
    public ResponseEntity<List<BillCodTotalOutput>> readBillsById(@PathVariable String dni) {
        try {
            List<BillCodTotalOutput> bill = billService.readBillsById(dni);
            return ResponseEntity.ok(bill);
        } catch (NotFoundException e) {
            System.out.println(e.getMessage());
            return ResponseEntity.notFound().build();
        } catch (WrongArgumentExceptcion e) {
            System.out.println(e.getMessage());
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/bills/findBy")
    public ResponseEntity<List<BillCompleteOutput>> readBillsByMesYAnyo(@RequestParam int mes, @RequestParam int anyo) {
        try {
            List<BillCompleteOutput> bills = billService.readBillsByMesYAnyo(mes, anyo);
            return ResponseEntity.ok(bills);
        } catch (WrongArgumentExceptcion e) {
            System.out.println(e.getMessage());
            return ResponseEntity.badRequest().build();
        } catch (EmptyGapException e) {
            System.out.println(e.getMessage());
            return ResponseEntity.notFound().build();
        }
    }
}