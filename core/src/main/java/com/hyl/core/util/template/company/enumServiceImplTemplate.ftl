
import com.alibaba.dubbo.config.annotation.Service;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

/**
 * Copyright glbpay.com
 * Created by Xu Qihua on 2019/3/31.
 * Descripton: 枚举公共类
 */
@Service
@Component
public class CommonEnumServiceImpl implements CommonEnumService {
    <#if enumList??>
    <#list enumList as e>
    /**
     * ${e.memo}
     * @return
     */
    public List<${e.name}> ${"get" + e.name + "s"}() {
        return Arrays.asList(${e.name}.values());
    }

    </#list>
    </#if>
}
