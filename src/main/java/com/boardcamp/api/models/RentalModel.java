package com.boardcamp.api.models;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

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
    this.rentDate = LocalDate.now();
    this.daysRented = dto.getDaysRented();
    this.returnDate = null;
    this.originalPrice = game.getPricePerDay() * dto.getDaysRented();
    this.delayFee = 0;
    this.customer = customer;
    this.game = game;
  }

  public RentalModel(RentalModel model) {
    this.rentDate = model.getRentDate();
    this.daysRented = model.getDaysRented();
    this.returnDate = LocalDate.now();
    this.originalPrice = model.getOriginalPrice();
    this.delayFee = calculateDelayFee(this.rentDate, this.returnDate, this.daysRented, model.game.getPricePerDay());
    this.customer = model.getCustomer();
    this.game = model.getGame();
  }

  private Integer calculateDelayFee(LocalDate rentDate, LocalDate returnDate, Integer daysRented, Integer pricePerDay) {
    Integer daysBetweenDates = (int) ChronoUnit.DAYS.between(rentDate, returnDate);

    if (daysBetweenDates <= daysRented) {
      return 0;
    }

    return (daysBetweenDates - daysRented) * pricePerDay;
  }

  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE)
  private Long id;

  @Column(nullable = false)
  private LocalDate rentDate;

  @Column(nullable = false)
  private Integer daysRented;

  @Column(nullable = true)
  private LocalDate returnDate;

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
