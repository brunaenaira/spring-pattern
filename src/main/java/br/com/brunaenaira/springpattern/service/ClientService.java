package br.com.brunaenaira.springpattern.service;

import br.com.brunaenaira.springpattern.model.Client;

public interface ClientService {

    Iterable<Client> getAll();

    Client getById(Long id);

    void create(Client client);

    void update(Long id, Client client);

    void delete(Long id);
}
