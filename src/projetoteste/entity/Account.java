package projetoteste.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "account")
public class Account implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;

	@Column(name = "accountNumber")
	private String accountNumber;

	@Column(name = "accountBalance")
	private Double accountBalance;

	@ManyToOne(fetch = FetchType.EAGER, optional = false)
	@JoinColumn(name = "client_id")
	private Client client;

	public Account() {

	}

	public Account(String accountNumber, Double accountBalance, Client client) {
		super();
		this.accountNumber = accountNumber;
		this.accountBalance = accountBalance;
		this.client = client;
	}

	public Account(int id, String accountNumber, Double accountBalance, Client client) {
		super();
		this.id = id;
		this.accountNumber = accountNumber;
		this.accountBalance = accountBalance;
		this.client = client;
	}

	public int getId() {
		return id;
	}

	public String getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}

	public Double getAccountBalance() {
		return accountBalance;
	}

	public void setAccountBalance(Double accountBalance) {
		this.accountBalance = accountBalance;
	}

	public Client getClient() {
		return client;
	}

	public void credit(Double value) {
		this.accountBalance += value;
	}

	public void debit(Double value) {
		this.accountBalance -= value;
	}

}
