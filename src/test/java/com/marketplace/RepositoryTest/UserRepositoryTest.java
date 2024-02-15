package com.marketplace.RepositoryTest;

import com.marketplace.TestDataUtil;
import com.marketplace.domain.entity.UserEntity;
import com.marketplace.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.DirtiesContext;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_CLASS)
public class UserRepositoryTest {

    private final UserRepository underTest;

    @Autowired
    public UserRepositoryTest(UserRepository underTest){
        this.underTest = underTest;
    }

    @Test
    public void testThatItemCanBeCreatedAndRecalled(){
        UserEntity userEntity = TestDataUtil.createUserA();
        underTest.save(userEntity);

        Optional<UserEntity> user = underTest.findById(userEntity.getUserUuid());
        assertThat(user).isPresent();
        assertThat(user).get().isEqualTo(userEntity);
    }

    @Test
    public void testThatMultiplyItemsCanBeFound(){
        UserEntity userEntityA = TestDataUtil.createUserA();
        underTest.save(userEntityA);
        UserEntity userEntityB = TestDataUtil.createUserB();
        underTest.save(userEntityB);
        UserEntity userEntityC = TestDataUtil.createUserC();
        underTest.save(userEntityC);

        Iterable<UserEntity> userEntityList = underTest.findAll();

        assertThat(userEntityList)
                .hasSize(3)
                .containsExactly(userEntityA, userEntityB, userEntityC);
    }

    @Test
    public void testThatItemCanBeDeleted(){
        UserEntity userEntity = TestDataUtil.createUserA();
        underTest.save(userEntity);
        underTest.deleteById(userEntity.getUserUuid());

        Optional<UserEntity> user = underTest.findById(userEntity.getUserUuid());

        assertThat(user).isEmpty();
    }

    @Test
    public void testThatItemCanBeUpdated(){
        UserEntity userEntity = TestDataUtil.createUserA();
        underTest.save(userEntity);
        userEntity.setLogin("UPDATED");
        underTest.save(userEntity);

        Optional<UserEntity> user = underTest.findById(userEntity.getUserUuid());
        assertThat(user).get().isEqualTo(userEntity);
    }
}
