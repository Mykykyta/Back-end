package com.netcraker.model.mapper;

import com.netcraker.model.FriendInvitation;
import lombok.Builder;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

@Builder
public class FriendInvitationRowMapper implements RowMapper<FriendInvitation> {
    @Override
    public FriendInvitation mapRow(ResultSet resultSet, int i) throws SQLException {
        Boolean accepted = resultSet.getBoolean("accepted");
        if (resultSet.wasNull()) {
            accepted = null;
        }
        return FriendInvitation.builder()
                .invitationId(resultSet.getInt("invitation_id"))
                .invitationSource(resultSet.getInt("invitation_source"))
                .invitationTarget(resultSet.getInt("invitation_target"))
                .accepted(accepted)
                .creationTime(resultSet.getTimestamp("creation_time"))
                .build();
    }
}
