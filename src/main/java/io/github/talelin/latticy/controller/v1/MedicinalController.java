package io.github.talelin.latticy.controller.v1;


import io.github.talelin.latticy.model.Response;
import io.github.talelin.latticy.service.MedicinalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import io.github.talelin.latticy.model.MedicinalDO;
import io.github.talelin.latticy.vo.CreatedVO;
import io.github.talelin.latticy.vo.DeletedVO;
import io.github.talelin.latticy.vo.PageResponseVO;
import io.github.talelin.latticy.vo.UpdatedVO;

import javax.validation.constraints.Min;
import javax.validation.constraints.Max;
import javax.validation.constraints.Positive;
import java.util.Map;

/**
* @author generator@TaleLin
* @since 2020-10-18
*/
@RestController
@RequestMapping("/fun/medicinal")
public class MedicinalController {

    @Autowired
    MedicinalService medicinalService;

    @RequestMapping("medicinalAdd")
    public Response medicinalAdd(@RequestBody Map<String,Object> paramMap){
        Response response = new Response();
        medicinalService.insertMedicinal(paramMap);
        return  response.success("添加成功：" + paramMap.get("medicinal_name"));
    }
    @RequestMapping("queryMedicinalList")
    public Response queryMedicinalList(@RequestBody Map<String,Object> paramMap){
        Response response = new Response();
        medicinalService.queryMedicinalList(paramMap);
        return  response.success(medicinalService.queryMedicinalList(paramMap));
    }
    @RequestMapping("queryMedicinalDetailById")
    public Response getMedicinalDetails(@RequestBody Map<String,Object> paramMap){
        Response response = new Response();
        return  response.success(medicinalService.getMedicinalDetails(paramMap));
    }
    @GetMapping("/page")
    public PageResponseVO<MedicinalDO> page(
            @RequestParam(name = "count", required = false, defaultValue = "10")
            @Min(value = 1, message = "{page.count.min}")
            @Max(value = 30, message = "{page.count.max}") Integer count,
            @RequestParam(name = "page", required = false, defaultValue = "0")
            @Min(value = 0, message = "{page.number.min}") Integer page
    ) {
        return null;
    }

}
