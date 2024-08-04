package com.damda.application.restaurant.api.v1;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@AllArgsConstructor
public class RestaurantResponse {
    private List<IndividualRestaurant> restaurants;
    private int count;
    private long totalCount;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @AllArgsConstructor
    @Getter
    public static class IndividualRestaurant {
        private Long id;
        private String name;
        private String address;
        private String restaurantPhoneNumber;
        private LocalDateTime visitedAt;
        private Float satisfied;
        private String comment;
        private boolean revisit;

    }
}
