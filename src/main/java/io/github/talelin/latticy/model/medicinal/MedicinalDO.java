package io.github.talelin.latticy.model.medicinal;

import io.github.talelin.latticy.model.BaseModel;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * @author generator@TaleLin
 * @since 2020-10-18
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("fun_medicinal")
public class MedicinalDO extends BaseModel {


    /**
     * 药品名称
     */
    private String medicinalName;

    /**
     * 药品拼音码
     */
    private String pinyinma;

    /**
     * 药品规格
     */
    private String spec;

    /**
     * 药品单位
     */
    private String unit;

    /**
     * 药品描述
     */
    private String description;


}
