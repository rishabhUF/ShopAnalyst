package com.rishabh.ShopAnalyst.service;

import com.rishabh.ShopAnalyst.service.impl.DatabaseServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
public class DatabaseServiceTest {

    @Mock
    private DatabaseService databaseService = new DatabaseServiceImpl();

    @Test
    public void testGetDatabaseNames() {


    }

}
