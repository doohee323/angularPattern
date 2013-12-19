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
@Table(name = "UIP_REGION")
public class Region {

	@SequenceGenerator(name = "RegionGen", sequenceName = "UIP_REGION_S")
	@Id
	@GeneratedValue(generator = "RegionGen")
	private int id;

	@Column
	private int uip_center_id;
	@Column
	private String code;
	@Column
	private String region_code;
	@Column
	private String name;
	@Column
	private String chief;
	@Column
	private String address;

	public Region() {
	}

	public Region(int id, int uip_center_id, String code, String region_code,
			String name, String chief, String address) {
		super();
		this.id = id;
		this.uip_center_id = uip_center_id;
		this.code = code;
		this.region_code = region_code;
		this.name = name;
		this.chief = chief;
		this.address = address;
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

	public int getUip_center_id() {
		return uip_center_id;
	}

	public void setUip_center_id(int uip_center_id) {
		this.uip_center_id = uip_center_id;
	}

	public String getRegion_code() {
		return region_code;
	}

	public void setRegion_code(String region_code) {
		this.region_code = region_code;
	}
}
