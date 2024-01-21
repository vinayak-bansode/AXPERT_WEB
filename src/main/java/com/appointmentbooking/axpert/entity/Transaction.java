package com.appointmentbooking.axpert.entity;

import java.util.Set;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "transaction")
@NoArgsConstructor
@Getter
@Setter
public class Transaction {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	int id;
	int userid;
	int transactionid;
	int amount;
	String accountname;
	String transactiontype;
	String paymentgateway;
	String upi;
	String amounttype;
	int transactionstatus;// 0 - succesfull , 1 - failed , 2 - pending
    Long date;
	
}
