package com.damda.application.restaurant.api.v1;

import lombok.Getter;
import org.springframework.data.domain.Sort;

@Getter
public final class PageRequest {
    private int size = 10;
    private int page = 1;
    private Sort.Direction direction = Sort.Direction.DESC;

    public void setPage(int page) {
        this.page = page <= 0 ? 1 : page;
    }

    public void setSize(int size) {
        int DEFAULT_SIZE = 10;
        int MAX_SIZE = 50;
        this.size = size > MAX_SIZE ? DEFAULT_SIZE : size;
    }

    public void setDirection(Sort.Direction direction) {
        this.direction = direction;
    }

    public org.springframework.data.domain.PageRequest of() {
        return org.springframework.data.domain.PageRequest.of(page -1, size, direction, "createdAt");
    }

}
