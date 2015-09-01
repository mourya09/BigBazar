/**
 * 
 */
package com.bigbazar.services;

import java.util.List;

import com.bigbazar.persistent.UserAddresses;
import com.bigbazar.persistent.UserDetails;

/**
 * @author NEX6UYU
 *
 */
public interface UserDetailsService {
	
	public Boolean addUsersDetails(UserDetails userDetails);
	public Boolean deleteUsersDetails(UserDetails userDetails);
	public UserDetails updateUserDetails(UserDetails userDetails);
	public Boolean addUserAddress(UserDetails userDetails, UserAddresses newAddresses);
	public Boolean deleteAllUserAddress(UserDetails userDetails);
	public Boolean deleteUserAddress(UserDetails userDetails, UserAddresses oldAddresses);
	public UserAddresses updateUserAddress(UserDetails userDetails, UserAddresses newAddresses, UserAddresses oldAddresses);
	
	public UserDetails getUserDetails(Long userId);
	public UserDetails getUserDetailsWithAddresses(Long userId);	
	public List<UserDetails> getUserDetails(String userHQL, Object[] objectArray);

}
