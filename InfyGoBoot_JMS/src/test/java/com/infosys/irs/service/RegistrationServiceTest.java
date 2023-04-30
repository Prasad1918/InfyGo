package com.infosys.irs.service;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.ContextConfiguration;
import com.infosys.irs.entity.UserEntity;
import com.infosys.irs.exception.UserIdAlreadyPresentException;
import com.infosys.irs.model.User;
import com.infosys.irs.repository.UserRepository;

@ContextConfiguration
public class RegistrationServiceTest 
{
    @Mock
    private UserRepository userRepository;
    @InjectMocks
    private RegistrationService registrationService;
    @Rule
    public ExpectedException e = ExpectedException.none();
    User user = new User();
    UserEntity userEntity = new UserEntity();
    public void inititalizeUserAndUserEntity()
    {
        
        user.setCity("Chennai");
        user.setEmail("anithamalar@infygo.com");
        user.setName("anithamalar");
        user.setPassword("anithamalar_svs");
        user.setPhone("9840567854");
        user.setUserId("C1021");
        userEntity.setCity(user.getCity());
        userEntity.setEmail(user.getEmail());
        userEntity.setName(user.getName());
        userEntity.setPassword(user.getPassword());
        userEntity.setPhone(user.getPhone());
        userEntity.setUserId(user.getUserId());
    }
    @Before
    public void initialWork()
    {
        MockitoAnnotations.initMocks(this);
        inititalizeUserAndUserEntity();
    }
    @Test
    public void testRegisterUserPositive() 
    {
        Mockito.when(userRepository.getOne(user.getName())).thenReturn(null);        
        Mockito.when(userRepository.saveAndFlush(userEntity)).thenReturn(userEntity);
        try
        {
            registrationService.registerUser(user);
        }
        catch(Exception exception){}
    }
    @Test
    public void testRegisterUserNegative() throws UserIdAlreadyPresentException
    {
        e.expect(UserIdAlreadyPresentException.class);
        e.expectMessage("RegistrationService.USERID_PRESENT");
        Mockito.when(userRepository.existsById(Mockito.anyString())).thenReturn(true);
        registrationService.registerUser(user);
    }
}