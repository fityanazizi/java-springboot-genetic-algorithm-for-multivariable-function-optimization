/**
 *
 * @author fityan
 */
package com.projectGenetic.projectGenetic;

public class GeneticAlgorithm {
    private static final double mutationrate = 0.05;
    private static final int tournamentsize = 5;
    private static final boolean elitism = true;
    
    //crossover
    public static Solution crossover(Solution parent1, Solution parent2){
        //membuat kromosom anak, ambil dari parent1
        Solution child1 = new Solution(parent1.getX(), parent1.getFitness());
        for(int i = 0; i < Library.getNumOfVariables(); i++){
            //simpan nilai fitness sebelum crossover untuk dibandingkan
            double f = child1.getFitness();
            //ubah isi kromosom ke-i
            child1.setX(i, (parent1.getX(i) + parent2.getX(i))/2);
            //jika lebih buruk, ubah seperti semula
            if(child1.getFitness() > f){
                child1.setX(i, parent1.getX(i));
            }
        }
        return child1;
    }
    //mutasi
    public static void mutation(Solution solution){
        //membuat kromosom dari kromosom yang akan dimutasi, untuk mengembalikan nilai jika lebih buruk
        Solution solution2 = new Solution(solution.getX(), solution.getFitness());
        for(int i = 0; i < Library.getNumOfVariables(); i++){
            //simpan nilai fitness sebelum mutasi untuk dibandingkan
            double f = solution.getFitness();
            //ubah isi kromosom ke-i
            solution.setX(i, solution.getX(i)+Math.random()-0.5);
            //jika lebih buruk, ubah seperti semula
            if(solution.getFitness() > f){
                solution.setX(i, solution2.getX(i));
            }
        }
    }
    //seleksi turnamen
    public static Solution tournament(Population pop){
        //geneate populasi baru sebanyak tournamentsize
        Population tournamentselection = new Population(tournamentsize, false);
        for(int i = 0; i < tournamentsize; i++){
            //declare random untuk mendapatkan nilai acak untuk index getSolution
            int random = (int)(Math.random()*pop.popSize());
            //simpan kromosom pada index random ke populasi tournamentselection
            tournamentselection.saveSolution(i, pop.getSolution(random));
        }
        //dapatkan nilai fittest
        Solution fittest = tournamentselection.getFittest();
        return fittest;
    }
    //evolusi
    public static Population evolve(Population pop){
        //generate populasi baru sebanyak jumlah populasi
        //akan dipakai untuk menyimpan hasil proses evolusi
        Population newpopulation = new Population(pop.popSize(), false);
        int elitismoffset = 0;
        //elitisim true-> simpan kromosom terbaik dari populasi yang akan dievolusi ke populasi baru
        if(elitism){
            newpopulation.saveSolution(0, pop.getFittest());
            elitismoffset = 1;
        }
        //lakukan crossover ke seluruh kromosom
        for(int i = elitismoffset; i < pop.popSize(); i++){
            //parent1 diambil dari kromosom ke-i
            Solution parent1 = pop.getSolution(i);
            //parent2 diambil dari seleksi turnamen
            Solution parent2 = tournament(pop);
            //crossover
            Solution child = crossover(parent1, parent2);
            //simpan hasil kromosom
            newpopulation.saveSolution(i, child);
        }
        //lakukan mutasi ke seluruh kromosom pada populasi baru
        for(int i = elitismoffset; i < pop.popSize(); i++){
            if(Math.random() < mutationrate){
                mutation(newpopulation.getSolution(i)); 
            }
        }
        return newpopulation;
    }
}
