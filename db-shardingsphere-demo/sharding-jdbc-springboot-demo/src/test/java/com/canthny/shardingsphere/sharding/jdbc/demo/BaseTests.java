package com.canthny.shardingsphere.sharding.jdbc.demo;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest()
@Import(StartupApplication.class)
@ActiveProfiles("sharding-db-table")
public class BaseTests {
	@Test
	public void contextLoads() {
	}

}
