package com.cisco.cs.helloworldrest.services;

import org.springframework.data.repository.CrudRepository;

import com.cisco.cs.helloworldrest.models.User;

public interface UserRepository extends CrudRepository<User, String> {

}
