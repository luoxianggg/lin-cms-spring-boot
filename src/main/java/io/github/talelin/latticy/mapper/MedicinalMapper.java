package io.github.talelin.latticy.mapper;

import io.github.talelin.latticy.model.MedicinalDO;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author generator@TaleLin
 * @since 2020-10-18
 */
@Repository
public interface MedicinalMapper extends BaseMapper<MedicinalDO> {
 public void  insertMedicinal(Map<String,Object> map);
 public List< MedicinalDO> queryMedicinals(Map<String,Object> map);
 public MedicinalDO queryMedicinalDetailById(Map<String,Object> map);
 public void updateMedicinal(Map<String,Object> map);
}
