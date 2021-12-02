package com.sdu.zhiji.dao;

public class Result {

    public static String[][] resKey
            = {{"E","I"}, {"S","N"}, {"T","F"}, {"J","P"}};
    public int[][] resValue;
    public String type;
    public int page;

    public Result() {
        resValue = new int[4][2];
        for(int i=0; i<resValue.length; i++){
            for(int j=0; j<resValue[i].length; j++){
                resValue[i][j] = 0;
            }
        }
    }
}
