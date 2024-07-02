package bg.mck.ordercommandservice.entity.service;

import bg.mck.ordercommandservice.entity.BaseEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "services")
public class ServiceEntity extends BaseEntity {

    private String description;
    private double price;

    public ServiceEntity() {
    }

    public ServiceEntity(String description, double price) {
        this.description = description;
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public ServiceEntity setDescription(String description) {
        this.description = description;
        return this;
    }

    public double getPrice() {
        return price;
    }

    public ServiceEntity setPrice(double price) {
        this.price = price;
        return this;
    }
}
