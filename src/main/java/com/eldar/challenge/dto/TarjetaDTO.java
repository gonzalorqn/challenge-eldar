package com.eldar.challenge.dto;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * TarjetaDTO
 */

public class TarjetaDTO   {
  @JsonProperty("id")
  private Long id;

  @JsonProperty("marca")
  private String marca;

  @JsonProperty("numero")
  private String numero;

  @JsonProperty("cardholder")
  private String cardholder;

  @JsonProperty("fechaVencimiento")
  private String fechaVencimiento;

  public TarjetaDTO id(Long id) {
    this.id = id;
    return this;
  }

  /**
   * Id de la tarjeta.
   * @return id
  */
  @ApiModelProperty(value = "Id de la tarjeta.")


  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public TarjetaDTO marca(String marca) {
    this.marca = marca;
    return this;
  }

  /**
   * Marca de la tarjeta.
   * @return marca
  */
  @ApiModelProperty(value = "Marca de la tarjeta.")


  public String getMarca() {
    return marca;
  }

  public void setMarca(String marca) {
    this.marca = marca;
  }

  public TarjetaDTO numero(String numero) {
    this.numero = numero;
    return this;
  }

  /**
   * Número de la tarjeta.
   * @return numero
  */
  @ApiModelProperty(value = "Número de la tarjeta.")


  public String getNumero() {
    return numero;
  }

  public void setNumero(String numero) {
    this.numero = numero;
  }

  public TarjetaDTO cardholder(String cardholder) {
    this.cardholder = cardholder;
    return this;
  }

  /**
   * Nombre y apellido del dueño de la tarjeta.
   * @return cardholder
  */
  @ApiModelProperty(value = "Nombre y apellido del dueño de la tarjeta.")


  public String getCardholder() {
    return cardholder;
  }

  public void setCardholder(String cardholder) {
    this.cardholder = cardholder;
  }

  public TarjetaDTO fechaVencimiento(String fechaVencimiento) {
    this.fechaVencimiento = fechaVencimiento;
    return this;
  }

  /**
   * Mes y año de vencimiento de la tarjeta.
   * @return fechaVencimiento
  */
  @ApiModelProperty(value = "Mes y año de vencimiento de la tarjeta.")


  public String getFechaVencimiento() {
    return fechaVencimiento;
  }

  public void setFechaVencimiento(String fechaVencimiento) {
    this.fechaVencimiento = fechaVencimiento;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    TarjetaDTO tarjetaDTO = (TarjetaDTO) o;
    return Objects.equals(this.id, tarjetaDTO.id) &&
        Objects.equals(this.marca, tarjetaDTO.marca) &&
        Objects.equals(this.numero, tarjetaDTO.numero) &&
        Objects.equals(this.cardholder, tarjetaDTO.cardholder) &&
        Objects.equals(this.fechaVencimiento, tarjetaDTO.fechaVencimiento);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, marca, numero, cardholder, fechaVencimiento);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class TarjetaDTO {\n");
    
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    marca: ").append(toIndentedString(marca)).append("\n");
    sb.append("    numero: ").append(toIndentedString(numero)).append("\n");
    sb.append("    cardholder: ").append(toIndentedString(cardholder)).append("\n");
    sb.append("    fechaVencimiento: ").append(toIndentedString(fechaVencimiento)).append("\n");
    sb.append("}");
    return sb.toString();
  }

  /**
   * Convert the given object to string with each line indented by 4 spaces
   * (except the first line).
   */
  private String toIndentedString(java.lang.Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }
}

