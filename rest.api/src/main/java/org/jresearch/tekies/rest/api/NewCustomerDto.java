package org.jresearch.tekies.rest.api;

import java.time.LocalDate;
import java.util.Optional;

import org.immutables.value.Value;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

@Value.Immutable
@JsonSerialize(as = ImmutableNewCustomerDto.class)
@JsonDeserialize(as = ImmutableNewCustomerDto.class)
public interface NewCustomerDto {

	public Optional<String> firstName();

	public Optional<String> lastName();

	public Optional<String> phone();

	public String email();

	public Optional<String> notes();

	public Optional<LocalDate> birthday();

}
