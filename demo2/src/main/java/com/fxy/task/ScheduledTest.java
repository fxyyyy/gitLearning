package com.fxy.task;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;

import com.fxy.bean.User;
import com.fxy.service.UserService;

@Component
@Controller 
public class ScheduledTest {

	@Autowired  
    private UserService userService; 
	
    private static final Logger logger = LoggerFactory.getLogger(ScheduledTest.class);
    // 每天早八点到晚八点，间隔5分钟执行任务
    //@Scheduled(cron="0 0/5 8-20 * * ?") 
    @Scheduled(cron = "0 34 13 * * ?") //如果你需要在特定的时间执行，就需要用到cron 了
    //这里是在每天的13点30分执行一次
    public void executeUploadTask() {

        // 间隔5分钟,执行工单上传任务              
        Thread current = Thread.currentThread();  
        logger.info("ScheduledTest.executeUploadTask 定时任务:"+current.getId() + ",name:"+current.getName());
        
        //每隔5分钟清空一次miss_number、allow_time
        int intUpdateUser = userService.updateUserMissTimeAllowTime(); 
        logger.info("intUpdateUser:"+intUpdateUser);
        
    }

}
