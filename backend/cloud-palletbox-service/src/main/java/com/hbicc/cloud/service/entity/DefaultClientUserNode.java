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
@TableName("`client_user_node`")
public class DefaultClientUserNode implements Serializable {
    private static final long serialVersionUID = 1L;
    @TableField("`action`")
    @JsonProperty("action")
    private String action;
    @TableField("`controller`")
    @JsonProperty("controller")
    private String controller;
    @TableField("`ctime`")
    @JsonProperty("ctime")
    private String ctime;
    @TableField("`group`")
    @JsonProperty("group")
    private String group;
    @TableId(value = "id", type = IdType.AUTO)
    private String id;
    @TableField("`name`")
    @JsonProperty("name")
    private String name;
    @TableField("`param`")
    @JsonProperty("param")
    private String param;
}
