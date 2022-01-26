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
@TableName("`car`")
public class DefaultCar implements Serializable {
    private static final long serialVersionUID = 1L;
    @TableField("`contact`")
    @JsonProperty("contact")
    private String contact;
    @TableField("`created_at`")
    @JsonProperty("created_at")
    private String createdAt;
    @TableField("`deleted_at`")
    @JsonProperty("deleted_at")
    private String deletedAt;
    // @TableId(value = "id", type = IdType.ID_WORKER_STR)
    @TableId(value = "id", type = IdType.NONE)
    private String id;
    @TableField("`name`")
    @JsonProperty("name")
    private String name;
    @TableField("`no`")
    @JsonProperty("no")
    private String no;
    @TableField("`updated_at`")
    @JsonProperty("updated_at")
    private String updatedAt;
}
