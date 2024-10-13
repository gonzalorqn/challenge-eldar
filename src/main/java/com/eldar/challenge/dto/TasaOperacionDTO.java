package com.eldar.challenge.dto;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * TasaOperacionDTO
 */

public class TasaOperacionDTO   {
  @JsonProperty("tasaOperacion")
  private String tasaOperacion;

  @JsonProperty("importe")
  private Float importe;

  @JsonProperty("marcaTarjeta")
  private String marcaTarjeta;

  public TasaOperacionDTO tasaOperacion(String tasaOperacion) {
    this.tasaOperacion = tasaOperacion;
    return this;
  }

  /**
   * Tasa de la operación.
   * @return tasaOperacion
  */
  @ApiModelProperty(value = "Tasa de la operación.")


  public String getTasaOperacion() {
    return tasaOperacion;
  }

  public void setTasaOperacion(String tasaOperacion) {
    this.tasaOperacion = tasaOperacion;
  }

  public TasaOperacionDTO importe(Float importe) {
    this.importe = importe;
    return this;
  }

  /**
   * Importe de la operación.
   * @return importe
  */
  @ApiModelProperty(value = "Importe de la operación.")


  public Float getImporte() {
    return importe;
  }

  public void setImporte(Float importe) {
    this.importe = importe;
  }

  public TasaOperacionDTO marcaTarjeta(String marcaTarjeta) {
    this.marcaTarjeta = marcaTarjeta;
    return this;
  }

  /**
   * Marca de la tarjeta.
   * @return marcaTarjeta
  */
  @ApiModelProperty(value = "Marca de la tarjeta.")


  public String getMarcaTarjeta() {
    return marcaTarjeta;
  }

  public void setMarcaTarjeta(String marcaTarjeta) {
    this.marcaTarjeta = marcaTarjeta;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    TasaOperacionDTO tasaOperacionDTO = (TasaOperacionDTO) o;
    return Objects.equals(this.tasaOperacion, tasaOperacionDTO.tasaOperacion) &&
        Objects.equals(this.importe, tasaOperacionDTO.importe) &&
        Objects.equals(this.marcaTarjeta, tasaOperacionDTO.marcaTarjeta);
  }

  @Override
  public int hashCode() {
    return Objects.hash(tasaOperacion, importe, marcaTarjeta);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class TasaOperacionDTO {\n");
    
    sb.append("    tasaOperacion: ").append(toIndentedString(tasaOperacion)).append("\n");
    sb.append("    importe: ").append(toIndentedString(importe)).append("\n");
    sb.append("    marcaTarjeta: ").append(toIndentedString(marcaTarjeta)).append("\n");
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

