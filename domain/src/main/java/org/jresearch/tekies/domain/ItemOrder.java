package  org.jresearch.tekies.domain;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CollectionTable;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OrderColumn;
import javax.persistence.SequenceGenerator;

import com.google.common.base.MoreObjects;

@Entity
public class ItemOrder extends DomainJpa {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "order_generator")
	@SequenceGenerator(name = "order_generator", sequenceName = "order_seq")
	private Long id;
	@ElementCollection(fetch = FetchType.EAGER)
	@CollectionTable(name = "order_reservations", joinColumns = @JoinColumn(name = "item_order"))
	@OrderColumn(name = "position")
	private List<ItemReservation> itemReservations = new ArrayList<>();
	private String comment;
	private OffsetDateTime createTime;
	@ManyToOne
	private Customer customer;

	@Override
	public Long getId() {
		return id;
	}

	@Override
	public void setId(final Long id) {
		this.id = id;
	}

	public List<ItemReservation> getItemReservations() {
		return itemReservations;
	}

	public void setItemReservations(List<ItemReservation> itemReservations) {
		this.itemReservations = itemReservations;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public OffsetDateTime getCreateTime() {
		return createTime;
	}

	public void setCreateTime(OffsetDateTime createTime) {
		this.createTime = createTime;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	@SuppressWarnings("nls")
	@Override
	public String toString() {
		return MoreObjects.toStringHelper(this)
				.omitNullValues()
				.add("super", super.toString())
				.add("itemReservations", itemReservations)
				.add("comment", comment)
				.add("createTime", createTime)
				.add("customer", id(customer))
				.toString();
	}

}
