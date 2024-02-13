package com.marketplace.RepositoryTest;

import com.marketplace.repository.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
public class ItemRepositoryTest {

    private final ItemRepository underTest;

    @Autowired
    public ItemRepositoryTest(ItemRepository underTest){
        this.underTest = underTest;
    }
}
