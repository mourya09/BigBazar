/**
 * 
 */
package com.bigbazar.services;

import com.vividsolutions.jts.geom.Point;

/**
 * @author NEX6UYU
 *
 */
public interface MapService {
	/**
	 * Store customers MUI.
	 * This method will store the users address. It will store the point which will be the possible destination for 
	 * users 
	 * */
	public Long storeCustomerMUI(Point point);
	
	public default Boolean storeBranchLocation(Point point)
	{
		return false;
	}
	

}
