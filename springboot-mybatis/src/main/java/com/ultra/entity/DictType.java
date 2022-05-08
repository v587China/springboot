package com.ultra.entity;

import com.ultra.constant.FormValidatedConstant;
import com.ultra.validated.InsertGroup;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import java.util.Date;

@Setter
@Getter
@ToString
public class DictType {

    /**
     * 主键
     */

    private Long id;
    /**
     * 名称
     */
    @NotBlank(groups = {InsertGroup.class}, message = "{attr.required}")
    @Length(min = FormValidatedConstant.COMMON_STR_MIN_LENGTH, max = FormValidatedConstant.COMMON_STR_MAX_LENGTH, message = "{attr.range.length}")
    private String name;
    /**
     * 备注
     */
    private String note;
    /**
     * 创建时间
     */
    private Date gmtCreate;
    /**
     * 更新时间
     */
    private Date gmtModified;
}
