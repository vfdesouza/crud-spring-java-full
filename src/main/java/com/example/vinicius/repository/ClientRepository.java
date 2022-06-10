package com.example.vinicius.repository;

import com.example.vinicius.models.Client;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClientRepository extends CrudRepository<Client, Long> {
    Client findById(long id);
    List<Client> findByNameIgnoreCaseContaining(String name);
}
