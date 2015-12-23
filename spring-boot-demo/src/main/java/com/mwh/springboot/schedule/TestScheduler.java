package com.mwh.springboot.schedule;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class TestScheduler implements InitializingBean {
	private static Logger log = LoggerFactory.getLogger(TestScheduler.class);

	@Scheduled(cron = "${boot.schedule1.cron}")
	public void shedule1() {
		//TODO excute task
	}

	@Scheduled(cron = "${boot.schedule2.cron}")
	public void shedule2() {
		//TODO excute task
	}

	

	@Override
	public void afterPropertiesSet() throws Exception {
		//TODO init task
	}

	
}