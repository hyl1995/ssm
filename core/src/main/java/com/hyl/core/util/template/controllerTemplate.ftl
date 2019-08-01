package com.hyl.client.controller;

import com.hyl.biz.model.Order;
import com.hyl.biz.model.query.OrderQuery;
import com.hyl.biz.service.OrderService;
import com.hyl.core.model.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@RestController
@RequestMapping("${entityName}/")
public class ${entity + "Controller"} {
    @Autowired
    private ${entity + "Service"} ${entityName + "Service"};

    @RequestMapping(value = "add", method = {RequestMethod.POST})
    public Result add(${entity} ${entityName}) {
        Result result = new Result(false);
        if (${entityName + "Service"}.add(${entityName})) {
            result.setSuccess(true);
        }
        return result;
    }

    @RequestMapping(value = "list", method = {RequestMethod.GET})
    public Result list(${entity + "Query"} query) {
        Result result = new Result(true);
        List<${entity}> ${entityName + "s"} = ${entityName + "Service"}.selectByList(query);
        result.setData(${entityName + "s"});
        return result;
    }

    @RequestMapping(value = "update", method = {RequestMethod.POST})
    public Result update(${entity} ${entityName}) {
        Result result = new Result(false);
        if (${entityName + "Service"}.update(${entityName})) {
            result.setSuccess(true);
        }
        return result;
    }

    @RequestMapping(value = "delete", method = RequestMethod.POST)
    public Result delete(Long id) {
        Result result = new Result(false);
        if (${entityName + "Service"}.delete(id)) {
            result.setSuccess(true);
        }
        return result;
    }
}
