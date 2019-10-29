package com.stackroute.recommendationservice.model;

import java.time.LocalDateTime;

public class Rating {
    private String userName;
    private int rating;
    private LocalDateTime time;

    @Override
    public String toString() {
        return "Rating{" +
                "userName='" + userName + '\'' +
                ", rating=" + rating +
                ", time=" + time +
                '}';
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public LocalDateTime getTime() {
        return time;
    }

    public void setTime(LocalDateTime time) {
        this.time = time;
    }
}
