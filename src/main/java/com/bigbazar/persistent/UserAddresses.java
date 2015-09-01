/**
 * 
 */
package com.bigbazar.persistent;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

/**
 * @author NEX6UYU
 *
 */
@Entity
@Table(name= "USER_ADDRESS")
/*@javax.persistence.TableGenerator(
        name="SEQ_GEN_TAB",
        table="SEQUENCE",
        pkColumnName = "SEQ_NAME",
        valueColumnName = "SEQ_COUNT",
        pkColumnValue="USER_ADDR_SEQ",
        allocationSize=1
)*/
public class UserAddresses implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5143147459086915817L;
	private Long ID;
	private UserDetails userId;
	private String flatNo;
	private String addr1;
	private String addr2;
	private String district;
	private String state;
	private Integer pinCode;
	private Long mappedUniqueId;
	
	
	@Id
	/*@GeneratedValue(strategy = GenerationType.SEQUENCE, generator="USER_ADDR_SEQ")
    @SequenceGenerator(name="USER_ADDR_SEQ", sequenceName="USER_ADDR_SEQ", allocationSize=1)*/
	 @GeneratedValue(generator="increment")
    @GenericGenerator(name="increment", strategy = "increment")
	@Column(name="ID")
	public Long getID() {
		return ID;
	}
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="USER_ID", nullable= false)
	public UserDetails getUserId() {
		return userId;
	}
	
	@Column(name="FLAT_NO")
	public String getFlatNo() {
		return flatNo;
	}
	@Column(name="ADDRESS_1")
	public String getAddr1() {
		return addr1;
	}
	@Column(name="ADDRESS_2")
	public String getAddr2() {
		return addr2;
	}
	@Column(name="DISTRICT")
	public String getDistrict() {
		return district;
	}
	@Column(name="STATE")
	public String getState() {
		return state;
	}
	@Column(name="PIN_CODE")
	public Integer getPinCode() {
		return pinCode;
	}
	public void setID(Long iD) {
		ID = iD;
	}
	public void setUserId(UserDetails userId) {
		this.userId = userId;
	}
	public void setFlatNo(String flatNo) {
		this.flatNo = flatNo;
	}
	public void setAddr1(String addr1) {
		this.addr1 = addr1;
	}
	public void setAddr2(String addr2) {
		this.addr2 = addr2;
	}
	public void setDistrict(String district) {
		this.district = district;
	}
	public void setState(String state) {
		this.state = state;
	}
	public void setPinCode(Integer pinCode) {
		this.pinCode = pinCode;
	}

	public Long getMappedUniqueId() {
		return mappedUniqueId;
	}

	public void setMappedUniqueId(Long mappedUniqueId) {
		this.mappedUniqueId = mappedUniqueId;
	}
}
