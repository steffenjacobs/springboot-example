package de.oio.testbackend.domain;

import javax.persistence.Entity;
import javax.persistence.Id;

/***
 * @author Steffen Jacobs
 */
@Entity
public class Entry {

	@Id
	private String data;

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	public Entry() {

	}

	public Entry(String data) {
		this.data = data;
	}
}