package com.example.springbootjpa.account;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import javax.sql.DataSource;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@DataJpaTest
//@SpringBootTest(properties = "spring.datasource.url=''")
public class AccountRepositoryTest {

    @Autowired
    DataSource dataSource;

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Autowired
    AccountRepository accountRepository;

    @Test
    public void di() {
//        try(Connection connection = dataSource.getConnection()) {
//            DatabaseMetaData metaData = connection.getMetaData();
//            System.out.println(metaData.getURL());
//            System.out.println(metaData.getDriverName());
//            System.out.println(metaData.getUserName());
//        } catch (SQLException throwables) {
//            throwables.printStackTrace();
//        }

        Account accout = new Account();
        accout.setUsername("jinwoo");
        accout.setPassword("pass");

        Account newAccount = accountRepository.save(accout);

        assertThat(newAccount).isNotNull();

        Optional<Account> existingAccount = accountRepository.findByUsername(newAccount.getUsername());
        assertThat(existingAccount).isNotEmpty();

        Optional<Account> nonExistingAccount = accountRepository.findByUsername("swat018");
        assertThat(nonExistingAccount).isEmpty();
    }
}