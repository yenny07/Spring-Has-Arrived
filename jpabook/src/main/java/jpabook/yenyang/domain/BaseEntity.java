package jpabook.yenyang.domain;

import java.util.Date;

import javax.persistence.MappedSuperclass;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@MappedSuperclass
public class BaseEntity {
	private Date createdDate;
	private Date lastModifiedDate;
}
