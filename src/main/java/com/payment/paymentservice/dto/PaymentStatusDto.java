/**
 * Autogenerated by Avro
 *
 * DO NOT EDIT DIRECTLY
 */
package com.payment.paymentservice.dto;

import org.apache.avro.specific.SpecificData;
import org.apache.avro.message.BinaryMessageEncoder;
import org.apache.avro.message.BinaryMessageDecoder;
import org.apache.avro.message.SchemaStore;

@SuppressWarnings("all")
@org.apache.avro.specific.AvroGenerated
public class PaymentStatusDto extends org.apache.avro.specific.SpecificRecordBase implements org.apache.avro.specific.SpecificRecord {
  private static final long serialVersionUID = 5528885341493972025L;
  public static final org.apache.avro.Schema SCHEMA$ = new org.apache.avro.Schema.Parser().parse("{\"type\":\"record\",\"name\":\"PaymentStatusDto\",\"namespace\":\"com.payment.paymentservice.dto\",\"fields\":[{\"name\":\"orderId\",\"type\":{\"type\":\"string\",\"avro.java.string\":\"String\"}},{\"name\":\"orderStatus\",\"type\":{\"type\":\"string\",\"avro.java.string\":\"String\"}}]}");
  public static org.apache.avro.Schema getClassSchema() { return SCHEMA$; }

  private static SpecificData MODEL$ = new SpecificData();

  private static final BinaryMessageEncoder<PaymentStatusDto> ENCODER =
      new BinaryMessageEncoder<PaymentStatusDto>(MODEL$, SCHEMA$);

  private static final BinaryMessageDecoder<PaymentStatusDto> DECODER =
      new BinaryMessageDecoder<PaymentStatusDto>(MODEL$, SCHEMA$);

  /**
   * Return the BinaryMessageDecoder instance used by this class.
   */
  public static BinaryMessageDecoder<PaymentStatusDto> getDecoder() {
    return DECODER;
  }

  /**
   * Create a new BinaryMessageDecoder instance for this class that uses the specified {@link SchemaStore}.
   * @param resolver a {@link SchemaStore} used to find schemas by fingerprint
   */
  public static BinaryMessageDecoder<PaymentStatusDto> createDecoder(SchemaStore resolver) {
    return new BinaryMessageDecoder<PaymentStatusDto>(MODEL$, SCHEMA$, resolver);
  }

  /** Serializes this PaymentStatusDto to a ByteBuffer. */
  public java.nio.ByteBuffer toByteBuffer() throws java.io.IOException {
    return ENCODER.encode(this);
  }

  /** Deserializes a PaymentStatusDto from a ByteBuffer. */
  public static PaymentStatusDto fromByteBuffer(
      java.nio.ByteBuffer b) throws java.io.IOException {
    return DECODER.decode(b);
  }

  @Deprecated public java.lang.String orderId;
  @Deprecated public java.lang.String orderStatus;

  /**
   * Default constructor.  Note that this does not initialize fields
   * to their default values from the schema.  If that is desired then
   * one should use <code>newBuilder()</code>.
   */
  public PaymentStatusDto() {}

  /**
   * All-args constructor.
   * @param orderId The new value for orderId
   * @param orderStatus The new value for orderStatus
   */
  public PaymentStatusDto(java.lang.String orderId, java.lang.String orderStatus) {
    this.orderId = orderId;
    this.orderStatus = orderStatus;
  }

  public org.apache.avro.Schema getSchema() { return SCHEMA$; }
  // Used by DatumWriter.  Applications should not call.
  public java.lang.Object get(int field$) {
    switch (field$) {
    case 0: return orderId;
    case 1: return orderStatus;
    default: throw new org.apache.avro.AvroRuntimeException("Bad index");
    }
  }

  // Used by DatumReader.  Applications should not call.
  @SuppressWarnings(value="unchecked")
  public void put(int field$, java.lang.Object value$) {
    switch (field$) {
    case 0: orderId = (java.lang.String)value$; break;
    case 1: orderStatus = (java.lang.String)value$; break;
    default: throw new org.apache.avro.AvroRuntimeException("Bad index");
    }
  }

  /**
   * Gets the value of the 'orderId' field.
   * @return The value of the 'orderId' field.
   */
  public java.lang.String getOrderId() {
    return orderId;
  }

  /**
   * Sets the value of the 'orderId' field.
   * @param value the value to set.
   */
  public void setOrderId(java.lang.String value) {
    this.orderId = value;
  }

  /**
   * Gets the value of the 'orderStatus' field.
   * @return The value of the 'orderStatus' field.
   */
  public java.lang.String getOrderStatus() {
    return orderStatus;
  }

  /**
   * Sets the value of the 'orderStatus' field.
   * @param value the value to set.
   */
  public void setOrderStatus(java.lang.String value) {
    this.orderStatus = value;
  }

  /**
   * Creates a new PaymentStatusDto RecordBuilder.
   * @return A new PaymentStatusDto RecordBuilder
   */
  public static com.payment.paymentservice.dto.PaymentStatusDto.Builder newBuilder() {
    return new com.payment.paymentservice.dto.PaymentStatusDto.Builder();
  }

  /**
   * Creates a new PaymentStatusDto RecordBuilder by copying an existing Builder.
   * @param other The existing builder to copy.
   * @return A new PaymentStatusDto RecordBuilder
   */
  public static com.payment.paymentservice.dto.PaymentStatusDto.Builder newBuilder(com.payment.paymentservice.dto.PaymentStatusDto.Builder other) {
    return new com.payment.paymentservice.dto.PaymentStatusDto.Builder(other);
  }

  /**
   * Creates a new PaymentStatusDto RecordBuilder by copying an existing PaymentStatusDto instance.
   * @param other The existing instance to copy.
   * @return A new PaymentStatusDto RecordBuilder
   */
  public static com.payment.paymentservice.dto.PaymentStatusDto.Builder newBuilder(com.payment.paymentservice.dto.PaymentStatusDto other) {
    return new com.payment.paymentservice.dto.PaymentStatusDto.Builder(other);
  }

  /**
   * RecordBuilder for PaymentStatusDto instances.
   */
  public static class Builder extends org.apache.avro.specific.SpecificRecordBuilderBase<PaymentStatusDto>
    implements org.apache.avro.data.RecordBuilder<PaymentStatusDto> {

    private java.lang.String orderId;
    private java.lang.String orderStatus;

    /** Creates a new Builder */
    private Builder() {
      super(SCHEMA$);
    }

    /**
     * Creates a Builder by copying an existing Builder.
     * @param other The existing Builder to copy.
     */
    private Builder(com.payment.paymentservice.dto.PaymentStatusDto.Builder other) {
      super(other);
      if (isValidValue(fields()[0], other.orderId)) {
        this.orderId = data().deepCopy(fields()[0].schema(), other.orderId);
        fieldSetFlags()[0] = true;
      }
      if (isValidValue(fields()[1], other.orderStatus)) {
        this.orderStatus = data().deepCopy(fields()[1].schema(), other.orderStatus);
        fieldSetFlags()[1] = true;
      }
    }

    /**
     * Creates a Builder by copying an existing PaymentStatusDto instance
     * @param other The existing instance to copy.
     */
    private Builder(com.payment.paymentservice.dto.PaymentStatusDto other) {
            super(SCHEMA$);
      if (isValidValue(fields()[0], other.orderId)) {
        this.orderId = data().deepCopy(fields()[0].schema(), other.orderId);
        fieldSetFlags()[0] = true;
      }
      if (isValidValue(fields()[1], other.orderStatus)) {
        this.orderStatus = data().deepCopy(fields()[1].schema(), other.orderStatus);
        fieldSetFlags()[1] = true;
      }
    }

    /**
      * Gets the value of the 'orderId' field.
      * @return The value.
      */
    public java.lang.String getOrderId() {
      return orderId;
    }

    /**
      * Sets the value of the 'orderId' field.
      * @param value The value of 'orderId'.
      * @return This builder.
      */
    public com.payment.paymentservice.dto.PaymentStatusDto.Builder setOrderId(java.lang.String value) {
      validate(fields()[0], value);
      this.orderId = value;
      fieldSetFlags()[0] = true;
      return this;
    }

    /**
      * Checks whether the 'orderId' field has been set.
      * @return True if the 'orderId' field has been set, false otherwise.
      */
    public boolean hasOrderId() {
      return fieldSetFlags()[0];
    }


    /**
      * Clears the value of the 'orderId' field.
      * @return This builder.
      */
    public com.payment.paymentservice.dto.PaymentStatusDto.Builder clearOrderId() {
      orderId = null;
      fieldSetFlags()[0] = false;
      return this;
    }

    /**
      * Gets the value of the 'orderStatus' field.
      * @return The value.
      */
    public java.lang.String getOrderStatus() {
      return orderStatus;
    }

    /**
      * Sets the value of the 'orderStatus' field.
      * @param value The value of 'orderStatus'.
      * @return This builder.
      */
    public com.payment.paymentservice.dto.PaymentStatusDto.Builder setOrderStatus(java.lang.String value) {
      validate(fields()[1], value);
      this.orderStatus = value;
      fieldSetFlags()[1] = true;
      return this;
    }

    /**
      * Checks whether the 'orderStatus' field has been set.
      * @return True if the 'orderStatus' field has been set, false otherwise.
      */
    public boolean hasOrderStatus() {
      return fieldSetFlags()[1];
    }


    /**
      * Clears the value of the 'orderStatus' field.
      * @return This builder.
      */
    public com.payment.paymentservice.dto.PaymentStatusDto.Builder clearOrderStatus() {
      orderStatus = null;
      fieldSetFlags()[1] = false;
      return this;
    }

    @Override
    @SuppressWarnings("unchecked")
    public PaymentStatusDto build() {
      try {
        PaymentStatusDto record = new PaymentStatusDto();
        record.orderId = fieldSetFlags()[0] ? this.orderId : (java.lang.String) defaultValue(fields()[0]);
        record.orderStatus = fieldSetFlags()[1] ? this.orderStatus : (java.lang.String) defaultValue(fields()[1]);
        return record;
      } catch (java.lang.Exception e) {
        throw new org.apache.avro.AvroRuntimeException(e);
      }
    }
  }

  @SuppressWarnings("unchecked")
  private static final org.apache.avro.io.DatumWriter<PaymentStatusDto>
    WRITER$ = (org.apache.avro.io.DatumWriter<PaymentStatusDto>)MODEL$.createDatumWriter(SCHEMA$);

  @Override public void writeExternal(java.io.ObjectOutput out)
    throws java.io.IOException {
    WRITER$.write(this, SpecificData.getEncoder(out));
  }

  @SuppressWarnings("unchecked")
  private static final org.apache.avro.io.DatumReader<PaymentStatusDto>
    READER$ = (org.apache.avro.io.DatumReader<PaymentStatusDto>)MODEL$.createDatumReader(SCHEMA$);

  @Override public void readExternal(java.io.ObjectInput in)
    throws java.io.IOException {
    READER$.read(this, SpecificData.getDecoder(in));
  }

}
