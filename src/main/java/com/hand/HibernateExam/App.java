package com.hand.HibernateExam;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.hand.HibernateExam.CustomerJDBCTemplate;

public class App {
	public static void main(String[] args) {
		ApplicationContext context = new ClassPathXmlApplicationContext("ApplicationContext.xml");
		CustomerJDBCTemplate customerJDBCTemplate = (CustomerJDBCTemplate) context.getBean("customerJDBCTemplate");
		System.out.println("请输入FirstName(first_name):");
		Scanner scan = new Scanner(System.in);
		String first_name = scan.nextLine();

		System.out.println("请输入LastName(last_name):");
		String last_name = scan.nextLine();

		System.out.println("请输入Email(email):");
		String email = scan.nextLine();

		System.out.println("请输入Address ID(address_id):");
		int address_id = scan.nextInt();
		boolean key = true;
		while (key) {
			if (address_id < 0 || address_id > 606) {
				System.out.println("你输入的Address ID不存在,请重新输入:");
				address_id=scan.nextInt();
			} else {
				key = false;
			}
		}

		Date dt = new Date();
		
		
		customerJDBCTemplate.create(1, first_name, last_name, email, address_id, dt);

		SimpleDateFormat time=new SimpleDateFormat("yyyy-MM-dd HH：mm：ss"); 
		System.out.println("before save");
		System.out.println("已经保存的数据如下:");
		List<Customer> customers = customerJDBCTemplate.listCustomers();
		for (Customer record : customers) {
			System.out.println("ID:" + record.getId());
			System.out.println("FirstName:" + record.getFirst_name());
			System.out.println("LastName:" + record.getLast_name());
			System.out.println("Email:" + record.getEmail());
			System.out.println("Address:" + record.getAddress());
			System.out.println("Create_date:" + time.format(dt));
		}
		
		System.out.println("请输入要删除的Customer的ID:");
		int del = scan.nextInt();
		boolean key1 = true;
		while (key1) {
			if (customerJDBCTemplate.check(del)) {
				System.out.println("你输入的Customer ID不存在,请重新输入:");
				scan=new Scanner(System.in);
				del=scan.nextInt();
			} else {
				key = false;
			}
		}
		customerJDBCTemplate.delete(del);

	}
}
