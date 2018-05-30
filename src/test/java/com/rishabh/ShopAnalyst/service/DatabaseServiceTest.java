package com.rishabh.ShopAnalyst.service;

import com.rishabh.ShopAnalyst.doa.CustomeMongoDao;
import com.rishabh.ShopAnalyst.doa.DatabaseMangoDoa;
import com.rishabh.ShopAnalyst.service.impl.DatabaseServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(SpringJUnit4ClassRunner.class)
public class DatabaseServiceTest {

    @Mock
    DatabaseMangoDoa mongoDao;

    @InjectMocks
    private DatabaseService databaseService = new DatabaseServiceImpl();

    @Test
    public void testGetDatabaseNames() {
        String expectedNames = "LOCAL(local)";
        when(mongoDao.getDatabaseNames()).thenReturn("LOCAL(local)");
        assertThat(databaseService.getDatabaseNames(),is(expectedNames));

    }

    @Test
    public void testGetDatabaseNamesEmptyString() {
        String expectedNames = "OOPS !! sorry, There are no databases present to show";
        when(mongoDao.getDatabaseNames()).thenReturn("");
        assertThat(databaseService.getDatabaseNames(),is(expectedNames));
        verify(mongoDao,times(1)).getDatabaseNames();
    }

    @Test
    public void testInsertData() {
        String expected = "Inserted 1000 records in mongo";
        when(mongoDao.insertData()).thenReturn("Inserted 1000 records in mongo");
        assertThat(databaseService.insertData(),is(expected));
        verify(mongoDao,times(1)).insertData();
    }

    @Test
    public void testInsertDataEmptyString() {
        String expected = "Data is not inserted property";
        when(mongoDao.insertData()).thenReturn("");
        assertThat(databaseService.insertData(),is(expected));
        verify(mongoDao,times(1)).insertData();
    }

    @Test
    public void testDisplayData() {
        String expected = "There is data in DB";
        when(mongoDao.displayData()).thenReturn("There is data in DB");
        assertThat(databaseService.displayData(),is(expected));
        verify(mongoDao,times(1)).displayData();
    }

    @Test
    public void testDisplayDataEmptyString() {
        String expected = "There is no data store in the DB";
        when(mongoDao.displayData()).thenReturn("");
        assertThat(databaseService.displayData(),is(expected));
        verify(mongoDao,times(1)).displayData();
    }
}
