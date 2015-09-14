/**
 * 
 */
package com.bigbazar.persistent;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

import com.vividsolutions.jts.geom.Geometry;



/**
 * @author NEX6UYU
 *
 */
@Entity
@Table(name= "USER_MAPPED_LOCATION")

public class UserMappedLocation implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 6832984575748042483L;
	private Long mappedUserId; // this is MUI for any mobile number.	
	private Geometry geometry;
	private Long mobileNumber;
	private Set<UserDetails> userId=new HashSet<UserDetails>();
	private String emailId;
	private String userIPAddress;
	
	@Id
/*	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator="USER_MAPPED_LOCATION_SEQ")
    @SequenceGenerator(name="USER_MAPPED_LOCATION_SEQ", sequenceName="USER_MAPPED_LOCATION_SEQ", allocationSize=1)*/
	 @GeneratedValue(generator="increment")
    @GenericGenerator(name="increment", strategy = "increment")
	@Column(name="USER_MAPPED_ID")
	public Long getMappedUserId() {
		return mappedUserId;
	}	
	
	
	@Column(name="MOBILE_NUMBER")
	public Long getMobileNumber() {
		return mobileNumber;
	}
	
	
	
	@Column(name="EMAIL_ID")
	public String getEmailId() {
		return emailId;
	}
	@ManyToMany(mappedBy="userLocations")
	public Set<UserDetails> getUserId() {
		return userId;
	}
	
	@Type(type="org.hibernate.spatial.GeometryType")
	public Geometry getGeometry() {
		return geometry;
	}

	@Column(name="IP_ADDRESS")
	public String getUserIPAddress() {
		return userIPAddress;
	}

	
	public void setUserIPAddress(String userIPAddress) {
		this.userIPAddress = userIPAddress;
	}


	public void setGeometry(Geometry geometry) {
		this.geometry = geometry;
	}


	public void setMobileNumber(Long mobileNumber) {
		this.mobileNumber = mobileNumber;
	}


	public void setUserId(Set<UserDetails> userId) {
		this.userId = userId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	public void setMappedUserId(Long mappedUserId) {
		this.mappedUserId = mappedUserId;
	}
	
	
	
}
