/**
 * 
 */
package com.bigbazar.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import com.bigbazar.persistent.UserAddresses;
import com.bigbazar.persistent.UserDetails;
import com.bigbazar.services.UserDetailsService;

/**
 * @author NEX6UYU
 *
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:BigBazar.xml" })
@TransactionConfiguration(transactionManager = "telenorTransactionManager", defaultRollback = false)
@Transactional
public class UserDetailsServiceTest {

	protected final Logger logger = LoggerFactory.getLogger(UserDetailsServiceTest.class);
	@Autowired
	private UserDetailsService userDetailsService;
	
	@Test
	public void testService1()
	{
		logger.debug("UserDetailsServiceTest Class");
		UserDetails u=new UserDetails();
		u.setFirstName("Praveen1");
		u.setLastName("Kumar1");
		u.setRole("Developer");
		u.setDisplayName("WebSpark1");
		u.setMobileNumber(9717792363L);
		u.setBigBazarCardNumber(99101293L);
		UserAddresses ua = new UserAddresses();
		ua.setFlatNo("A-401");
		ua.setPinCode(201016);
		ua.setDistrict("GHAZIABAD");
		ua.setState("Uttar Pradesh");
		ua.setAddr1("Gaur Global Village");
		ua.setAddr2("Crossing Republic");
		ua.setUserId(u);
		u.getAddresses().add(ua);
		userDetailsService.addUsersDetails(u);
		
	}
	@Test
	public void testTest()
	{
		logger.info("Timepass");
	}
}
