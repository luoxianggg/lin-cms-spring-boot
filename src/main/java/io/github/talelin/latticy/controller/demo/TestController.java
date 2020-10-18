package io.github.talelin.latticy.controller.demo;

import io.github.talelin.latticy.model.Response;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/demo")
public class TestController {

    @RequestMapping("test")
    public Response medicinalAdd(@RequestBody Map<String,Object> paramMap){
        Response response = new Response();
        return  response.success("hello world" + paramMap.get("medicinal_name"));
    }
}
