package com.damda.application.restaurant.domain;

import com.damda.application.common.BaseTime;
import com.damda.application.restaurant.service.model.RestaurantUpdateModel;
import com.damda.application.user.domain.User;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "restaurants")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Restaurant extends BaseTime {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JoinColumn(name = "user_id", nullable = false)
    @ManyToOne(fetch = FetchType.LAZY)
    private User writer;

    @Embedded
    private RestaurantContents contents;

    @Embedded
    private RestaurantAdditionalContents additionalContents;

    public Restaurant(User writer, RestaurantContents contents, RestaurantAdditionalContents additionalContents) {
        this.writer = writer;
        this.contents = contents;
        this.additionalContents = additionalContents;
    }

    public void updateRestaurant(RestaurantUpdateModel updateModel) {
        contents.updateRestaurantContents(updateModel);
        additionalContents.updateRestaurantAdditionalContents(updateModel);
    }
}
