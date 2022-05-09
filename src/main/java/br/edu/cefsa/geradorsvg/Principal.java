/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package br.edu.cefsa.geradorsvg;

import br.edu.cefsa.arquivos.Arquivos;
import br.edu.cefsa.gramatica.Gramatica;

import java.util.Stack;

/**
 *
 * @author rafae
 */
public class Principal {

    public static double posicaoX = 25;
    public static double posicaoY = 1500;
    public static double andarQtdX = 5;
    public static double andarQtdY = 0;
    public static double distancia = 5;
    public static double angulo = 25;
    public static double anguloAtual = -60;
    public static Stack<Double[]> pilhaRetorno = new Stack();
    
    
    //O padrão para o array gramática utilizado foi:
    //Index [0] → Alfabeto da Linguagem;
    //Index [1] → Passos, etapas;
    //Index [2] → Axioma, condição inicial;
    //Index [3] → Ângulo;
    //Index [4] e adiante → Regras de produção
    //
    //O ângulo inicial de execução é -60º.
    public static void main(String[] args) {
        String[] entrada = //CriaGramatica();
        Arquivos.LerArquivo("input.txt");
        
        String[] gramatica = Gramatica.TrataEntrada(entrada);
        //Se o arquivo estiver vazio, cria-se uma gramática nova.
        if(gramatica.length == 0)
            Gramatica.Criar();

        angulo = Integer.parseInt(gramatica[3]);
        Gramatica.ExecutaRegra(gramatica);
        AtualizaAngulo();
        String html = GeraHTML(gramatica);
        
        Arquivos.EscreverArquivo(html);
    }
    
    public static String GeraHTML(String[] gramatica) {
        String html = "<!DOCTYPE html>\n" +"<html>\n" +"<body>";
        html += GeraSVG(gramatica);
        html += "</body>\n" +"</html>";
        
        return html;
    }
    
    public static String[] ExecutaRegra(String[] gramatica) {
        int qtdRegras = gramatica.length - 4;
        String[][] substituir = new String[qtdRegras][2];

        int qtdVezes = Integer.parseInt(gramatica[1]);
        angulo = Integer.parseInt(gramatica[3]);
        
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

    public static String GeraSVG(String[] gramatica) {
        String svg = "<svg height=\"1500\" width=\"10000\" style=\"stroke:rgb(255,0,0);stroke-width:2\">";
        
        for(char instrucao : gramatica[2].toCharArray())
            svg += TraduzComando(instrucao);
        
        svg += "</svg>";
        return svg;
    }
    
    public static String TraduzComando(char comando) {
        String svgTraduzido = "";
        switch (comando) {
            case 'F':
                svgTraduzido += "<line x1=\"" +posicaoX +"\" y1=\"" +posicaoY +"\" ";
                posicaoX += andarQtdX;
                posicaoY += andarQtdY;
                svgTraduzido += " x2=\"" +posicaoX +"\" y2=\"" +posicaoY +"\"  />";
                
                return svgTraduzido;
            case '-':
                anguloAtual += angulo;
                if (anguloAtual >= 360) {
                    anguloAtual -= 360;
                }
                AtualizaAngulo();
                
                return svgTraduzido;
            case '+':
                anguloAtual -= angulo;
                if (anguloAtual < 0) {
                    anguloAtual += 360;
                }
                AtualizaAngulo();
                
                return svgTraduzido;
            case 'f':
                posicaoX += andarQtdX;
                posicaoY += andarQtdY;
                
                return svgTraduzido;
            case '[':
                var posicaoAtual = new Double[]{posicaoX, posicaoY, anguloAtual};
                pilhaRetorno.add(posicaoAtual);
                
                return svgTraduzido;
            case ']':
                Double[] posicaoRetorno = pilhaRetorno.pop();
                
                posicaoX = posicaoRetorno[0];
                posicaoY = posicaoRetorno[1];
                anguloAtual = posicaoRetorno[2];
                
                return svgTraduzido;
            default:
                
                return svgTraduzido;
        }
    }
    
    public static void AtualizaAngulo() {
        double radianos = ConvertGrauRadiano(anguloAtual);
        
        andarQtdX = distancia*Math.cos(radianos);
        andarQtdY = distancia*Math.sin(radianos);
    }
    
    public static double ConvertGrauRadiano(double angulo) {
        return (angulo*Math.PI)/180;
    }
    
}
