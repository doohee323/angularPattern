package com.tz.common.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.apache.commons.lang.builder.ToStringBuilder;

/**
 * @author TZ
 * 
 */

@Entity
@Table(name = "UIP_CENTER")
public class Center {

	@SequenceGenerator(name = "CenterGen", sequenceName = "UIP_CENTER_S")
	@Id
	@GeneratedValue(generator = "CenterGen")
	private int id;

	@Column
	private String code;
	@Column
	private String name;
	@Column
	private String chief;
	@Column
	private String address;
	@Column
	private String phone;

	public Center() {
	}

	public Center(int id, String code, String name, String chief,
			String address, String phone) {
		super();
		this.id = id;
		this.code = code;
		this.name = name;
		this.chief = chief;
		this.address = address;
		this.phone = phone;
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getChief() {
		return chief;
	}

	public void setChief(String chief) {
		this.chief = chief;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}
}
