package io.github.talelin.latticy.service.medicinal;

import io.github.talelin.latticy.common.mybatis.Page;
import io.github.talelin.latticy.model.medicinal.MedicinalDO;
import com.baomidou.mybatisplus.extension.service.IService;
import io.github.talelin.latticy.model.medicinal.MedicinalInStockDO;
import io.github.talelin.latticy.model.medicinal.MedicinalStockListDo;

import java.util.Map;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author generator@TaleLin
 * @since 2020-10-18
 */
public interface MedicinalService extends IService<MedicinalDO> {

    public void insertMedicinal(Map<String,Object> map);
    public Page<MedicinalDO> queryMedicinalList (Map<String,Object> map);
    public MedicinalDO getMedicinalDetails(Map<String,Object> map);
    public void updateMedicinal(Map<String,Object> map);
    public void deleteMedicinal(Map<String,Object> map);
    public String doMedicinalInstock(Map<String, Object> map);
    public Page<MedicinalStockListDo> queryMedicinalStockList(Map<String,Object> map);
    public Page<MedicinalInStockDO> quertMedicinalInstockList(Map<String,Object> map);
}
