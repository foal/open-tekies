package  org.jresearch.tekies.domain;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

import com.google.common.base.MoreObjects;

@Entity
public class Customer extends DomainJpa {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "customer_generator")
	@SequenceGenerator(name = "customer_generator", sequenceName = "customer_seq")
	private Long id;
	private String firstName;
	private String lastName;
	private String phone;
	@Column(unique = true)
	private String email;
	private String notes;
	@Column(columnDefinition = "DATE")
	private LocalDate birthday;

	@Override
	public Long getId() {
		return id;
	}

	@Override
	public void setId(final Long id) {
		this.id = id;
	}

	public LocalDate getBirthday() {
		return birthday;
	}

	public void setBirthday(final LocalDate birthday) {
		this.birthday = birthday;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(final String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(final String lastName) {
		this.lastName = lastName;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(final String phone) {
		this.phone = phone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(final String email) {
		this.email = email;
	}

	public String getNotes() {
		return notes;
	}

	public void setNotes(final String notes) {
		this.notes = notes;
	}

	@SuppressWarnings({ "nls" })
	@Override
	public String toString() {
		return MoreObjects
				.toStringHelper(this)
				.omitNullValues()
				.add("super", super.toString())
				.add("id", id)
				.add("firstName", firstName)
				.add("lastName", lastName)
				.add("phone", phone)
				.add("email", email)
				.add("notes", notes)
				.add("birthday", birthday)
				.toString();
	}

}
