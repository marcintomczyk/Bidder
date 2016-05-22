package com.tomczyk.bidder.model;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Bid {

	@Id
	private Long id;

	private BigDecimal cost;
	
	@OneToMany(mappedBy = "bid", cascade=CascadeType.PERSIST, fetch=FetchType.EAGER)
	private List<Site> sites;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public BigDecimal getCost() {
		return cost;
	}

	public void setCost(BigDecimal cost) {
		this.cost = cost;
	}

	public List<Site> getSites() {
		return sites;
	}

	public void setSites(List<Site> sites) {
		this.sites = sites;
	}

	//very simple implementation of the hashcode method
	@Override
	public int hashCode() {
		return this.id.intValue();
	}

	//very simple implementation of the equals method
	@Override
	public boolean equals(Object obj) {
		 if (obj == null || getClass() != obj.getClass())
	            return false;
	 
	        if (this.id != ((Bid) obj).id)
	            return false;
	        
	        return true;
	}
	
	
	
}
