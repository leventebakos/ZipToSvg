//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.8-b130911.1802 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2015.11.23 at 04:32:27 PM CET 
//


package com.epam.junior.ziptosvg.domain;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for ZipClassType.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="ZipClassType">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="UNIQUE"/>
 *     &lt;enumeration value="STANDARD"/>
 *     &lt;enumeration value="MILITARY"/>
 *     &lt;enumeration value="PO BOX ONLY"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "ZipClassType")
@XmlEnum
public enum ZipClassType {

    UNIQUE("UNIQUE"),
    STANDARD("STANDARD"),
    MILITARY("MILITARY"),
    @XmlEnumValue("PO BOX ONLY")
    PO_BOX_ONLY("PO BOX ONLY");
    private final String value;

    ZipClassType(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static ZipClassType fromValue(String v) {
        for (ZipClassType c: ZipClassType.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}