package com.example.mapStudy.bean;

import lombok.Data;

import java.io.Serializable;
import java.util.Objects;

/**
 * ip
 *
 * @author
 */
@Data
public class Ip implements Serializable {
    private String ip;

    private static final long serialVersionUID = 1L;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (this.ip.equals(o)) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Ip ip1 = (Ip) o;
        return Objects.equals(ip, ip1.ip);
    }

    @Override
    public int hashCode() {
        return Objects.hash(ip);
    }
}