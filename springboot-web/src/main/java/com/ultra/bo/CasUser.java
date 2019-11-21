package com.ultra.bo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * @author admin
 */
@Setter
@Getter
@ToString
@NoArgsConstructor
public class CasUser implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;

    private String username;

    @JsonIgnore
    private String password;

    private Integer expired = 0;

    private Integer disabled = 0;

    private Integer locked = 0;

    /**
     * 需要返回实现org.apereo.cas.authentication.principal.Principal的类名接口
     */
    @JsonProperty("@class")
    private String clazz = "org.apereo.cas.authentication.principal.SimplePrincipal";

    @JsonProperty("attributes")
    private Map<String, Object> attributes = new HashMap<>();

    public CasUser(String username, String password) {
        this.username = username;
        this.password = password;
    }
}

