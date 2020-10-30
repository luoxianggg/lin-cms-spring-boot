package io.github.talelin.latticy.mapper.medicinal;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import io.github.talelin.latticy.common.mybatis.Page;
import io.github.talelin.latticy.model.medicinal.MedicinalInStockDO;
import io.github.talelin.latticy.model.medicinal.MedicinalStockListDo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;
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
    public List<MedicinalStockListDo> getMedicinalStockList(Page<?> page, @Param("map")Map<String,Object> map);
}
