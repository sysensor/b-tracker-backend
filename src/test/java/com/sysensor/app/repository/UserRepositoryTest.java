package com.sysensor.app.repository;

import com.sysensor.app.TestConst;
import com.sysensor.app.model.User;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class UserRepositoryTest {

    @Autowired
    private UserRepo userRepo;


    @Test
    @Transactional
    public void userShouldBeAbleToReturnCorrectAttributes() {
        Optional<User> userOptional = userRepo.findById(TestConst.USER_SEVEN_UUID);
        Assert.assertTrue(userOptional.isPresent());
        User user = userOptional.get();
        Assert.assertEquals(user.getUuid(), TestConst.USER_SEVEN_UUID);
        Assert.assertEquals(user.getUsername(), "yohan");
        Assert.assertEquals(user.getName(), "Yohan");
        Assert.assertEquals(user.getAddress(), "Matale");
        Assert.assertEquals(user.getPhone(), "0702737004");
        Assert.assertEquals(user.getType(), "time keeper");
        Assert.assertEquals(user.getStatus(), true);
    }

    public void userShouldBeAbleToUpdateAttributes() {


    }
}
