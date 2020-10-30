package io.github.talelin.latticy.model.medicinal;

import com.baomidou.mybatisplus.annotation.TableName;
import io.github.talelin.latticy.model.BaseModelNoDelete;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.math.BigDecimal;

/**
 * @author generator@TaleLin
 * @since 2020-10-18
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("fnd_customer")
public class CustomerDO extends BaseModelNoDelete {


    /**
     * 药品名称
     */
    private String name;

    /**
     * 药品拼音码
     */
    private String sex;

    /**
     * 药品规格
     */
    private BigDecimal age;

    /**
     * 药品单位
     */
    private String address;


}
