/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.edu.cefsa.arquivos;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.nio.charset.StandardCharsets;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author rafae
 */
public class Arquivos {
    public static String[] LerArquivo(String nomeArquivo) {
        try (BufferedReader br = new BufferedReader(new FileReader(nomeArquivo, StandardCharsets.UTF_8))){
            String line = br.readLine();
            List<String> lista = new LinkedList();

            while (line != null) {
                String aux = line.trim();
                lista.add(aux);
                line = br.readLine();
            }           
            String[] array = new String[lista.size()];
            
            return lista.toArray(array);
        } catch (Exception erro) {            
            System.out.println("Erro: " +erro.toString());
            return null;
        }
    }
    
    public static void EscreverArquivo(String dados) {
        try (FileWriter myWriter = new FileWriter("saida.html")) {
                myWriter.write(dados);
        } catch (Exception erro) {                  
            System.out.println("Erro: " +erro.toString());
        }
    }
}
