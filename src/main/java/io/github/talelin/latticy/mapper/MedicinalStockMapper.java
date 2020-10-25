package io.github.talelin.latticy.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import io.github.talelin.latticy.model.MedicinalInStockDO;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.Map;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author generator@luoxiang
 * @since 2020-10-18
 */
@Repository
public interface MedicinalStockMapper extends BaseMapper<MedicinalInStockDO> {
    public BigDecimal getStockMedicinals(Map<String,Object> map);
    public  String getMaxMedicinalNumbers(Map<String,Object> map);
    public void updateMedicinalStocks(Map<String,Object> map);
    public void initMedicinalStock(Map<String,Object> map);

}
