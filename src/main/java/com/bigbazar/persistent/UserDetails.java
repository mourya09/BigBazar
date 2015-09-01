/**
 * 
 */
package com.bigbazar.persistent;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 * @author NEX6UYU
 *
 */

@Entity
@Table(name= "USER_DETAIL")
/*@javax.persistence.TableGenerator(
        name="SEQ_GEN_TAB",
        table="SEQUENCE",
        pkColumnName = "SEQ_NAME",
        valueColumnName = "SEQ_COUNT",
        pkColumnValue="USER_DETAIL_SEQ",
        allocationSize=1
)*/
public class UserDetails  implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -4031152287103560967L;
	private Long id;
	private String firstName;
	private String middleName;
	private String lastName;
	private String displayName;
	private String role;
	private Long mobileNumber;
	private String emailId;
	private Long bigBazarCardNumber;
	
	private Set<UserAddresses> addresses = new HashSet<UserAddresses>();
	
	private Set<UserMappedLocation> userLocations = new HashSet<UserMappedLocation>();
	
	@Id
	/*@GeneratedValue(strategy=GenerationType.IDENTITY, generator="SEQ_GEN_TAB")*/
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator="USER_DETAIL_SEQ")
    @SequenceGenerator(name="USER_DETAIL_SEQ", sequenceName="USER_DETAIL_SEQ", allocationSize=1)
	@Column(name="ID")
	public Long getId() {
		return id;
	}
	
	@Column(name="FIRST_NAME")
	public String getFirstName() {
		return firstName;
	}
	
	@Column(name="MIDDLE_NAME")
	public String getMiddleName() {
		return middleName;
	}
	@Column(name="LAST_NAME")
	public String getLastName() {
		return lastName;
	}
	
	@Column(name="DISPLAY_NAME")
	public String getDisplayName() {
		return displayName;
	}
	@Column(name="ROLE")
	public String getRole() {
		return role;
	}
	
	@Column(name="MOBILE_NUMBER")
	public Long getMobileNumber() {
		return mobileNumber;
	}
	@Column(name="BB_CARD_NUMBER")
	public Long getBigBazarCardNumber() {
		return bigBazarCardNumber;
	}
	@Column(name="EMAIL_ID")
	public String getEmailId() {
		return emailId;
	}
	@OneToMany(fetch=FetchType.LAZY, mappedBy="userId")
	public Set<UserAddresses> getAddresses() {
		return addresses;
	}
	
	@ManyToMany(cascade=CascadeType.ALL)
	@JoinTable(name="USER_LOCATION_MAPPING", 
    joinColumns={@JoinColumn(name="USER_ID")}, 
    inverseJoinColumns={@JoinColumn(name="USER_MAPPED_ID")})
	public Set<UserMappedLocation> getUserLocations() {
		return userLocations;
	}

	public void setUserLocations(Set<UserMappedLocation> userLocations) {
		this.userLocations = userLocations;
	}

	public void setId(Long id) {
		this.id = id;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}
	public void setRole(String role) {
		this.role = role;
	}
	public void setMobileNumber(Long mobileNumber) {
		this.mobileNumber = mobileNumber;
	}
	public void setBigBazarCardNumber(Long bigBazarCardNumber) {
		this.bigBazarCardNumber = bigBazarCardNumber;
	}

	

	public void setAddresses(Set<UserAddresses> addresses) {
		this.addresses = addresses;
	}

	

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	

	
	
	
	
}
