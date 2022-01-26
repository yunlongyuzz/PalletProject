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
@TableName("`client_user_role`")
public class DefaultClientUserRole implements Serializable {
    private static final long serialVersionUID = 1L;
    @TableField("`client_id`")
    @JsonProperty("client_id")
    private String clientId;
    @TableField("`ctime`")
    @JsonProperty("ctime")
    private String ctime;
    @TableId(value = "id", type = IdType.AUTO)
    private String id;
    @TableField("`name`")
    @JsonProperty("name")
    private String name;
    @TableField("`node`")
    @JsonProperty("node")
    private String node;
}
