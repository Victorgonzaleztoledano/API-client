package com.APIclient.Controller;

import com.APIclient.Domain.Client;
import com.APIclient.Domain.EmptyGapException;
import com.APIclient.Domain.WrongArgumentExceptcion;
import com.APIclient.Service.AlreadyExistsException;
import com.APIclient.Service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@Controller
public class ClientController {
    @Autowired
    private ClientService clientService;

    @GetMapping("/clients")
    public ResponseEntity<List<ClientCompleteOutput>> readClients() {
        try {
            List<ClientCompleteOutput> clients = clientService.readClients();
            return ResponseEntity.ok(clients);
        } catch (WrongArgumentExceptcion e) {
            System.out.println(e.getMessage());
            return ResponseEntity.badRequest().build();
        } catch (EmptyGapException e) {
            System.out.println(e.getMessage());
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/clients")
    public ResponseEntity addClient(@RequestBody ClientInput clientInput){
        try {
            clientService.addClient(clientInput);
            return ResponseEntity.status(HttpStatus.CREATED).build();
        }
        catch (AlreadyExistsException e){
            System.out.println(e.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).build();
        }
        catch (WrongArgumentExceptcion e){
            System.out.println(e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        catch (EmptyGapException e){
            System.out.println(e.getMessage());
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/clients/findBy")
    public ResponseEntity<List<ClientNameDniOutput>> getClientsOrdered(@RequestParam boolean premium, @RequestParam String pais) throws WrongArgumentExceptcion, EmptyGapException {
        List<ClientNameDniOutput> clients = clientService.getClientsByNameYDni(premium, pais);
        return ResponseEntity.ok(clients);
    }
}
