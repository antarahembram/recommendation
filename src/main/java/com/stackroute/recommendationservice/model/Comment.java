package com.stackroute.recommendationservice.model;

import java.time.LocalDateTime;

public class Comment {
    private String userName;
    private String comment;
    private LocalDateTime time;

    public Comment(String userName, String comment, LocalDateTime time, String userImage) {
        this.userName = userName;
        this.comment = comment;
        this.time = time;
        this.userImage = userImage;
    }

    private String userImage;
}
