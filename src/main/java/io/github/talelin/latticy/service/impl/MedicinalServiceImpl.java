package io.github.talelin.latticy.service.impl;

import io.github.talelin.latticy.model.MedicinalDO;
import io.github.talelin.latticy.mapper.MedicinalMapper;
import io.github.talelin.latticy.service.MedicinalService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author generator@TaleLin
 * @since 2020-10-18
 */
@Service
public class MedicinalServiceImpl extends ServiceImpl<MedicinalMapper, MedicinalDO> implements MedicinalService {

    @Autowired
    private MedicinalMapper medicinalMapper;
    @Override
    public void insertMedicinal(Map<String, Object> map) {
        MedicinalDO medicinalDO = new MedicinalDO();
        System.out.println(map.get("medicinal_name").toString());
        medicinalDO.setMedicinalName(map.get("medicinal_name").toString());
        medicinalDO.setPinyinma(map.get("pinyinma").toString()+ "_1");
        medicinalDO.setSpec(map.get("spec").toString());
        medicinalDO.setUnit(map.get("unit").toString());
        medicinalDO.setDescription(map.get("description").toString());
        medicinalMapper.insertMedicinal(map);
        medicinalMapper.insert(medicinalDO);
    }

    @Override
    public List<MedicinalDO> queryMedicinalList(Map<String,Object> map){
      return   medicinalMapper.queryMedicinals(map);
    }
    @Override
    public MedicinalDO getMedicinalDetails(Map<String,Object> map){
        return medicinalMapper.queryMedicinalDetailById(map);
    }
}
