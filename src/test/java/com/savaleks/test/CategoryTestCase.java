package com.savaleks.test;

import com.savaleks.model.Category;
import com.savaleks.repository.CategoryRepository;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@DataJpaTest
public class CategoryTestCase {

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private TestEntityManager entityManager;

    @Test
    public void testAddCategory(){
        Category category = new Category();
        category.setName("Memory Card");
        category.setDescription("Some description for memory card");
        category.setImageURL("pic4.jpg");


    }
}
