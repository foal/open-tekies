package org.jresearch.tekies.domain;

import static org.jresearch.tekies.domain.DomainJpa.id;

import java.math.BigDecimal;

import javax.persistence.Embeddable;
import javax.persistence.ManyToOne;

import com.google.common.base.MoreObjects;

@Embeddable
public class ItemReservation {

	@ManyToOne(optional = false)
	private Item item;
	private BigDecimal price = BigDecimal.ZERO;
	private BigDecimal vat = BigDecimal.ZERO;
	private BigDecimal discount = BigDecimal.ZERO;
	private int amount;

	public Item getItem() {
		return item;
	}

	public void setItem(Item item) {
		this.item = item;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public BigDecimal getVat() {
		return vat;
	}

	public void setVat(BigDecimal vat) {
		this.vat = vat;
	}

	public BigDecimal getDiscount() {
		return discount;
	}

	public void setDiscount(BigDecimal discount) {
		this.discount = discount;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	@SuppressWarnings({ "nls"})
	@Override
	public String toString() {
		return MoreObjects.toStringHelper(this)
				.add("super", super.toString())
				.add("item", id(item))
				.add("price", price)
				.add("vat", vat)
				.add("discount", discount)
				.add("amount", amount)
				.toString();
	}

}
