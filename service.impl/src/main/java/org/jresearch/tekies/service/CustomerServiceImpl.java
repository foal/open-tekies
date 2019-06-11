package org.jresearch.tekies.service;

import java.time.LocalDate;
import java.util.Optional;

import javax.transaction.Transactional;

import org.jresearch.tekies.domain.Customer;
import org.jresearch.tekies.resources.api.CustomerDao;
import org.jresearch.tekies.service.api.CustomerService;
import org.jresearch.tekies.service.api.DuplicateEmail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

@Service
public class CustomerServiceImpl implements CustomerService {

	@Autowired
	private CustomerDao customerDao;

	@Override
	@Transactional
	public Customer addCustomer(String firstName, String lastName, String phone, String email, LocalDate birthday) throws DuplicateEmail {
		try {
			Customer customer = new Customer();
			customer.setBirthday(birthday);
			customer.setEmail(email);
			customer.setFirstName(firstName);
			customer.setLastName(lastName);
			customer.setPhone(phone);
			return customerDao.saveAndFlush(customer);
		} catch (DataIntegrityViolationException e) {
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			throw new DuplicateEmail(e);
		}
	}

	@Override
	public Optional<Customer> getCustomer(long customerId) {
		return customerDao.findById(Long.valueOf(customerId));
	}

	@Override
	@Transactional
	public Customer updateCustomer(Customer update) throws DuplicateEmail {
		try {
			return customerDao.saveAndFlush(update);
		} catch (DataIntegrityViolationException e) {
			throw new DuplicateEmail(e);
		}
	}

	@Override
	@Transactional
	public void removeCustomer(long customerId) {
		customerDao.deleteById(Long.valueOf(customerId));
	}

	@Override
	public Page<Customer> getCustomers(Pageable pageParameters) {
		return customerDao.findAll(pageParameters);
	}

}
