package jpabook.yenyang.domain;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Entity
@DiscriminatorValue("B")
public class Book extends Item{
	private String author;
	private String isbn;

	public Book() {
		super();
	}
}
