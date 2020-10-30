package io.github.talelin.latticy.mapper.medicinal;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import io.github.talelin.latticy.common.mybatis.Page;
import io.github.talelin.latticy.model.medicinal.CustomerDO;
import io.github.talelin.latticy.model.medicinal.PrescribeDO;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface MedicinalSellMapper extends BaseMapper<PrescribeDO> {
    public Page<PrescribeDO> getPrePrescribeList(Map<String,Object> map);
    public String getetPrescribeNumber(Map<String,Object> map);
    public void insertNewCustomer(Map<String,Object> map);
    public List<CustomerDO> getCustomers(Map<String,Object> map);
    public void saveMedicinalSells(Map<String,Object> map);
    public PrescribeDO getPrescribesByDocNum(String docNum);
}
