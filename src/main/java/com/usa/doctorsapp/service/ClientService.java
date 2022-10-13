package com.usa.doctorsapp.service;

import com.usa.doctorsapp.model.Client;
import com.usa.doctorsapp.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClientService {

    @Autowired
    private ClientRepository clientRepository;

    public List<Client> getAll(){
        return clientRepository.getAll();
        }

    public Optional<Client> getById(Integer id){
        return clientRepository.getById(id);
    }

    public Client save(Client client){
        if(client.getIdClient()==null){
            return clientRepository.save(client);
        } else {
            Optional<Client> optionalClient=clientRepository.getById(client.getIdClient());
            if(optionalClient.isEmpty()){
                return clientRepository.save(client);
            } else {
                return client;
            }
        }
    }

    public Client update(Client client){
        if(client.getIdClient()!=null){
            Optional<Client> optionalClient=clientRepository.getById(client.getIdClient());
            if(!optionalClient.isEmpty()){
                if(client.getName()!=null){
                    optionalClient.get().setName(client.getName());
                }
                if(client.getEmail()!=null){
                    optionalClient.get().setEmail(client.getEmail());
                }
                if(client.getPassword()!=null){
                    optionalClient.get().setPassword(client.getPassword());
                }
                if(client.getAge()!=null){
                    optionalClient.get().setAge(client.getAge());
                }
                clientRepository.save(optionalClient.get());
                return optionalClient.get();
            } else {
                return client;
            }
        } else {
            return client;
        }
    }

    public boolean delete(Integer idClient){
        Boolean aBoolean=getById(idClient).map(client -> {
            clientRepository.delete(client);
            return true;
        }).orElse(false);
        return aBoolean;
    }
}
