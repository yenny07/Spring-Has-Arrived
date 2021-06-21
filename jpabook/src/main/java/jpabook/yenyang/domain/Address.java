package jpabook.yenyang.domain;

import javax.persistence.Embeddable;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Embeddable
public class Address {

	private String city;
	private String street;
	private String zipcode;
}
