package com.infosys.irs.repository;

import static org.assertj.core.api.Assertions.assertThat;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;
import com.infosys.irs.entity.UserEntity;
import com.infosys.irs.model.User;
@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace=Replace.NONE)
public class UserRepositoryTest 
{
    @Mock
    private UserRepository userRepository;
    private User user1; 
    private User user2;
    private String dummy1="dummy1";
    private String dummy2="dummy2";
    private String userId="userId";
    
    public void user1()
    {
        user1= new User();
        user1.setUserId(dummy1);
        user1.setPassword(dummy1);
        user1.setName(dummy1);
        user1.setCity(dummy1);
        user1.setEmail(dummy1);
        user1.setPhone(dummy1);
    }
    
    public void user2()
    {
        user2 = new User();
        user2.setUserId(dummy2);
        user2.setPassword(dummy2);
        user2.setCity(dummy2);
        user2.setCity(dummy2);
        user2.setEmail(dummy2);
        user2.setPhone(dummy2);
    }
    
    @Before
    public void setUp()
    {
        user1(); user2();
    }
    
    @Test
    public void saveAndFlushTest()
    {    
        UserEntity userEntity1 = new UserEntity();
        userEntity1.setCity(user1.getCity());
        userEntity1.setEmail(user1.getEmail());
        userEntity1.setName(user1.getName());
        userEntity1.setPassword(user1.getPassword());
        userEntity1.setPhone(user1.getPhone());
        userEntity1.setUserId(user1.getUserId());
        
        UserEntity userEntity2 = new UserEntity();
        userEntity2.setCity(user2.getCity());
        userEntity2.setEmail(user2.getEmail());
        userEntity2.setName(user2.getName());
        userEntity2.setPassword(user2.getPassword());
        userEntity2.setPhone(user2.getPhone());
        userEntity2.setUserId(user2.getUserId());       
        
        Mockito.when(userRepository.saveAndFlush(userEntity1)).thenReturn(userEntity1);
        UserEntity user = userRepository.saveAndFlush(userEntity1);
        assertThat(user).hasFieldOrPropertyWithValue(userId, dummy1);
        
        Mockito.when(userRepository.saveAndFlush(userEntity2)).thenReturn(new UserEntity());
        user = userRepository.saveAndFlush(userEntity2);
        assertThat(user).hasFieldOrPropertyWithValue(userId, null);
    }
    
    @Test
    public void findOneTest()
    {
        UserEntity userEntity2 = new UserEntity();
        userEntity2.setCity(user2.getCity());
        userEntity2.setEmail(user2.getEmail());
        userEntity2.setName(user2.getName());
        userEntity2.setPassword(user2.getPassword());
        userEntity2.setPhone(user2.getPhone());
        userEntity2.setUserId(user2.getUserId());   
        Mockito.when(userRepository.getOne(user2.getUserId())).thenReturn(userEntity2);
        UserEntity user = userRepository.getOne(user2.getUserId());
        assertThat(user).hasFieldOrPropertyWithValue(userId, "dummy2");
    }
}