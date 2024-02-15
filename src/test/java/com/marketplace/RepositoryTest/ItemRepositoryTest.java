package com.marketplace.RepositoryTest;

import com.marketplace.TestDataUtil;
import com.marketplace.domain.entity.ItemEntity;
import com.marketplace.repository.ItemRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.DirtiesContext;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_CLASS)
public class ItemRepositoryTest {

    private final ItemRepository underTest;

    @Autowired
    public ItemRepositoryTest(ItemRepository underTest){
        this.underTest = underTest;
    }

    @Test
    public void testThatItemCanBeCreatedAndRecalled(){
        ItemEntity itemEntity = TestDataUtil.createItemA();
        underTest.save(itemEntity);

        Optional<ItemEntity> item = underTest.findById(itemEntity.getItemUuid());
        assertThat(item).isPresent();
        assertThat(item).get().isEqualTo(itemEntity);
    }

    @Test
    public void testThatMultiplyItemsCanBeFound(){
        ItemEntity itemEntityA = TestDataUtil.createItemA();
        underTest.save(itemEntityA);
        ItemEntity itemEntityB = TestDataUtil.createItemB();
        underTest.save(itemEntityB);
        ItemEntity itemEntityC = TestDataUtil.createItemC();
        underTest.save(itemEntityC);

        Iterable<ItemEntity> itemEntityList = underTest.findAll();

        assertThat(itemEntityList)
                .hasSize(3)
                .containsExactly(itemEntityA, itemEntityB, itemEntityC);
    }

    @Test
    public void testThatItemCanBeDeleted(){
        ItemEntity itemEntity = TestDataUtil.createItemA();
        underTest.save(itemEntity);
        underTest.deleteById(itemEntity.getItemUuid());

        Optional<ItemEntity> item = underTest.findById(itemEntity.getItemUuid());

        assertThat(item).isEmpty();
    }

    @Test
    public void testThatItemCanBeUpdated(){
        ItemEntity itemEntity = TestDataUtil.createItemA();
        underTest.save(itemEntity);
        itemEntity.setDescription("UPDATED");
        underTest.save(itemEntity);

        Optional<ItemEntity> item = underTest.findById(itemEntity.getItemUuid());
        assertThat(item).get().isEqualTo(itemEntity);
    }
}
