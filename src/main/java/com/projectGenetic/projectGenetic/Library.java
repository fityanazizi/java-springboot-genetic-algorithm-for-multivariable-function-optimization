/**
 *
 * @author fityan
 */
package com.projectGenetic.projectGenetic;

public class Library {
    private static int numOfVariables;
    private static String funcType;
    public static void setNumOfVariables(int a){
        numOfVariables = a;
    }
    public static int getNumOfVariables(){
        return numOfVariables;
    }
    public static void setFuncType(String name){
        funcType = name;
    }
    public static String getFuncType(){
        return funcType;
    }
}
