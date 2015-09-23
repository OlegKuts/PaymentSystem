package ua.epam.repository.template;

import static org.junit.Assert.*;

import javax.transaction.Transactional;

import org.junit.FixMethodOrder;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:testContext.xml" })
@Transactional
@FixMethodOrder(MethodSorters.JVM)
public class RepositoryTestTemplate extends
		AbstractTransactionalJUnit4SpringContextTests {

}
