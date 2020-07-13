/**
 *
 * @author fityan
 */
package com.projectGenetic.projectGenetic;

public class Population {
    Solution[] solution;
    
    //Constructor Population
    public Population(int numberOfSolutions, boolean initialization){
        solution = new Solution[numberOfSolutions];
        //jika inisiasi, generate populasi berisi kromosom
        if(initialization){
            for(int i = 0; i<numberOfSolutions; i++){
                Solution newSolution = new Solution();
                saveSolution(i,newSolution);
            }
        }
    }
    
    //function untuk menyimpan kromosom kedalam populasi
    public void saveSolution(int index, Solution newSolution){
        solution[index] = newSolution;
    }
    
    //function untuk mendapatkan kromosom
    public Solution getSolution(int index){
        return solution[index];
    }
    
    //function untuk mendapatkan kromosom terbaik dalam populasi
    public Solution getFittest(){
        Solution fittest = solution[0];
        for(int i = 0; i<solution.length; i++){
            if(getSolution(i).getFitness()<fittest.getFitness()){
                fittest = getSolution(i);
            }
        }
        return fittest;
    }
    
    public int popSize(){
        return solution.length;
    }
    
}
