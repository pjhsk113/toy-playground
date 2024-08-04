package com.damda.application.restaurant.api.v1;

import com.damda.application.restaurant.service.model.RestaurantUpdateModel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
public class RestaurantUpdateRequest {
    @Size(max = 15)
    @NotBlank
    private String name;

    @Size(max = 20)
    @NotBlank
    private String address;

    private String restaurantPhoneNumber;

    private LocalDateTime visitedAt;

    private Float satisfied;

    @Size(max = 40)
    private String comment;

    public RestaurantUpdateModel toUpdateRestaurantModel() {
        return RestaurantUpdateModel.of(
                name,
                address,
                restaurantPhoneNumber,
                visitedAt,
                satisfied,
                comment
        );
    }
}
