package com.hbicc.cloud.service.task;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.hbicc.cloud.service.utils.MyUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.lang.Console;
import cn.hutool.json.JSONUtil;
import javax.annotation.Resource;
import java.util.List;
// @Slf4j
@Component
public class MyTask {
    // @Resource
    // private IDefaultActivityService defaultActivityService;
    // @Value("${server.is-main}")
    // private Boolean isMain;
    /**
     * 定时任务测试
     */
    @Scheduled(cron = "* * * * * *")
    public void testTask() {
    }
}
