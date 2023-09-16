package com.noktapa.qrgenapp.models;

import com.google.gson.annotations.SerializedName;

public class CurrentIPResponse{

	@SerializedName("zip")
	private String zip;

	@SerializedName("country")
	private String country;

	@SerializedName("city")
	private String city;

	@SerializedName("org")
	private String org;

	@SerializedName("timezone")
	private String timezone;

	@SerializedName("regionName")
	private String regionName;

	@SerializedName("isp")
	private String isp;

	@SerializedName("query")
	private String query;

	@SerializedName("lon")
	private Object lon;

	@SerializedName("as")
	private String as;

	@SerializedName("countryCode")
	private String countryCode;

	@SerializedName("region")
	private String region;

	@SerializedName("lat")
	private Object lat;

	@SerializedName("status")
	private String status;

	public String getZip(){
		return zip;
	}

	public String getCountry(){
		return country;
	}

	public String getCity(){
		return city;
	}

	public String getOrg(){
		return org;
	}

	public String getTimezone(){
		return timezone;
	}

	public String getRegionName(){
		return regionName;
	}

	public String getIsp(){
		return isp;
	}

	public String getQuery(){
		return query;
	}

	public Object getLon(){
		return lon;
	}

	public String getAs(){
		return as;
	}

	public String getCountryCode(){
		return countryCode;
	}

	public String getRegion(){
		return region;
	}

	public Object getLat(){
		return lat;
	}

	public String getStatus(){
		return status;
	}
}