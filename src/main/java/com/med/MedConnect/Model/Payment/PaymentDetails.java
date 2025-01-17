package com.med.MedConnect.Model.Payment;

import jakarta.persistence.*;

import java.util.Date;

@Entity
public class PaymentDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int paymentDetailsID;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "payment_id", referencedColumnName = "paymentid")
    private Payment payment;

    @Column
//    @OneToOne(cascade = CascadeType.ALL)
//    @JoinColumn(name = "donationid", referencedColumnName = "donationid")
    private int donationID;

    @Column
    private int amount;

    @Column
    private Date paymentDate;

    public PaymentDetails() {
    }

    public PaymentDetails(Payment payment, int donationID, int amount, Date paymentDate) {
        this.payment = payment;
        this.donationID = donationID;
        this.amount = amount;
        this.paymentDate = paymentDate;
    }

    public int getPaymentDetailsID() {
        return paymentDetailsID;
    }

    public void setPaymentDetailsID(int paymentDetailsID) {
        this.paymentDetailsID = paymentDetailsID;
    }

    public Payment getPayment() {
        return payment;
    }

    public void setPayment(Payment payment) {
        this.payment = payment;
    }

    public int getDonationID() {
        return donationID;
    }

    public void setDonationID(int donationID) {
        this.donationID = donationID;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public Date getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(Date paymentDate) {
        this.paymentDate = paymentDate;
    }

    @Override
    public String toString() {
        return "PaymentDetails{" +
                "paymentDetailsID=" + paymentDetailsID +
                ", payment=" + payment +
                ", donationID=" + donationID +
                ", amount=" + amount +
                ", paymentDate=" + paymentDate +
                '}';
    }
}
