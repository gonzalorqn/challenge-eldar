package com.eldar.challenge.dto;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.eldar.challenge.dto.TarjetaDTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.time.OffsetDateTime;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * OperacionDTO
 */

public class OperacionDTO   {
  @JsonProperty("id")
  private Long id;

  @JsonProperty("importe")
  private Float importe;

  @JsonProperty("tarjeta")
  private TarjetaDTO tarjeta = null;

  @JsonProperty("fechaTransaccion")
  private OffsetDateTime fechaTransaccion;

  public OperacionDTO id(Long id) {
    this.id = id;
    return this;
  }

  /**
   * Id de la operación.
   * @return id
  */
  @ApiModelProperty(value = "Id de la operación.")


  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public OperacionDTO importe(Float importe) {
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

  public OperacionDTO tarjeta(TarjetaDTO tarjeta) {
    this.tarjeta = tarjeta;
    return this;
  }

  /**
   * Get tarjeta
   * @return tarjeta
  */
  @ApiModelProperty(value = "")

  @Valid

  public TarjetaDTO getTarjeta() {
    return tarjeta;
  }

  public void setTarjeta(TarjetaDTO tarjeta) {
    this.tarjeta = tarjeta;
  }

  public OperacionDTO fechaTransaccion(OffsetDateTime fechaTransaccion) {
    this.fechaTransaccion = fechaTransaccion;
    return this;
  }

  /**
   * Fecha y hora en la que se realizó la operación.
   * @return fechaTransaccion
  */
  @ApiModelProperty(value = "Fecha y hora en la que se realizó la operación.")

  @Valid

  public OffsetDateTime getFechaTransaccion() {
    return fechaTransaccion;
  }

  public void setFechaTransaccion(OffsetDateTime fechaTransaccion) {
    this.fechaTransaccion = fechaTransaccion;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    OperacionDTO operacionDTO = (OperacionDTO) o;
    return Objects.equals(this.id, operacionDTO.id) &&
        Objects.equals(this.importe, operacionDTO.importe) &&
        Objects.equals(this.tarjeta, operacionDTO.tarjeta) &&
        Objects.equals(this.fechaTransaccion, operacionDTO.fechaTransaccion);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, importe, tarjeta, fechaTransaccion);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class OperacionDTO {\n");
    
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    importe: ").append(toIndentedString(importe)).append("\n");
    sb.append("    tarjeta: ").append(toIndentedString(tarjeta)).append("\n");
    sb.append("    fechaTransaccion: ").append(toIndentedString(fechaTransaccion)).append("\n");
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

