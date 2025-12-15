package org.example;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Address {
    private int streetNo;
    private String street;
    private String city;
    private Province province;
    private String postalCode;

    public Address(int streetNo, String street, String city, Province province, String postalCode) {
        if (isPostalCodeValid(postalCode)) {
            this.streetNo = streetNo;
            this.street = street;
            this.city = city;
            this.province = province;
            this.postalCode = postalCode.toUpperCase();
        } else {
            this.streetNo = 0;
            this.street = null;
            this.city = null;
            this.province = null;
            this.postalCode = null;
        }
    }

    /**
     * Validates if a postal code follows the correct Canadian format
     * @param postalCode the postal code string to validate
     * @return true if valid false otherwise
     */
    public static boolean isPostalCodeValid(String postalCode) {
        if (postalCode == null || postalCode.length() != 6) {
            return false;
        }

        for (int i = 0; i < 6; i++) {
            char c = postalCode.charAt(i);
            if (i % 2 == 0) {
                if (!Character.isLetter(c)) {
                    return false;
                }
            } else {
                if (!Character.isDigit(c)) {
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * sets postal code with validation
     * @param postalCode the postal codee to set
     */
    public void setPostalCode(String postalCode) {
        if (isPostalCodeValid(postalCode)) {
            this.postalCode = postalCode.toUpperCase();
        }
    }

    @Override
    public String toString() {
        if (streetNo == 0 || street == null) {
            return "Address{invalid}";
        }
        return street + " " + streetNo + ", " + city + ", " + province.getAbbreviation() + " " + postalCode;
    }

    @Override
    /**
     * Compares two Address objects for equality.
     * @param obj the object to compare
     * @return true if equal, false otherwise
     */
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;

        Address address = (Address) obj;
        return streetNo == address.streetNo &&
                street.equals(address.street) &&
                city.equals(address.city) &&
                province == address.province &&
                postalCode.equals(address.postalCode);
    }
}

