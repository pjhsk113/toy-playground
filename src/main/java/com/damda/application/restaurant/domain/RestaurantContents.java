package com.damda.application.restaurant.domain;

import com.damda.application.restaurant.service.model.RestaurantUpdateModel;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.Size;

@Embeddable
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class RestaurantContents {

    @Size(max = 15)
    @Column(name = "name", nullable = false)
    private String name;

    @Size(max = 20)
    @Column(name = "address", nullable = false)
    private String address;

    public RestaurantContents(String name, String address) {
        this.name = name;
        this.address = address;
    }

    public void updateRestaurantContents(RestaurantUpdateModel updateModel) {
        if (updateModel.getName() != null) {
            name = updateModel.getName();
        }

        if (updateModel.getAddress() != null) {
            address = updateModel.getAddress();
        }
    }
}
