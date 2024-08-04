package com.damda.application.restaurant.api.v1;

import com.damda.application.restaurant.domain.RestaurantAdditionalContents;
import com.damda.application.restaurant.domain.RestaurantContents;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
public class RestaurantRegisterRequest {
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

    private boolean revisit;

    public RestaurantContents toRestaurantContents() {
        return new RestaurantContents(
                this.name,
                this.address
        );
    }

    public RestaurantAdditionalContents toRestaurantAdditionalContents() {
        return new RestaurantAdditionalContents(
                this.restaurantPhoneNumber,
                this.visitedAt,
                this.satisfied,
                this.revisit,
                this.comment
        );
    }
}
