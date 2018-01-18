package com.dituhui.saas.alan.a.in;

import javax.annotation.Resource;

import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Sink;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

@EnableBinding(Sink.class)
@Component
public class MsgSink {

	@Resource
	private RedisTemplate<String, String> redisTemplate;
	
	@Resource
	private SendService sendService;
	
	@StreamListener(Sink.INPUT)
	public void messageSink(Object payload) {
		System.out.println("Received: " + payload);
		
		String message=payload.toString();
		
		String msg[]=message.split(",");
		try {
			String ips=redisTemplate.opsForValue().get(msg[0]);
			if(ips.equals("10.25.11.231")){
				sendService.sendMessage1(msg[1]);
			}else{
				sendService.sendMessage2(msg[1]);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
	}
}
