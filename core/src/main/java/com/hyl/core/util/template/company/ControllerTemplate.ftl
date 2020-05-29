package com.alib.controller;

/**
* @Auther: hyl
* @Date: ${.now}
* @Description:
*/
@RestController
@RequestMapping("/api/${entityName}")
@Api(tags = "相关接口")
public class ${entity + "Controller"} {
    @Autowired
    private ${entity + "ServiceI"} ${entityName + "ServiceI"};

    @PostMapping("/add")
    @ApiOperation(value = "接口说明")
    @ApiVersion(group = {ApiVersions.MYW_API_260})
    @RequiresAuthentication
    public Response add${entity}(@RequestBody @Validated ${entity + "Qry"} qry) {
        return ${entityName + "ServiceI"}.add${entity}(qry);
    }

    @PostMapping("/add")
    @ApiOperation(value = "接口说明")
    @ApiVersion(group = {ApiVersions.MYW_API_260})
    public Response add${entity}(@RequestBody @Validated ${entity + "Cmd"} cmd) {
        return ${entityName + "ServiceI"}.query${entity}(cmd);
    }
}
