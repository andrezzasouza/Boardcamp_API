package com.boardcamp.api.models;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.boardcamp.api.dtos.RentalDTO;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "rentals")
public class RentalModel {
  public RentalModel(RentalDTO dto, CustomerModel customer, GameModel game) {
    this.rentDate = this.formatDate(new Date());
    this.daysRented = dto.getDaysRented();
    this.returnDate = null;
    this.originalPrice = game.getPricePerDay() * dto.getDaysRented();
    this.delayFee = 0;
    this.customer = customer;
    this.game = game;
  }

  private Date formatDate(Date currentDate) {
    SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
    String formattedDateAsString = formatter.format(currentDate);

    try {
      return formatter.parse(formattedDateAsString);
    } catch (ParseException e) {
      e.printStackTrace();
      return null;
    }
  }

  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE)
  private Long id;

  @Column(nullable = false)
  private Date rentDate;

  @Column(nullable = false)
  private Integer daysRented;

  @Column(nullable = true)
  private Date returnDate;

  @Column(nullable = false)
  private Integer originalPrice;

  @Column(nullable = false)
  private Integer delayFee;

  @ManyToOne
  @JoinColumn(name = "customerId")
  private CustomerModel customer;

  @ManyToOne
  @JoinColumn(name = "gameId")
  private GameModel game;
}
