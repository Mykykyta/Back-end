package com.netcraker.model;

import lombok.*;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BookOverview {
    private int bookOverviewId;
    private @NonNull String description;
    private int bookId;
    private Book book;
    private int userId;
    private User user;
    private boolean published;
    private LocalDateTime creationTime;
}
