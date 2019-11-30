package com.netcraker.repositories;

import com.netcraker.model.User;

import java.util.List;

public interface FriendRepository {
    List<User> getFriendsList(int userId);

    boolean isFriends(int user1Id, int user2Id);

    /**
     * Returns true if there
     *
     * @param sourceUserId
     * @param targetUserId
     * @return
     */
    boolean isAwaitingFriendRequestAccept(int sourceUserId, int targetUserId);

    boolean deleteFromFriends(int userId, int friendId);
}
