package com.infosys.irs.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import com.infosys.irs.entity.UserEntity;
import com.infosys.irs.exception.UserIdAlreadyPresentException;
import com.infosys.irs.model.User;
import com.infosys.irs.repository.UserRepository;

@Service
public class RegistrationService {
	final Logger logger= LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private UserRepository userRepository;
	
	public void registerUser(User user) throws UserIdAlreadyPresentException{
		Long time1;
		Long time2;
        time1 = System.currentTimeMillis();
		UserEntity ue = findUser(user.getUserId());
		time2 = System.currentTimeMillis();

		String data =Long.toString(time2-time1);
        logger.info("Amount of time taken:");
        logger.info(data);
        logger.info("miliseconds.");
		if(ue!=null)
			throw new UserIdAlreadyPresentException("RegistrationService.USERID_PRESENT");
		UserEntity userEntity = new UserEntity();
		userEntity.setCity(user.getCity());
		userEntity.setEmail(user.getEmail());
		userEntity.setName(user.getName());
		userEntity.setPasswrd(user.getPassword());
		userEntity.setPhone(user.getPhone());
		userEntity.setUserId(user.getUserId());
		userRepository.saveAndFlush(userEntity);		
	}
	
	@Cacheable("user")
    public UserEntity findUser(String userId) 
    {

        return userRepository.getOne(userId);
    }
	
}
