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
@TableName("`pallet_log`")
public class DefaultPalletLog implements Serializable {
    private static final long serialVersionUID = 1L;
    @TableField("`address`")
    @JsonProperty("address")
    private String address;
    @TableField("`car_no`")
    @JsonProperty("car_no")
    private String carNo;
    @TableField("`created_at`")
    @JsonProperty("created_at")
    private String createdAt;
    @TableField("`deleted_at`")
    @JsonProperty("deleted_at")
    private String deletedAt;
    @TableField("`from_client_id`")
    @JsonProperty("from_client_id")
    private String fromClientId;
    @TableId(value = "id", type = IdType.AUTO)
    private String id;
    @TableField("`latitude`")
    @JsonProperty("latitude")
    private String latitude;
    @TableField("`longitude`")
    @JsonProperty("longitude")
    private String longitude;
    @TableField("`pallet_id`")
    @JsonProperty("pallet_id")
    private String palletId;
    @TableField("`to_client_id`")
    @JsonProperty("to_client_id")
    private String toClientId;
    @TableField("`trans_type`")
    @JsonProperty("trans_type")
    private String transType;
    @TableField("`updated_at`")
    @JsonProperty("updated_at")
    private String updatedAt;
    @TableField("`user_id`")
    @JsonProperty("user_id")
    private String userId;
}
