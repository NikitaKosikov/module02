package com.epam.esm;

import java.math.BigDecimal;
import java.util.Date;

public class GiftCertificate {
    private int id;
    private String name;
    private String description;
    private BigDecimal price;
    private int duration;
    private Date createDate;
    private Date lastUpdateDate;

    public GiftCertificate() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Date getLastUpdateDate() {
        return lastUpdateDate;
    }

    public void setLastUpdateDate(Date lastUpdateDate) {
        this.lastUpdateDate = lastUpdateDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GiftCertificate that = (GiftCertificate) o;
        return id == that.id &&
                duration == that.duration &&
                name.equals(that.name) &&
                description.equals(that.description) &&
                price.equals(that.price) &&
                createDate.equals(that.createDate) &&
                lastUpdateDate.equals(that.lastUpdateDate);
    }

    @Override
    public int hashCode() {
        int result = 17;
        result = 31 * result + name.hashCode();
        result = 31 * result + description.hashCode();
        result = 31 * result + price.hashCode();
        result = 31 * result + createDate.hashCode();
        result = 31 * result + lastUpdateDate.hashCode();
        return result;
    }
}
