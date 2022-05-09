/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.edu.cefsa.gramatica;

import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author rafae
 */
public class Gramatica {
    public static String[] Criar() {
        String[] gramatica = new String[6];
        
        gramatica[0] = "F, +, -";
        gramatica[1] = "6";
        gramatica[2] = "X";
        gramatica[3] = "25";
        gramatica[4] = "X→F+[[X]-X]-F[-FX]+X";
        gramatica[5] = "F→FF";
        
        return gramatica;
    }
    
    public static String[] TrataEntrada(String[] entrada) {
        String[] espelhoGramatica = new String[entrada.length];
        List<String> regras = new LinkedList();
        
        for (int i = 0; i < entrada.length; i++)
            TrataLinha(entrada[i], espelhoGramatica, regras);
        
        return CriaGramatica(espelhoGramatica, regras);
    }
    
    public static String[] ExecutaRegra(String[] gramatica) {
        int qtdRegras = gramatica.length - 4;
        String[][] substituir = new String[qtdRegras][2];

        int qtdVezes = Integer.parseInt(gramatica[1]);
        
        for (int i = 0; i < qtdVezes; i++) {
            qtdRegras = 0;
            for (int j = 4; j < gramatica.length; j++) {
                substituir[qtdRegras] = gramatica[j].split("→");
                
                gramatica[2] = gramatica[2].replaceAll(substituir[qtdRegras][0], substituir[qtdRegras][1]);
                qtdRegras++;
            }
        }

        return gramatica;
    }
    
    private static void TrataLinha(String linhaArquivo, String[] espelhoGramatica, List<String> regras) {
        String[] aux = linhaArquivo.trim().split(":");
        
        if(!aux[0].startsWith("p")) {
            switch(aux[0].trim()){
                case "Σ":
                    espelhoGramatica[0] = aux[1].trim();
                    break;
                case "n":
                    espelhoGramatica[1] = aux[1].trim();
                    break;
                case "ω":
                    espelhoGramatica[2] = aux[1].trim();
                    break;
                case "δ":
                    espelhoGramatica[3] = aux[1].trim().replaceAll("º", "");
                    break;
                default:
                    break;
            }
        }
        else
            regras.add(aux[1].trim().replaceAll(" ", ""));
    }
    
    private static String[] CriaGramatica(String[] gramatica, List<String> regras) {
        String[] retorno = new String[gramatica.length];
        int countList = 0;
        
        for(int i = 0; i < gramatica.length; i++)
            retorno[i] = gramatica[i];
        
        for(int j = gramatica.length-regras.size(); j < gramatica.length; j++) {
            retorno[j] = regras.get(countList);
            countList++;
        }
        
        return retorno;
    }
}
