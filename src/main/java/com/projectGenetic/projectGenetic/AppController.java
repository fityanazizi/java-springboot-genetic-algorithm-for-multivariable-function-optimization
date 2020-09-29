/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.projectGenetic.projectGenetic;

/**
 *
 * @author fityan
 */
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/ajax")
public class AppController extends HttpServlet {
    private static final long serialVersionUID = 1L;
    
    public AppController() {
        super();
    }
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //genetic  
        genetic(request, response);
    }
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response); 
    }
    public void genetic(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        String var = request.getParameter("var");
        String type = request.getParameter("type");
        int numOfVariables = 0;
        numOfVariables = Integer.parseInt(var);
        String funcType = String.valueOf(type);
        Library lib = new Library();
        lib.setNumOfVariables(numOfVariables);
        lib.setFuncType(funcType);
        int numOfPopulations = 50;
        int numOfIterations = 1000;
            
        long start = System.currentTimeMillis();
        Population pop = new Population(numOfPopulations, true);
        GeneticAlgorithm ga = new GeneticAlgorithm();
        for(int i = 0; i < numOfIterations; i++){
            pop = ga.evolve(pop);
        }
        long end = System.currentTimeMillis();
        float sec = (end - start) / 1000F;
        //main
        response.getWriter().print("Waktu yang ditempuh : " +sec+ " detik"+"<br>");
        response.getWriter().print("Fitness/Optimum di : " +pop.getFittest().getFitness()+ "<br><br>");
        response.getWriter().print("Solusi : " + "<br>");
        for(int i = 0; i < numOfVariables; i++){
            response.getWriter().print(pop.getFittest().x[i]+"<br><br>");
        }
    }
}