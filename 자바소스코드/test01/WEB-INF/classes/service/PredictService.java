package service;

import java.util.ArrayList;

public class PredictService {
	//파이썬으로 얻은 데이터값만 저장해놓고 넘겨줌
	public ArrayList<String> getWin(int choice){
		ArrayList<String> winA = new ArrayList<>();
		ArrayList<String> winB = new ArrayList<>();
		ArrayList<String> winC = new ArrayList<>();
		ArrayList<String> result = new ArrayList<>();
		
		winA.add("H");
		winA.add("A");
		winA.add("H");
		winA.add("A");
		winA.add("H");
		winA.add("H");
		winA.add("D");
		winA.add("A");
		winA.add("D");
		winA.add("A");
		
		winB.add("H");
		winB.add("A");
		winB.add("H");
		winB.add("A");
		winB.add("H");
		winB.add("H");
		winB.add("H");
		winB.add("D");
		winB.add("A");
		winB.add("A");
		
		winC.add("H");
		winC.add("A");
		winC.add("H");
		winC.add("A");
		winC.add("H");
		winC.add("H");
		winC.add("H");
		winC.add("A");
		winC.add("A");
		winC.add("A");
		
		
		if(choice == 1) {
			result.add(winA.get(choice-1));
			result.add(winB.get(choice-1));
			result.add(winC.get(choice-1));
		}else if(choice ==2) {
			result.add(winA.get(choice-1));
			result.add(winB.get(choice-1));
			result.add(winC.get(choice-1));
		}else if(choice == 3) {
			result.add(winA.get(choice-1));
			result.add(winB.get(choice-1));
			result.add(winC.get(choice-1));
		}else if(choice == 4) {
			result.add(winA.get(choice-1));
			result.add(winB.get(choice-1));
			result.add(winC.get(choice-1));
		}else if(choice == 5) {
			result.add(winA.get(choice-1));
			result.add(winB.get(choice-1));
			result.add(winC.get(choice-1));
		}else if(choice ==6) {
			result.add(winA.get(choice-1));
			result.add(winB.get(choice-1));
			result.add(winC.get(choice-1));
		}else if(choice == 7) {
			result.add(winA.get(choice-1));
			result.add(winB.get(choice-1));
			result.add(winC.get(choice-1));
		}else if(choice == 8) {
			result.add(winA.get(choice-1));
			result.add(winB.get(choice-1));
			result.add(winC.get(choice-1));
		}else if(choice == 9) {
			result.add(winA.get(choice-1));
			result.add(winB.get(choice-1));
			result.add(winC.get(choice-1));
		}else if(choice == 10) {
			result.add(winA.get(choice-1));
			result.add(winB.get(choice-1));
			result.add(winC.get(choice-1));
		}else {
			return null;
		}
		return result;
	}
	
	public double[][] getRate(int choice){
		double[][] result = new double[3][3];
		
		if(choice==1) {
			double[] num = {28.205128,23.076923,48.717949};
			double[] num2 = {32.175594,24.741196,43.083206};
			double[] num3 = {36.239500,24.152092,39.608409};
			result[0][0] = num[0];
			result[0][1] = num[1];
			result[0][2] = num[2];
			
			result[1][0] = num2[0];
			result[1][1] = num2[1];
			result[1][2] = num2[2];
			
			result[2][0] = num3[0];
			result[2][1] = num3[1];
			result[2][2] = num3[2];
			
		}else if(choice==2) {
			double[] num = {41.025641,30.769231,28.205128};
			double[] num2 = {41.865429,27.119192,31.015375};
			double[] num3 = {44.974916,26.612340,28.412744};
			result[0][0] = num[0];
			result[0][1] = num[1];
			result[0][2] = num[2];
			
			result[1][0] = num2[0];
			result[1][1] = num2[1];
			result[1][2] = num2[2];
			
			result[2][0] = num3[0];
			result[2][1] = num3[1];
			result[2][2] = num3[2];
		}else if(choice==3) {
			double[] num = {7.692308,23.076923,69.230769};
			double[] num2 = {22.086773,23.550308,54.362923};
			double[] num3 = {20.066839,21.503419,58.429742};
			result[0][0] = num[0];
			result[0][1] = num[1];
			result[0][2] = num[2];
			
			result[1][0] = num2[0];
			result[1][1] = num2[1];
			result[1][2] = num2[2];
			
			result[2][0] = num3[0];
			result[2][1] = num3[1];
			result[2][2] = num3[2];
		}else if(choice==4) {
			double[] num = {38.461538,30.769231,30.769231};
			double[] num2 = {49.434525,24.199776,26.365704};
			double[] num3 = {46.356940,27.611160,26.031899};
			result[0][0] = num[0];
			result[0][1] = num[1];
			result[0][2] = num[2];
			
			result[1][0] = num2[0];
			result[1][1] = num2[1];
			result[1][2] = num2[2];
			
			result[2][0] = num3[0];
			result[2][1] = num3[1];
			result[2][2] = num3[2];
		}else if(choice==5) {
			double[] num = {30.769231,33.333333,35.897436};
			double[] num2 = {33.443329,21.486612,45.070065};
			double[] num3 = {25.451445,25.515463,49.033092};
			result[0][0] = num[0];
			result[0][1] = num[1];
			result[0][2] = num[2];
			
			result[1][0] = num2[0];
			result[1][1] = num2[1];
			result[1][2] = num2[2];
			
			result[2][0] = num3[0];
			result[2][1] = num3[1];
			result[2][2] = num3[2];
		}else if(choice==6) {
			double[] num = {10.256410,15.384615,74.358974};
			double[] num2 = {7.714274,14.772815,77.512909};
			double[] num3 = {6.422436,19.001200,74.576363};
			result[0][0] = num[0];
			result[0][1] = num[1];
			result[0][2] = num[2];
			
			result[1][0] = num2[0];
			result[1][1] = num2[1];
			result[1][2] = num2[2];
			
			result[2][0] = num3[0];
			result[2][1] = num3[1];
			result[2][2] = num3[2];
		}else if(choice==7) {
			double[] num = {17.948718,41.025641,41.025641};
			double[] num2 = {18.634111,21.641937,59.723957};
			double[] num3 = {33.031495,20.793237,46.175268};
			result[0][0] = num[0];
			result[0][1] = num[1];
			result[0][2] = num[2];
			
			result[1][0] = num2[0];
			result[1][1] = num2[1];
			result[1][2] = num2[2];
			
			result[2][0] = num3[0];
			result[2][1] = num3[1];
			result[2][2] = num3[2];
		}else if(choice==8) {
			double[] num = {48.717949,30.769231,20.512821};
			double[] num2 = {32.087181,34.906406,33.006413};
			double[] num3 = {37.286909,27.739115,34.973976};
			result[0][0] = num[0];
			result[0][1] = num[1];
			result[0][2] = num[2];
			
			result[1][0] = num2[0];
			result[1][1] = num2[1];
			result[1][2] = num2[2];
			
			result[2][0] = num3[0];
			result[2][1] = num3[1];
			result[2][2] = num3[2];
		}else if(choice==9) {
			double[] num = {35.897436,41.025641,23.076923};
			double[] num2 = {39.196114,28.182007,32.621880};
			double[] num3 = {41.064539,26.543013,32.392448};
			result[0][0] = num[0];
			result[0][1] = num[1];
			result[0][2] = num[2];
			
			result[1][0] = num2[0];
			result[1][1] = num2[1];
			result[1][2] = num2[2];
			
			result[2][0] = num3[0];
			result[2][1] = num3[1];
			result[2][2] = num3[2];
		}else if(choice==10) {
			double[] num = {56.410256,15.384615,28.205128};
			double[] num2 = {54.700226,19.008469,26.291311};
			double[] num3 = {49.752265,26.423626,23.824110};
			result[0][0] = num[0];
			result[0][1] = num[1];
			result[0][2] = num[2];
			
			result[1][0] = num2[0];
			result[1][1] = num2[1];
			result[1][2] = num2[2];
			
			result[2][0] = num3[0];
			result[2][1] = num3[1];
			result[2][2] = num3[2];
		}
		return result;
		
	}
	
	

}
