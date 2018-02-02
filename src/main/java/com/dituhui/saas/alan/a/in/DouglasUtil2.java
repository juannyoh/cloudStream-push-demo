package com.dituhui.saas.alan.a.in;

import java.io.FileInputStream;
import java.text.DecimalFormat;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Lists;


/**
 * 点抽稀工具
 *
 */
class DouglasUtil2 {
	
	private static DecimalFormat xyDecimalFormat = new DecimalFormat("###.#");
	
	List<Point> points;
	Double tolerance;
	
	List<Integer> indexPoints=Lists.newArrayList();
	
	private double D=1d;
	
	public DouglasUtil2(List<Point> points,Double tolerance) {
		this.points=points;
		this.D=tolerance;
	}
	
    public static List<Point> compress(List<Point> points,Double tolerance){
    	List<Point> resultPointList=Lists.newArrayList();
    	if (points.size() < 20 || tolerance == 0) return points; //小于3个点时不抽稀，因为1个或2个点无法进行抽稀，小于20个点的也不抽
    	  int firstPoint = 0,lastPoint = points.size() - 2; //取开始点与倒数第二个点的下标
    	  List<Integer> pointIndexsToKeep =Lists.newArrayList(); //保存需要点下标的数组

    	  pointIndexsToKeep.add(firstPoint);//开始的点保存
    	  pointIndexsToKeep.add(lastPoint); //结束的点保存
    	  pointIndexsToKeep.add(lastPoint-1); //结束的点保存

    	  while ((points.get(firstPoint).getX() == points.get(lastPoint).getX()) && ((points.get(firstPoint).getY() == points.get(lastPoint).getY()))) { 
    	    //处理闭合情况，闭合时，强制断开
    	    lastPoint--;
    	  }

    	  reduce(points, firstPoint, lastPoint, tolerance, pointIndexsToKeep); //抽稀

    	  for (int i = 0; i < pointIndexsToKeep.size(); i++) {
    		  resultPointList.add(points.get(pointIndexsToKeep.get(i)));
    	  }

    	  return resultPointList;
    }
	
    /**
     *  抽稀处理
     * @method reduce
     * @param {Array} points  待抽稀的数组
     * @param {Number} firstPoint  起点
     * @param {Number} lastPoint 终点
     * @param {Number} tolerance 取样临界值
     * @param {Array} pointIndexsToKeep 保留点下标的数组
     */
    private static void  reduce(List<Point> points,int firstPointIndex, int lastPointIndex, double tolerance, List<Integer> pointIndexsToKeep) {  
      double  maxDis = 0;int idxFarthest = 0; //定义最大长度及最远点的下标
      for (int i = firstPointIndex; i < lastPointIndex; i++) {
    	  double dis=perpendicularDistance(points.get(firstPointIndex), points.get(lastPointIndex), points.get(i)); //获取当前点到起点与
        if (dis > maxDis) { //保存最远距离
          maxDis = dis;
          idxFarthest = i;
        }
      }
      if (maxDis > tolerance && idxFarthest != 0) { //如果最远距离大于临界值
        pointIndexsToKeep.add(idxFarthest);
        reduce(points, firstPointIndex, idxFarthest, tolerance, pointIndexsToKeep);
        reduce(points, idxFarthest, lastPointIndex, tolerance, pointIndexsToKeep);
      }
    }

    /**
     * 计算给出的comparePoint到beginPoint与endPoint组成的直线的垂直距离
     * @method perpendicularDistance 
     * @param {Object} beginPoint 起始点
     * @param {Object} endPoint 结束点
     * @param {Object} comparePoint 比较点
     */
    private static Double perpendicularDistance(Point beginPoint, Point endPoint, Point comparePoint) {
      double area = Math.abs(0.5 * (beginPoint.getX() * endPoint.getY() + endPoint.getX() * comparePoint.getY() + comparePoint.getX() * beginPoint.getY() -
        endPoint.getX() * beginPoint.getY() - comparePoint.getX()* endPoint.getY() - beginPoint.getX() * comparePoint.getY()));
      double bottom = Math.sqrt(Math.pow(beginPoint.getX() - endPoint.getX(), 2) + Math.pow(beginPoint.getY() - endPoint.getY(), 2));
      double height = area / bottom * 2;
      return height;
    }
    
    
    private static String dealStr(List<Point> pointList){
    	List<String> xyList=Lists.newArrayList();
    	if(CollectionUtils.isNotEmpty(pointList)){
    		for(Point p:pointList){
    			String x=xyDecimalFormat.format(p.getX());
    			String y=xyDecimalFormat.format(p.getY());
    			xyList.add(x+","+y);
    		}
    	}
    	return StringUtils.join(xyList,";");
    }
    
    private void compress2(Point beginPoint,Point endPoint){
    	boolean switchvalue=false;
    	double A = Math.abs((beginPoint.getY()-endPoint.getY()))/
    			 Math.sqrt(Math.pow((beginPoint.getX()-endPoint.getX()), 2)
    					 +Math.pow((beginPoint.getY()-endPoint.getY()), 2));
    	
    	double B = (endPoint.getX()-beginPoint.getX())/
   			 Math.sqrt(Math.pow((beginPoint.getX()-endPoint.getX()), 2)
   					 +Math.pow((beginPoint.getY()-endPoint.getY()), 2));
    	
    	
    	double C = (beginPoint.getX()*endPoint.getY()-endPoint.getX()*beginPoint.getY())/
      			 Math.sqrt(Math.pow((beginPoint.getX()-endPoint.getX()), 2)
      					 +Math.pow((beginPoint.getY()-endPoint.getY()), 2));
    	
    	double d=0,dmax=0;
    	
    	int m=points.indexOf(beginPoint),n=points.indexOf(endPoint);
    	
    	if(n==m+1)return;
    	
    	Point middle=null;
    	
    	List<Double> distans=Lists.newArrayList();
    	for(int i=m+1;i<n;i++){
    		d=Math.abs(A*(points.get(i).getX())+B*(points.get(i).getY())+C)
    				/Math.sqrt(Math.pow(A, 2)+Math.pow(B, 2))
    				;
    		distans.add(d);
    	}
    	
    	dmax=distans.get(0);
    	
    	for(int j=1;j<distans.size();j++){
    		if(distans.get(j)>dmax){
    			dmax=distans.get(j);
    		}
    	}
    	
    	switchvalue=(dmax>D);
    	
    	if(!switchvalue){
    		for(int i=m+1;i<n;i++){
    			indexPoints.add(i);
    		}
    	}else{
    		for(int i=m+1;i<n;i++){
    			if((Math.abs(A*(points.get(i).getX())+B*(points.get(i).getY())+C)
        				/Math.sqrt(Math.pow(A, 2)+Math.pow(B, 2)))==dmax){
    				middle=points.get(i);
    			}
    		}
    		compress2(beginPoint,middle);
    		compress2(middle,endPoint);
    	}
    }
    
    
    public static void main(String[] args) {
		try {
			List<String> lines=IOUtils.readLines(new FileInputStream("E:\\temp\\ldppResult.txt"), "utf-8");
			if(CollectionUtils.isNotEmpty(lines)){
				for(String line:lines){
					JSONObject json=JSONObject.parseObject(line);
					JSONArray pathArr=json.getJSONObject("result").getJSONArray("planedPaths");
					if(null!=pathArr&&pathArr.size()>0){
						for(int i=0;i<pathArr.size();i++){
							List<Point> pointList=JSONArray.parseArray(pathArr.getJSONObject(i).getString("points"), Point.class);
							System.out.println(dealStr(pointList));
							DouglasUtil2 uu=new DouglasUtil2(pointList,1d);
							uu.compress2(uu.points.get(0), uu.points.get(uu.points.size()-1));
//							List<Point> pointResultList=compress(pointList,100d);
							List<Integer> indexPoints=uu.indexPoints;
							for(int x=0;x<indexPoints.size();x++){
								pointList.remove(indexPoints.get(x));
							}
							System.out.println(dealStr(pointList));
						}
					}
				}
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
