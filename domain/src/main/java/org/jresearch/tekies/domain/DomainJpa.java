package org.jresearch.tekies.domain;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.persistence.MappedSuperclass;

import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;

import one.util.streamex.EntryStream;
import one.util.streamex.StreamEx;

@MappedSuperclass
public abstract class DomainJpa {

	public abstract Long getId();

	public abstract void setId(Long id);

	@SuppressWarnings({ "nls", "null" })
	@Override
	public String toString() {
		return MoreObjects.toStringHelper(this)
				.omitNullValues()
				.add("id", getId())
				.toString();
	}

	@SuppressWarnings("boxing")
	@Override
	public int hashCode() {
		return Objects.hashCode(super.hashCode(), getId());
	}

	@SuppressWarnings("null")
	@Override
	public boolean equals(final Object object) {
		if (object instanceof DomainJpa) {
			if (!super.equals(object)) {
				return false;
			}
			final DomainJpa that = (DomainJpa) object;
			return Objects.equal(this.getId(), that.getId());
		}
		return false;
	}

	public static Long id(final DomainJpa object) {
		return Optional.ofNullable(object).map(DomainJpa::getId).orElse(null);
	}

	public static List<Long> id(final Collection<? extends DomainJpa> objects) {
		return objects == null ? ImmutableList.of() : StreamEx.of(objects).map(DomainJpa::getId).toList();
	}

	public static Map<?, Long> id(final Map<?, ? extends DomainJpa> objects) {
		return objects == null ? ImmutableMap.of() : EntryStream.of(objects).mapValues(DomainJpa::getId).toMap();
	}

}
