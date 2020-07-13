/**
 *
 * @author fityan
 */
package com.projectGenetic.projectGenetic;
public class Solution {
    //inisiasi array x dan nilai fitness
    public double x[] = new double[Library.getNumOfVariables()];
    double fitness = 0;
    String funcType = String.valueOf(Library.getFuncType());
    //constructor, generate x dengan bilangan random
    public Solution(){
        //range bilangan sesuai dengan batasan dalam fungsi
        switch(funcType){
            case "Sphere":
                for(int i = 0; i < x.length; i++){
                    x[i] = Math.random()*200-100;
                }
                break;
            case "Csendes":
                for(int i = 0; i < x.length; i++){
                    x[i] = Math.random()*2-1;
                }
                break;
            case "Alpine":
                for(int i = 0; i < x.length; i++){
                    x[i] = Math.random()*20-10;
                }
                break;
            case "Exponential":
                for(int i = 0; i < x.length; i++){
                    x[i] = Math.random()*2-1;
                }
                break;
            case "SumSquares":
                for(int i = 0; i < x.length; i++){
                    x[i] = Math.random()*20-10;
                }
                break;
            case "SchumerSteiglitz":
                for(int i = 0; i < x.length; i++){
                    x[i] = Math.random()*200-100;
                }
                break;
            case "Quintic":
                for(int i = 0; i < x.length; i++){
                    x[i] = Math.random()*20-10;
                }
                break;          
        }          
    }
    
    //inisiasi kromosom baru dengan mendapatkan kromosom lain
    //constructor, generate x sesuai dengan array a
    public Solution(double[] a, double f){
        for(int i = 0; i < x.length; i++){
            x[i]=a[i];
        }
        fitness = f;
    }
    
    //function untuk mengubah isi kromosom
    //hitung nilai fitness ketika mengubah isi
    public void setX(int i, double a){
        double b = x[i];
        x[i]=a;
        switch(funcType){
            case "Sphere":
                    fitness += Math.pow(a, 2) - Math.pow(b, 2);
                break;
            case "Csendes":
                    fitness += Math.pow(a, 6)*(2 + Math.sin(1/a)) - Math.pow(b, 6)*(2 + Math.sin(1/b));
                break;
            case "Alpine":
                    fitness += Math.abs(a*Math.sin(a) + 0.1*a) - Math.abs(b*Math.sin(b) + 0.1*b);
                break;
            case "Exponential":
                    fitness *= -Math.exp(-0.5*Math.pow(a, 2))/-Math.exp(-0.5*Math.pow(b, 2));
                break;
            case "SumSquares":
                    fitness += i*Math.pow(a, 2) - i*Math.pow(b, 2);
                break;
            case "SchumerSteiglitz":
                    fitness += Math.pow(a, 4) - Math.pow(b, 4); 
                break;
            case "Quintic":
                    fitness += Math.abs(Math.pow(a, 5) - 3*Math.pow(a, 4) + 4*Math.pow(a, 3) + 2*Math.pow(a, 2) - 10*a - 4) - Math.abs(Math.pow(b, 5) - 3*Math.pow(b, 4) + 4*Math.pow(b, 3) + 2*Math.pow(b, 2) - 10*b - 4);
                break; 
        }
    }
    
    //function untuk mendapatkan kromosom
    public double[] getX(){
        return x;
    }
    
    //function untuk mendapatkan nilai tertentu dalam kromosom
    public double getX(int i){
        return x[i];
    }
    
    //function untuk evaluasi fungsi
    public double f(){
        double result = 0;
        switch(funcType){
            case "Sphere":
                for(int i = 0; i < x.length; i++){
                    result += Math.pow(x[i], 2);
                }
                break;
            case "Csendes":
                for(int i = 0; i < x.length; i++){
                    result = result + Math.pow(x[i], 6)*(2 + Math.sin(1/x[i]));
                }
                break;
            case "Alpine":
                for(int i = 0; i < x.length; i++){
                    result += Math.abs(x[i]*Math.sin(x[i]) + 0.1*x[i]);
                }
                break;
            case "Exponential":
                double a = 0;
                for(int i = 0; i < x.length; i++){
                    a += Math.pow(x[i], 2); 
                }
                result = -Math.exp(-0.5*a);
                break;
            case "SumSquares":
                for(int i = 0; i < x.length; i++){
                    result = result + i*Math.pow(x[i], 2);
                }
                break;
            case "SchumerSteiglitz":
                for(int i = 0; i < x.length; i++){
                    result = result + Math.pow(x[i], 4);
                }
                break;
            case "Quintic":
                for(int i = 0; i < x.length; i++){
                    result = result + Math.abs(Math.pow(x[i], 5) - 3*Math.pow(x[i], 4) + 4*Math.pow(x[i], 3) + 2*Math.pow(x[i], 2) - 10*x[i] - 4);
                }
                break;   
        } 
        return result;
    }
    
    //function untuk mendapatkan nilai fitness
    //jika fitness nol, maka dibawa ke fungsi evaluasi
    public double getFitness(){
        if(fitness==0){
            fitness = f();
        }
        return fitness;
    }
}
