package com.dituhui.saas.alan.a.in;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;

@EnableBinding(CustomSource.class)
@Component
public class SendService {

	@Autowired
	private CustomSource source;

	public void sendMessage1(String msg) {
		try {
			source.output1().send(MessageBuilder.withPayload(msg).build());
			System.out.println("sendMessage1:"+msg);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void sendMessage2(String msg) {
		try {
			source.output2().send(MessageBuilder.withPayload(msg).build());
			System.out.println("sendMessage2:"+msg);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
