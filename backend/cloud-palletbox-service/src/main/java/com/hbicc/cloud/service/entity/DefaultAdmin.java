package com.hbicc.cloud.service.entity;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import java.io.Serializable;
// import java.time.LocalDateTime;
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("`admin`")
public class DefaultAdmin implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableField("`ctime`")
    @JsonProperty("ctime")
    private String ctime;

    @TableField("`email`")
    @JsonProperty("email")
    private String email;

    @TableField("`is_valid`")
    @JsonProperty("is_valid")
    private String isValid;

    @TableField("`last_login_ip`")
    @JsonProperty("last_login_ip")
    private String lastLoginIp;

    @TableField("`last_login_time`")
    @JsonProperty("last_login_time")
    private String lastLoginTime;

    @TableField("`mtime`")
    @JsonProperty("mtime")
    private String mtime;

    @TableField("`nickname`")
    @JsonProperty("nickname")
    private String nickname;

    @TableField("`password`")
    @JsonProperty("password")
    private String password;

    @TableField("`phone`")
    @JsonProperty("phone")
    private String phone;

    @TableField("`role`")
    @JsonProperty("role")
    private String role;

    @TableId(value = "user_id", type = IdType.AUTO)
    private String userId;

    @TableField("`user_name`")
    @JsonProperty("user_name")
    private String userName;
}
