/**
 * 
 */
package com.bigbazar.services.impl;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import com.bigbazar.dao.BigBazarDao;
import com.bigbazar.persistent.UserAddresses;
import com.bigbazar.persistent.UserDetails;
import com.bigbazar.services.UserDetailsService;

/**
 * @author NEX6UYU
 *
 */
@Service("userDetailsService")
public class UserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	private BigBazarDao bigBazarDao;
	
	/*@Autowired
	private PropertyConfigurer propertyConfigurer;*/
	
	private Logger logger = Logger.getLogger(UserDetailsServiceImpl.class);
	/* (non-Javadoc)
	 * @see com.bigbazar.services.UserDetailsService#addUsersDetails(com.bigbazar.persistent.UserDetails)
	 */
	@Transactional(isolation=Isolation.READ_COMMITTED)
	public Boolean addUsersDetails(UserDetails userDetails) {
		logger.info("addUsersDetails start ");
		Boolean result = false;
		Set<UserAddresses> set = userDetails.getAddresses();
		userDetails.setAddresses(null);
		bigBazarDao.save(userDetails);
		try{
			userDetails.setAddresses(new HashSet<UserAddresses>());
			if(set.size() > 0)
			{
				for(UserAddresses ua:set){
					ua.setUserId(userDetails);
					bigBazarDao.save(ua);
					userDetails.getAddresses().add(ua);
				}
				
			}
		result = true;
		}catch( Exception ex)
		{
			logger.error(ex);
			ex.printStackTrace();
		}
		logger.info("addUsersDetails end ");
		return result;
	}

	/* (non-Javadoc)
	 * @see com.bigbazar.services.UserDetailsService#deleteUsersDetails(com.bigbazar.persistent.UserDetails)
	 */
	@Transactional(isolation=Isolation.READ_COMMITTED)
	public Boolean deleteUsersDetails(UserDetails userDetails) {
		logger.info("deleteUsersDetails start ");
		Boolean result = false;
		try{
		
		UserDetails uDetails = this.getUserDetails(userDetails.getId());
		result = this.deleteAllUserAddress(uDetails);
		uDetails.setAddresses(null);
		bigBazarDao.delete(uDetails);
		result = true;
		}catch( Exception ex)
		{
			logger.error(ex);
			ex.printStackTrace();
		}
		logger.info("deleteUsersDetails end ");
		return result;
	}

	/* (non-Javadoc)
	 * @see com.bigbazar.services.UserDetailsService#updateUserDetails(com.bigbazar.persistent.UserDetails)
	 */
	@Transactional(isolation=Isolation.READ_COMMITTED)
	public UserDetails updateUserDetails(UserDetails userDetails) {
		logger.info("updateUserDetails start ");
		UserDetails result = null;
		try{
		
		UserDetails uDetails = this.getUserDetails(userDetails.getId());		
		uDetails.setBigBazarCardNumber(userDetails.getBigBazarCardNumber());
		uDetails.setDisplayName(userDetails.getDisplayName());
		uDetails.setFirstName(userDetails.getFirstName());
		uDetails.setLastName(userDetails.getLastName());
		uDetails.setMiddleName(userDetails.getMiddleName());
		uDetails.setMobileNumber(userDetails.getMobileNumber());
		uDetails.setRole(userDetails.getRole());
		bigBazarDao.update(uDetails);
		result = uDetails;
		}catch( Exception ex)
		{
			logger.error(ex);
			ex.printStackTrace();
		}
		logger.info("updateUserDetails end ");
		return result;
	}

	/* (non-Javadoc)
	 * @see com.bigbazar.services.UserDetailsService#addUserAddress(com.bigbazar.persistent.UserDetails, com.bigbazar.persistent.UserAddresses)
	 */
	@Transactional(isolation=Isolation.READ_COMMITTED)
	public Boolean addUserAddress(UserDetails userDetails, UserAddresses newAddresses) {
		logger.info("updateUserDetails start ");
		Boolean result = false;
		try{
		if(userDetails.getId() == null){
			newAddresses.setUserId(userDetails);
			userDetails.getAddresses().add(newAddresses);
			bigBazarDao.save(newAddresses);
			bigBazarDao.save(userDetails);
			result = true;
		}else
		{
			UserDetails uDetails = this.getUserDetails(userDetails.getId());
			newAddresses.setUserId(uDetails);
			bigBazarDao.save(newAddresses);
			result = true;
		}				
		
		}catch( Exception ex)
		{
			logger.error(ex);
			ex.printStackTrace();
		}
		logger.info("updateUserDetails end ");
		return result;
	}

	/* (non-Javadoc)
	 * @see com.bigbazar.services.UserDetailsService#deleteAllUserAddress(com.bigbazar.persistent.UserDetails)
	 */
	@Transactional(isolation=Isolation.READ_COMMITTED)
	public Boolean deleteAllUserAddress(UserDetails userDetails) {
		logger.info("deleteAllUserAddress start ");
		Boolean result = false;
		try{
		if(userDetails.getId() != null){
			Set<UserAddresses> set;
			UserDetails uDetails = this.getUserDetails(userDetails.getId());
			set = uDetails.getAddresses();
			uDetails.setAddresses(new HashSet<UserAddresses>());
			for(UserAddresses ud: set)
			{
				ud.setUserId(null);
				bigBazarDao.save(ud);
			}
			bigBazarDao.save(uDetails);
			result = true;
		}else
		{
			logger.error("User Information doesn't exits in system!");
		}
		
		}catch( Exception ex)
		{
			logger.error(ex);
			ex.printStackTrace();
		}
		logger.info("deleteAllUserAddress end ");
		return result;
	}

	/* (non-Javadoc)
	 * @see com.bigbazar.services.UserDetailsService#deleteUserAddress(com.bigbazar.persistent.UserDetails, com.bigbazar.persistent.UserAddresses)
	 */
	@Transactional(isolation=Isolation.READ_COMMITTED)
	public Boolean deleteUserAddress(UserDetails userDetails, UserAddresses oldAddresses) {
		logger.info("deleteAllUserAddress start ");
		Boolean result = false;
		try{
		if(userDetails.getId() != null){
			Set<UserAddresses> set;
			UserDetails uDetails = this.getUserDetails(userDetails.getId());
			set = uDetails.getAddresses();
			
			for(UserAddresses ud: set)
			{
				if(ud.getID() == oldAddresses.getID()){
				ud.setUserId(null);
				bigBazarDao.delete(ud);
				set.remove(ud);
				break;
				}
			}
			uDetails.setAddresses(set);
			bigBazarDao.save(uDetails);
			result = true;
		}else
		{
			logger.error("User Information doesn't exits in system!");
		}
		
		}catch( Exception ex)
		{
			logger.error(ex);
			ex.printStackTrace();
		}
		logger.info("deleteAllUserAddress end ");
		return result;
	}

	/* (non-Javadoc)
	 * @see com.bigbazar.services.UserDetailsService#updateUserAddress(com.bigbazar.persistent.UserDetails, com.bigbazar.persistent.UserAddresses, com.bigbazar.persistent.UserAddresses)
	 */
	@Transactional(isolation=Isolation.READ_COMMITTED)
	public UserAddresses updateUserAddress(UserDetails userDetails, UserAddresses newAddresses,
			UserAddresses oldAddresses) {
		logger.info("updateUserAddress start ");
		UserAddresses result = null;
		try{
		if(userDetails.getId() != null){
			Set<UserAddresses> set;
			UserDetails uDetails = this.getUserDetails(userDetails.getId());
			set = uDetails.getAddresses();
			
			for(UserAddresses ud: set)
			{
				if(ud.getID() == oldAddresses.getID()){
				ud.setAddr1(newAddresses.getAddr1());
				ud.setAddr2(newAddresses.getAddr2());
				ud.setDistrict(newAddresses.getDistrict());
				ud.setFlatNo(newAddresses.getFlatNo());
				ud.setPinCode(newAddresses.getPinCode());
				ud.setState(newAddresses.getState());					
				bigBazarDao.update(ud);	
				result = ud;
				break;
				}
			}
			
			
		}else
		{
			logger.error("User Information doesn't exits in system!");
		}
		
		}catch( Exception ex)
		{
			logger.error(ex);
			ex.printStackTrace();
		}
		logger.info("updateUserAddress end ");
		return result;
	}
	
	@Transactional(isolation=Isolation.READ_COMMITTED,readOnly=true)
	public UserDetails getUserDetails(Long userId) {
		// TODO Auto-generated method stub USER_DETAILS_QUERY1
		logger.info("getUserDetails start ");
		UserDetails result = null;
		try{
			
			result =(UserDetails) bigBazarDao.get(UserDetails.class, userId);
		}catch( Exception ex)
		{
			logger.error(ex);
			ex.printStackTrace();
		}
		logger.info("getUserDetails end ");
		return result;
	}
	
	@Transactional(isolation=Isolation.READ_COMMITTED,readOnly=true)
	public UserDetails getUserDetailsWithAddresses(Long userId) {
		logger.info("getUserDetailsWithAddresses start ");
		UserDetails result = null;
		try{
			
			result =(UserDetails) bigBazarDao.get(UserDetails.class, userId);
			for(UserAddresses ud:result.getAddresses())
			{
				logger.info(ud.getID());
			}
			logger.info("Obtained Addresses Successfully");
		}catch( Exception ex)
		{
			logger.error(ex);
			ex.printStackTrace();
		}
		logger.info("getUserDetailsWithAddresses end ");
		return result;
	}
	
	@SuppressWarnings("unchecked")
	@Transactional(isolation=Isolation.READ_COMMITTED,readOnly=true)
	public List<UserDetails> getUserDetails(String userHQL, Object[] objectArray)
	{
		logger.info("getUserDetails Start ");
		List<UserDetails> list=null;
		try{
			list = bigBazarDao.find(userHQL, objectArray);
			logger.info("Obtained Addresses Successfully");
		}catch( Exception ex)
		{
			logger.error(ex);
			ex.printStackTrace();
		}
		logger.info("getUserDetails end ");
		return list;
	}
	
}
