package br.com.brunaenaira.springpattern.service.impl;

import br.com.brunaenaira.springpattern.model.Address;
import br.com.brunaenaira.springpattern.model.AddressRepository;
import br.com.brunaenaira.springpattern.model.Client;
import br.com.brunaenaira.springpattern.model.ClientRepository;
import br.com.brunaenaira.springpattern.service.CepService;
import br.com.brunaenaira.springpattern.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;

public class ClientServiceImpl implements ClientService {
    @Autowired
    private ClientRepository clientRepository;
    @Autowired
    private AddressRepository addressRepository;

    @Autowired
    private CepService cepService;

    @Override
    public Iterable<Client> getAll() {
        return clientRepository.findAll();
    }

    @Override
    public Client getById(Long id) {
        return clientRepository.findById(id).get();
    }

    @Override
    public void create(Client client) {
        saveClientWithCep(client);
    }

    private void saveClientWithCep(Client client) {
        String cep = client.getAddress().getCep();
        Address address = addressRepository.findById(cep).orElseGet(() -> {
            Address newAddress = cepService.searchCep(cep);
            addressRepository.save(newAddress);
            return newAddress;
        });
        client.setAddress(address);
        clientRepository.save(client);
    }

    @Override
    public void update(Long id, Client client) {
        var clientDb = clientRepository.findById(id);
        if (clientDb.isPresent()) {
            saveClientWithCep(client);
        }
    }

    @Override
    public void delete(Long id) {
        clientRepository.deleteById(id);
    }
}
