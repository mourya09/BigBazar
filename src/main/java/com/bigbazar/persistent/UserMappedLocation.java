/**
 * 
 */
package com.bigbazar.persistent;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.Type;

import com.vividsolutions.jts.geom.Geometry;



/**
 * @author NEX6UYU
 *
 */
@Entity
@Table(name= "USER_MAPPED_LOCATION")
/*@javax.persistence.TableGenerator(
        name="SEQ_GEN_TAB",
        table="SEQUENCE",
        pkColumnName = "SEQ_NAME",
        valueColumnName = "SEQ_COUNT",
        pkColumnValue="USER_MAPPED_LOCATION_SEQ",
        allocationSize=1
)*/
public class UserMappedLocation {
	
	private Long mappedUserId; // this is MUI for any mobile number.	
	private Geometry geometry;
	private Long mobileNumber;
	private Set<UserDetails> userId=new HashSet<UserDetails>();
	private String emailId;
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator="USER_MAPPED_LOCATION_SEQ")
    @SequenceGenerator(name="USER_MAPPED_LOCATION_SEQ", sequenceName="USER_MAPPED_LOCATION_SEQ", allocationSize=1)
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
