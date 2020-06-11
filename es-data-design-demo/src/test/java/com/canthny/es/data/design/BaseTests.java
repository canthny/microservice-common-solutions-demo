package com.canthny.es.data.design;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest()
@Import(ESDataDesignApplication.class)
public class BaseTests {
	@Test
	public void contextLoads() {
	}

}
