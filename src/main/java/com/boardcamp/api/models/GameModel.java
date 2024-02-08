package com.boardcamp.api.models;

import com.boardcamp.api.dtos.GameDTO;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "games")
public class GameModel {
  public GameModel(GameDTO dto) {
    this.name = dto.getName();
    this.image = dto.getImage();
    this.stockTotal = dto.getStockTotal();
    this.pricePerDay = dto.getPricePerDay();
  }

  public GameModel(GameModel model) {
    this.name = model.getName();
    this.image = model.getImage();
    this.stockTotal = model.getStockTotal();
    this.pricePerDay = model.getPricePerDay();
  }

  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE)
  private Long id;

  @Column(nullable = false, unique = true)
  private String name;

  @Column(columnDefinition = "text", nullable = false)
  private String image;

  @Column(nullable = false)
  private Integer stockTotal;

  @Column(nullable = false)
  private Integer pricePerDay;
}
