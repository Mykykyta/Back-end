package com.netcraker.repositories;

import com.netcraker.model.AuthorizationLinks;
import com.netcraker.model.mapper.LinkRowMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;

@Repository
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@PropertySource("classpath:sqlQueries.properties")
public class AuthorizationRepository {
    private final JdbcTemplate jdbcTemplate;

    @Value("${authorizationLink.create}")
    private String sqlCreateLink;

    @Value("${authorizationLink.update}")
    private String sqlUpdateLink;

    @Value("${authorizationLink.findByToken}")
    private String sqlFindLinkByToken;

    @Value("${authorizationLink.findByUserId}")
    private String sqlFindLinkByUserId;

    public AuthorizationLinks creteAuthorizationLinks(AuthorizationLinks authorizationLinks) {
        Object[] param = {authorizationLinks.getToken(),  new Timestamp(System.currentTimeMillis()),
                        authorizationLinks.getUserId(), authorizationLinks.isRegistrationToken(),
                        authorizationLinks.isUsed()};
        jdbcTemplate.update(sqlCreateLink, param);

        return authorizationLinks;
    }
    public AuthorizationLinks findByActivationCode(String token) {
        Object[] param = {token};
        return jdbcTemplate.queryForObject(sqlFindLinkByToken, param, new LinkRowMapper());
    }
    public AuthorizationLinks findByUserId(int user_id) {
        Object[] param = {user_id};
        return jdbcTemplate.queryForObject(sqlFindLinkByUserId, param, new LinkRowMapper());
    }
    public AuthorizationLinks updateAuthorizationLinks(AuthorizationLinks authorizationLinks) {
        jdbcTemplate.update(sqlUpdateLink, authorizationLinks.isUsed(), authorizationLinks.getToken());
        return authorizationLinks;
    }
}
