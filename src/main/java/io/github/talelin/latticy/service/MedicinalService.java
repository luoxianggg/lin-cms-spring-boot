package io.github.talelin.latticy.service;

import io.github.talelin.latticy.mapper.MedicinalMapper;
import io.github.talelin.latticy.model.MedicinalDO;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
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
    public List<MedicinalDO> queryMedicinalList(Map<String,Object> map);
    public MedicinalDO getMedicinalDetails(Map<String,Object> map);
}
