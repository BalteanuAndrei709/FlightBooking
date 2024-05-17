/**
 * Autogenerated by Avro
 *
 * DO NOT EDIT DIRECTLY
 */
package com.payment.paymentservice.mock;

import org.apache.avro.specific.SpecificData;
import org.apache.avro.message.BinaryMessageEncoder;
import org.apache.avro.message.BinaryMessageDecoder;
import org.apache.avro.message.SchemaStore;

@SuppressWarnings("all")
@org.apache.avro.specific.AvroGenerated
public class BookingMock extends org.apache.avro.specific.SpecificRecordBase implements org.apache.avro.specific.SpecificRecord {
  private static final long serialVersionUID = 5074268356173497025L;
  public static final org.apache.avro.Schema SCHEMA$ = new org.apache.avro.Schema.Parser().parse("{\"type\":\"record\",\"name\":\"BookingMock\",\"namespace\":\"com.payment.paymentservice.mock\",\"fields\":[{\"name\":\"amount\",\"type\":\"double\"},{\"name\":\"iban\",\"type\":{\"type\":\"string\",\"avro.java.string\":\"String\"}},{\"name\":\"bookingId\",\"type\":{\"type\":\"string\",\"avro.java.string\":\"String\"}}]}");
  public static org.apache.avro.Schema getClassSchema() { return SCHEMA$; }

  private static SpecificData MODEL$ = new SpecificData();

  private static final BinaryMessageEncoder<BookingMock> ENCODER =
      new BinaryMessageEncoder<BookingMock>(MODEL$, SCHEMA$);

  private static final BinaryMessageDecoder<BookingMock> DECODER =
      new BinaryMessageDecoder<BookingMock>(MODEL$, SCHEMA$);

  /**
   * Return the BinaryMessageDecoder instance used by this class.
   */
  public static BinaryMessageDecoder<BookingMock> getDecoder() {
    return DECODER;
  }

  /**
   * Create a new BinaryMessageDecoder instance for this class that uses the specified {@link SchemaStore}.
   * @param resolver a {@link SchemaStore} used to find schemas by fingerprint
   */
  public static BinaryMessageDecoder<BookingMock> createDecoder(SchemaStore resolver) {
    return new BinaryMessageDecoder<BookingMock>(MODEL$, SCHEMA$, resolver);
  }

  /** Serializes this BookingMock to a ByteBuffer. */
  public java.nio.ByteBuffer toByteBuffer() throws java.io.IOException {
    return ENCODER.encode(this);
  }

  /** Deserializes a BookingMock from a ByteBuffer. */
  public static BookingMock fromByteBuffer(
      java.nio.ByteBuffer b) throws java.io.IOException {
    return DECODER.decode(b);
  }

  @Deprecated public double amount;
  @Deprecated public java.lang.String iban;
  @Deprecated public java.lang.String bookingId;

  /**
   * Default constructor.  Note that this does not initialize fields
   * to their default values from the schema.  If that is desired then
   * one should use <code>newBuilder()</code>.
   */
  public BookingMock() {}

  /**
   * All-args constructor.
   * @param amount The new value for amount
   * @param iban The new value for iban
   * @param bookingId The new value for bookingId
   */
  public BookingMock(java.lang.Double amount, java.lang.String iban, java.lang.String bookingId) {
    this.amount = amount;
    this.iban = iban;
    this.bookingId = bookingId;
  }

  public org.apache.avro.Schema getSchema() { return SCHEMA$; }
  // Used by DatumWriter.  Applications should not call.
  public java.lang.Object get(int field$) {
    switch (field$) {
    case 0: return amount;
    case 1: return iban;
    case 2: return bookingId;
    default: throw new org.apache.avro.AvroRuntimeException("Bad index");
    }
  }

  // Used by DatumReader.  Applications should not call.
  @SuppressWarnings(value="unchecked")
  public void put(int field$, java.lang.Object value$) {
    switch (field$) {
    case 0: amount = (java.lang.Double)value$; break;
    case 1: iban = (java.lang.String)value$; break;
    case 2: bookingId = (java.lang.String)value$; break;
    default: throw new org.apache.avro.AvroRuntimeException("Bad index");
    }
  }

  /**
   * Gets the value of the 'amount' field.
   * @return The value of the 'amount' field.
   */
  public java.lang.Double getAmount() {
    return amount;
  }

  /**
   * Sets the value of the 'amount' field.
   * @param value the value to set.
   */
  public void setAmount(java.lang.Double value) {
    this.amount = value;
  }

  /**
   * Gets the value of the 'iban' field.
   * @return The value of the 'iban' field.
   */
  public java.lang.String getIban() {
    return iban;
  }

  /**
   * Sets the value of the 'iban' field.
   * @param value the value to set.
   */
  public void setIban(java.lang.String value) {
    this.iban = value;
  }

  /**
   * Gets the value of the 'bookingId' field.
   * @return The value of the 'bookingId' field.
   */
  public java.lang.String getBookingId() {
    return bookingId;
  }

  /**
   * Sets the value of the 'bookingId' field.
   * @param value the value to set.
   */
  public void setBookingId(java.lang.String value) {
    this.bookingId = value;
  }

  /**
   * Creates a new BookingMock RecordBuilder.
   * @return A new BookingMock RecordBuilder
   */
  public static com.payment.paymentservice.mock.BookingMock.Builder newBuilder() {
    return new com.payment.paymentservice.mock.BookingMock.Builder();
  }

  /**
   * Creates a new BookingMock RecordBuilder by copying an existing Builder.
   * @param other The existing builder to copy.
   * @return A new BookingMock RecordBuilder
   */
  public static com.payment.paymentservice.mock.BookingMock.Builder newBuilder(com.payment.paymentservice.mock.BookingMock.Builder other) {
    return new com.payment.paymentservice.mock.BookingMock.Builder(other);
  }

  /**
   * Creates a new BookingMock RecordBuilder by copying an existing BookingMock instance.
   * @param other The existing instance to copy.
   * @return A new BookingMock RecordBuilder
   */
  public static com.payment.paymentservice.mock.BookingMock.Builder newBuilder(com.payment.paymentservice.mock.BookingMock other) {
    return new com.payment.paymentservice.mock.BookingMock.Builder(other);
  }

  /**
   * RecordBuilder for BookingMock instances.
   */
  public static class Builder extends org.apache.avro.specific.SpecificRecordBuilderBase<BookingMock>
    implements org.apache.avro.data.RecordBuilder<BookingMock> {

    private double amount;
    private java.lang.String iban;
    private java.lang.String bookingId;

    /** Creates a new Builder */
    private Builder() {
      super(SCHEMA$);
    }

    /**
     * Creates a Builder by copying an existing Builder.
     * @param other The existing Builder to copy.
     */
    private Builder(com.payment.paymentservice.mock.BookingMock.Builder other) {
      super(other);
      if (isValidValue(fields()[0], other.amount)) {
        this.amount = data().deepCopy(fields()[0].schema(), other.amount);
        fieldSetFlags()[0] = true;
      }
      if (isValidValue(fields()[1], other.iban)) {
        this.iban = data().deepCopy(fields()[1].schema(), other.iban);
        fieldSetFlags()[1] = true;
      }
      if (isValidValue(fields()[2], other.bookingId)) {
        this.bookingId = data().deepCopy(fields()[2].schema(), other.bookingId);
        fieldSetFlags()[2] = true;
      }
    }

    /**
     * Creates a Builder by copying an existing BookingMock instance
     * @param other The existing instance to copy.
     */
    private Builder(com.payment.paymentservice.mock.BookingMock other) {
            super(SCHEMA$);
      if (isValidValue(fields()[0], other.amount)) {
        this.amount = data().deepCopy(fields()[0].schema(), other.amount);
        fieldSetFlags()[0] = true;
      }
      if (isValidValue(fields()[1], other.iban)) {
        this.iban = data().deepCopy(fields()[1].schema(), other.iban);
        fieldSetFlags()[1] = true;
      }
      if (isValidValue(fields()[2], other.bookingId)) {
        this.bookingId = data().deepCopy(fields()[2].schema(), other.bookingId);
        fieldSetFlags()[2] = true;
      }
    }

    /**
      * Gets the value of the 'amount' field.
      * @return The value.
      */
    public java.lang.Double getAmount() {
      return amount;
    }

    /**
      * Sets the value of the 'amount' field.
      * @param value The value of 'amount'.
      * @return This builder.
      */
    public com.payment.paymentservice.mock.BookingMock.Builder setAmount(double value) {
      validate(fields()[0], value);
      this.amount = value;
      fieldSetFlags()[0] = true;
      return this;
    }

    /**
      * Checks whether the 'amount' field has been set.
      * @return True if the 'amount' field has been set, false otherwise.
      */
    public boolean hasAmount() {
      return fieldSetFlags()[0];
    }


    /**
      * Clears the value of the 'amount' field.
      * @return This builder.
      */
    public com.payment.paymentservice.mock.BookingMock.Builder clearAmount() {
      fieldSetFlags()[0] = false;
      return this;
    }

    /**
      * Gets the value of the 'iban' field.
      * @return The value.
      */
    public java.lang.String getIban() {
      return iban;
    }

    /**
      * Sets the value of the 'iban' field.
      * @param value The value of 'iban'.
      * @return This builder.
      */
    public com.payment.paymentservice.mock.BookingMock.Builder setIban(java.lang.String value) {
      validate(fields()[1], value);
      this.iban = value;
      fieldSetFlags()[1] = true;
      return this;
    }

    /**
      * Checks whether the 'iban' field has been set.
      * @return True if the 'iban' field has been set, false otherwise.
      */
    public boolean hasIban() {
      return fieldSetFlags()[1];
    }


    /**
      * Clears the value of the 'iban' field.
      * @return This builder.
      */
    public com.payment.paymentservice.mock.BookingMock.Builder clearIban() {
      iban = null;
      fieldSetFlags()[1] = false;
      return this;
    }

    /**
      * Gets the value of the 'bookingId' field.
      * @return The value.
      */
    public java.lang.String getBookingId() {
      return bookingId;
    }

    /**
      * Sets the value of the 'bookingId' field.
      * @param value The value of 'bookingId'.
      * @return This builder.
      */
    public com.payment.paymentservice.mock.BookingMock.Builder setBookingId(java.lang.String value) {
      validate(fields()[2], value);
      this.bookingId = value;
      fieldSetFlags()[2] = true;
      return this;
    }

    /**
      * Checks whether the 'bookingId' field has been set.
      * @return True if the 'bookingId' field has been set, false otherwise.
      */
    public boolean hasBookingId() {
      return fieldSetFlags()[2];
    }


    /**
      * Clears the value of the 'bookingId' field.
      * @return This builder.
      */
    public com.payment.paymentservice.mock.BookingMock.Builder clearBookingId() {
      bookingId = null;
      fieldSetFlags()[2] = false;
      return this;
    }

    @Override
    @SuppressWarnings("unchecked")
    public BookingMock build() {
      try {
        BookingMock record = new BookingMock();
        record.amount = fieldSetFlags()[0] ? this.amount : (java.lang.Double) defaultValue(fields()[0]);
        record.iban = fieldSetFlags()[1] ? this.iban : (java.lang.String) defaultValue(fields()[1]);
        record.bookingId = fieldSetFlags()[2] ? this.bookingId : (java.lang.String) defaultValue(fields()[2]);
        return record;
      } catch (java.lang.Exception e) {
        throw new org.apache.avro.AvroRuntimeException(e);
      }
    }
  }

  @SuppressWarnings("unchecked")
  private static final org.apache.avro.io.DatumWriter<BookingMock>
    WRITER$ = (org.apache.avro.io.DatumWriter<BookingMock>)MODEL$.createDatumWriter(SCHEMA$);

  @Override public void writeExternal(java.io.ObjectOutput out)
    throws java.io.IOException {
    WRITER$.write(this, SpecificData.getEncoder(out));
  }

  @SuppressWarnings("unchecked")
  private static final org.apache.avro.io.DatumReader<BookingMock>
    READER$ = (org.apache.avro.io.DatumReader<BookingMock>)MODEL$.createDatumReader(SCHEMA$);

  @Override public void readExternal(java.io.ObjectInput in)
    throws java.io.IOException {
    READER$.read(this, SpecificData.getDecoder(in));
  }

}
