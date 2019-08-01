package com.baizhi.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.io.Serializable;
import java.util.*;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Admin implements Serializable {
    private String id;
    private String username;
    private String password;
    private String salt;
    private List<Role> roles;
}
