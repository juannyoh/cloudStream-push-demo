//package com.dituhui.saas.service.pathplan;
//
//import java.util.Arrays;
//import java.util.Date;
//import java.util.Map;
//
//import javax.annotation.Resource;
//
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
//import org.springframework.context.annotation.ImportResource;
//import org.springframework.test.context.junit4.SpringRunner;
//
//import com.dituhui.saas.framework.user.SaasUser;
//import com.dituhui.saas.service.api.elasticsearch.IExtendColumnService;
//import com.dituhui.saas.service.api.layer.enums.ServiceTypeEnum;
//import com.dituhui.saas.service.api.pojo.DemandPoint;
//import com.dituhui.saas.service.api.pojo.ExtendColumnRow;
//import com.dituhui.saas.service.api.pojo.PageResult;
//import com.dituhui.saas.service.api.pojo.VehicleInfo;
//import com.dituhui.saas.service.api.util.APICoordType;
//import com.dituhui.saas.service.api.vehicle.IVehicleInfoService;
//import com.google.common.collect.Maps;
////import com.dituhui.saas.service.pathplan.service.TestKafkaService;
//
//@SpringBootTest(webEnvironment=WebEnvironment.RANDOM_PORT)
//@RunWith(SpringRunner.class)
//@ImportResource({"classpath:pathplan-dubbo.xml"})
//public class AppTest {
//	
////	@Resource
////	TestKafkaService testKafkaService;
//	
////	@Resource
////	PathPlanApiUtils pathPlanApiUtils;
//	
//	@Resource
//	IVehicleInfoService vehicleInfoService;
//	
//	@Resource
//	IExtendColumnService extendColumnService;
//	
//	SaasUser user=new SaasUser();
//	
//	@Test
//	public void test2(){
////		addVehicle();
////		searchVe();
////		deleteField();
//	}
//	
//	private void deleteField(){
//		user.setTeamID("8aaffff35caea804015caee805cc0018");
//		boolean flag=extendColumnService.delExtendColumnMetadata(user, user.getTeamID(), ServiceTypeEnum.Vehicle, "最大行驶");
//		boolean flag2=extendColumnService.delExtendColumns(user, user.getTeamID(), ServiceTypeEnum.Vehicle, Arrays.asList("a2f318640a7145aeb3ef91f55f2c068a"));
//		System.out.println(flag+","+flag2);
//	}
//	
//	private void searchVe(){
//		PageResult<ExtendColumnRow>  row=this.vehicleInfoService.getVehiclesByPage(user, 1, 10, null, null);
//		System.out.println(row);
//	}
//	
//	
//	private void addVehicle(){
//		user.setTeamID("8aaffff35caea804015caee805cc0018");
//		Map<String,Object> infoMap=Maps.newHashMap();
//		infoMap.put("车牌号", "aa06");
//		infoMap.put("类型", "货车");
//		infoMap.put("装载体积", "07");
//		infoMap.put("载重", "07");
//		infoMap.put("出发时间", "02:00:00");
//		infoMap.put("返回时间", "23:00:00");
//		infoMap.put("最大行驶(小时)", "170");
//		infoMap.put("状态", "可用");
//		infoMap.put("创建时间", new Date());
//		vehicleInfoService.saveVehicle(user, infoMap);
//	}
//	
////	@Test
//	public void test(){
//		
//		String ak="4e41c1eefa7946f4bab0b66ebc664d31";
//		
//		DemandPoint point=new DemandPoint();
//		point.setDemands("1");
//		point.setStartTime("01:00:00");
//		point.setEndTime("22:00:00");
//		point.setPoint("11585411.72,3592028.62");
//		
////		DemandPoint point1=new DemandPoint();
////		point1.setDemands("1");
////		point1.setStartTime("06:00:00");
////		point1.setEndTime("20:00:00");
////		point1.setPoint("13504784.1,3671490.0");
//		
//		VehicleInfo info=new VehicleInfo();
//		info.setLoadWeights("4000");
//		info.setCost("1000");
//		info.setStartTime("00:00:00");
//		info.setEndTime("23:00:00");
//		
////		Map<String, Object> map1=pathPlanApiUtils.ldpp(ak, "13518294.762069613,3636083.0706385877", APICoordType.gcj02mc
////				, Arrays.asList(point), Arrays.asList(info), true, null, null, 
////				null, null,true);
//		
////		Map<String, Object> map2=pathPlanApiUtils.mcpp(ak, "13503936.9,3670440.0", "13504784.1,3671490.0", false, APICoordType.gcj02mc, true, null, null, null);
//		
////		Map<String, Object> map3=pathPlanApiUtils.scpp(ak, "13503936.9,3670440.0", "13504784.1,3671490.0", null, APICoordType.gcj02mc, true, null, null, null);
//		
////		System.out.println(map1);
////		System.out.println(map2);
////		System.out.println(map3);
//	}
//	
//	
////	@Test
////	public void testKafka(){
////		String topic="test4";
////		for(int i=0;true;i++){
////			testKafkaService.sendMessage(topic,"ss"+i+"test","vv"+i+"test");
////		}
////	}
//}
