package io.github.talelin.latticy.controller.v1;


import io.github.talelin.core.annotation.GroupMeta;
import io.github.talelin.latticy.common.mybatis.Page;
import io.github.talelin.latticy.model.Response;
import io.github.talelin.latticy.service.MedicinalService;
import io.github.talelin.latticy.util.StringUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import io.github.talelin.latticy.model.MedicinalDO;

import java.util.Map;

/**
* @author generator@luoxiang
* @since 2020-10-18
*/
@RestController
@RequestMapping("/fun/medicinal")
@Slf4j
public class MedicinalController {

    @Autowired
    MedicinalService medicinalService;

    @GroupMeta(permission = "添加药品", module = "药品", mount = true)
    @RequestMapping("medicinalAdd")
    public Response medicinalAdd(@RequestBody Map<String,Object> paramMap){
        Response response = new Response();
        medicinalService.insertMedicinal(paramMap);
        return  response.success("添加成功：" + paramMap.get("medicinal_name"));
    }
    @GroupMeta(permission = "查询药品", module = "药品", mount = true)
    @RequestMapping("queryMedicinalList")
    public Response queryMedicinalList(@RequestBody Map<String,Object> paramMap){
        Response response = new Response();
        if(!StringUtil.hasKeyValue(paramMap,"pageNum") || !StringUtil.hasKeyValue(paramMap,"pageSize")){
            log.error("请求参数不正确");
            return response.failure("请求参数不正确");
        }
        Page<MedicinalDO> page = medicinalService.queryMedicinalList(paramMap);
        response.setCount((int)page.getTotal());
        return  response.success(page.getRecords());
    }
    @RequestMapping("queryMedicinalDetailById")
    public Response getMedicinalDetails(@RequestBody Map<String,Object> paramMap){
        Response response = new Response();
        return  response.success(medicinalService.getMedicinalDetails(paramMap));
    }
    @RequestMapping("updateMedicinal")
    public Response updateMedicinalMsg(@RequestBody Map<String,Object> paramMap){
        Response response = new Response();
        medicinalService.updateMedicinal(paramMap);
        return  response.success("信息更新成功！");
    }
    @RequestMapping("deleteMedicinal")
    public Response deleteMedicinals(@RequestBody Map<String,Object> paramMap){
        Response response = new Response();
        medicinalService.deleteMedicinal(paramMap);
        return response.success("删除成功！");
    }

}
