package com.APIclient.Service;

import com.APIclient.Controller.BillCompleteOutput;
import com.APIclient.Controller.ClientCompleteOutput;
import com.APIclient.Controller.ClientInput;
import com.APIclient.Controller.ClientNameDniOutput;
import com.APIclient.Domain.Bill;
import com.APIclient.Domain.Client;
import com.APIclient.Domain.EmptyGapException;
import com.APIclient.Domain.WrongArgumentExceptcion;
import com.APIclient.Repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ClientService {
    @Autowired
    private ClientRepository clientRepository;

    public void addClient(ClientInput clientInput) throws AlreadyExistsException, WrongArgumentExceptcion, EmptyGapException {
        if (clientRepository.existsById(clientInput.getDni()))
            throw new AlreadyExistsException("El cliente ya está registrado");
        else {
            Client client = Client.getClient(clientInput);
            clientRepository.save(client);
        }
    }

    public List<ClientCompleteOutput> readClients() throws WrongArgumentExceptcion, EmptyGapException {
        List<Client> clients = clientRepository.findAll();
        List<ClientCompleteOutput> clientsComplete = new ArrayList<>();
        for (Client client : clients) {
            clientsComplete.add(ClientCompleteOutput.getClientComplete(client));
        }
        return clientsComplete;
    }

    public List<ClientNameDniOutput> getClientsByNameYDni(boolean vip, String country) throws WrongArgumentExceptcion, EmptyGapException {
        List<Client> clients = clientRepository.findByPremiumAndCountryOrderByNameAsc(vip, country);
        List<ClientNameDniOutput> clientsOutput = new ArrayList<>();
        for (Client client : clients) {
            clientsOutput.add(ClientNameDniOutput.getClient(client));
        }
        return clientsOutput;
    }
}