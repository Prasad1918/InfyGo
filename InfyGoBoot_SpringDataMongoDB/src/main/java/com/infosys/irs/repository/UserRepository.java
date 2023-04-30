package com.infosys.irs.repository;


import org.springframework.data.mongodb.repository.MongoRepository;
import com.infosys.irs.document.UserDocument;

public interface UserRepository extends MongoRepository<UserDocument, String>{
	
	UserDocument findByUserId(String userId);
}