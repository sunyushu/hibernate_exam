package com.hand.HibernateExam;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;

public class CustomerJDBCTemplate implements CustomerDAO {
	private JdbcTemplate jdbcTemplateObject;
	private DataSource dataSource;

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
		this.jdbcTemplateObject = new JdbcTemplate(dataSource);
	}

	public void create(Integer store_id, String first_name, String last_name, String email, Integer address_id,
			Date create_date) {
		String SQL = "insert into Customer(store_id,first_name,last_name,email,address_id,create_date)"
				+ "values(?,?,?,?,?,?)";
		jdbcTemplateObject.update(SQL, store_id, first_name, last_name, email, address_id, create_date);
		return;

	}

	public List<Customer> listCustomers() {
		String SQL = "select customer_id,first_name,last_name,email,address,customer.create_date from Customer,Address where Customer.address_id=Address.address_id order by customer.create_date desc limit 1";
		List<Customer> customers = jdbcTemplateObject.query(SQL, new CustomerMapper());
		return customers;
	}

	public void delete(Integer customer_id) {
		String SQL = "delete from Customer where custommer_id=?";
		jdbcTemplateObject.update(SQL, customer_id);
		return;

	}

	public Boolean check(Integer customer_id) {
		String SQL = "select customer_id from customer";
		List<Customer> customers = jdbcTemplateObject.query(SQL, new CustomerMapper());
		if(customers!=null)
		{
			return true;
		}
		return false;
	}

	

}
