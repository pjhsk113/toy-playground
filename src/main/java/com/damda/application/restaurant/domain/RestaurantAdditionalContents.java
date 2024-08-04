package com.damda.application.restaurant.domain;

import com.damda.application.restaurant.service.model.RestaurantUpdateModel;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;

@Embeddable
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class RestaurantAdditionalContents {
    @Column(name = "restaurantPhoneNumber")
    private String restaurantPhoneNumber;

    @Column(name = "visited_date")
    private LocalDateTime visitedAt;

    @Column(name = "satisfied")
    private Float satisfied;

    @Column(name = "revisit")
    private boolean revisit;

    @Size(max = 40)
    @Column(name = "comment")
    private String comment;

    public RestaurantAdditionalContents(String restaurantPhoneNumber, LocalDateTime visitedAt, Float satisfied, boolean revisit, String comment) {
        this.restaurantPhoneNumber = restaurantPhoneNumber;
        this.visitedAt = visitedAt;
        this.satisfied = satisfied;
        this.revisit = revisit;
        this.comment = comment;
    }

    public void updateRestaurantAdditionalContents(RestaurantUpdateModel updateModel) {
        restaurantPhoneNumber = updateModel.getRestaurantPhoneNumber();
        visitedAt = updateModel.getVisitedAt();
        satisfied = updateModel.getSatisfied();
        comment = updateModel.getComment();
    }
}
