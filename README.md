# GrammerToSVG

Análise
- Definir uma gramática em arquivo texto
- Ler arquivo com a definição da gramática
- Aplicar as regras de produção, recursivamente → Gerar o string final
- Definir a semântica para cada elemento do alfabeto da sua linguagem:
    + - Vira a direita
    - - Vira a esquerda
    F - Para frente desenhando
    f - Para frente sem desenhar
    [ - Pilha... etc...
    
Gramática da linguagem:

Σ   ☞ Alfabeto da linguagem  
n   ☞ Passos, etapas  
ω   ☞ Axioma, condição inicial  
δ   ☞ Angulo  
pX  ☞ Regras de produção  

A saída vem configurada por default com a seguinte imagem:  
- Σ : F, +, -  
- n : 6  
- ω : X  
- δ : 25  
- p1 : X→F+[[X]-X]-F[-FX]+X  
- p2: F→FF  

![alt text](https://upload.wikimedia.org/wikipedia/commons/4/4b/Fractal_Farn.gif)
