package com.marketplace.RepositoryTest;

import com.marketplace.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
public class UserRepositoryTest {

    private final UserRepository underTest;

    @Autowired
    public UserRepositoryTest(UserRepository underTest){
        this.underTest = underTest;
    }
}
