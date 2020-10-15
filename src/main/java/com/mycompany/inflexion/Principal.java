/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.inflexion;

/**
 *
 * @author Paulina
 */
public class Principal {
    private static int posiciones[] = new int [7]; 
    public static void main(String[] args) {
        System.out.println("FUNCION: f(x)=((x^4)/12)-((x^3)/2)+(x^2)+10");
        System.out.println("PRIMER DERIVADA: f'(x)="+derivar("", 0, 4, 12)+""+derivar("-", 0, 3, 2)+""+derivar("+", 0, 2, 0));
        System.out.println("SEGUNDA DERIVADA: f'(x)="+derivar("", 0, 3, 3)+""+derivar("-", 3, 2, 2)+""+derivar("+", 2, 0, 0));
        System.out.println("SEGUNDA DERIVADA IGUALADA A 0: (x  1)(x  2)=0");
        if (f_2da(0.9) > 0) System.out.println("f''(0.9): "+f_2da(0.9)+" > 0");
        else System.out.println("f''(0.9): "+f_2da(0.9)+" < 0");
        if (f_2da(1.1) > 0) System.out.println("f''(1.1): "+f_2da(1.1)+" > 0");
        else System.out.println("f''(1.1): "+f_2da(1.1)+" < 0");
        if (f_2da(1.9) > 0) System.out.println("f''(1.9): "+f_2da(0.9)+" > 0");
        else System.out.println("f''(1.9): "+f_2da(0.9)+" < 0");
        if (f_2da(2.1) > 0) System.out.println("f''(2.1): "+f_2da(1.1)+" > 0");
        else System.out.println("f''(2.1): "+f_2da(1.1)+" < 0");
        System.out.println("Puesto que f(x) cambia de signo, se llega a la conclusión de que un punto de inflexión existe cuando x=2.");
        System.out.println("En la grafica se muestra IN en el punto de inflexión cuando x = 2\n");
        System.out.println("\nG R A F I C A de f''(x)");
        System.out.println(graficar());
        
    }

    public static double f_2da(double x) {
        return (x*x)-(3*x)+(2);
    }
    
    public static String derivar(String signo, int cons, int pot, int div){
        String resultado = signo; String potencia = "";
        if (pot == 1 || pot == 0) ;
        else if (pot -1 != 1) potencia = ""+(pot - 1);
        if (cons == 0) {
            if (pot == 0) return "";
            else cons = pot;
        } else if (pot == 0) ; else cons = cons * pot;
        if (div == 0) {
            if (potencia == "") {
                resultado += "(" + cons +")";
            }
            else resultado += "(" + cons + "x^" + potencia + ")";
        } else {
            if (cons % div == 0) {
                if (cons / div == 0) {
                    if (potencia == "") resultado += "(x" + potencia + ")";
                    else resultado += "(x^" + potencia + ")";
                } else {
                    if (potencia == "") resultado += "(" + (cons / div) + "x" + potencia + ")";
                    else resultado += "(" + (cons / div) + "x^" + potencia + ")";
                }
            } else if (div % cons == 0) {
                if (div / cons == 0) {
                    if (potencia == "") resultado += "(x" + potencia + ")";
                    else resultado += "(x^" + potencia + ")";
                } else {
                    if (potencia == "") resultado += "((x" + potencia + ")/" + (div / cons) + ")";
                    else resultado += "((x^" + potencia + ")/" + (div / cons) + ")";
                }
            } else {
                if (potencia == "") resultado += "((" + cons + "x" + potencia + ")/" + div + ")";
                else resultado += "((" + cons + "x^" + potencia + ")/" + div + ")";
            }
        }
        return resultado;
    }
    
    public static String graficar(){
        String mostrar = "";
        int p[] = new int [7];
        int grafica[] = new int[7]; int g[] = new int[7];
        g[0] = (int)f_2da(-2); posiciones[0] = 0;
        g[1] = (int)f_2da(-1); posiciones[1] = 1;
        g[2] = (int)f_2da(0); posiciones[2] = 6;
        g[3] = (int)f_2da(1); posiciones[3] = 2;
        g[4] = (int)f_2da(2); posiciones[4] = 5;
        g[5] = (int)f_2da(3); posiciones[5] = 3;
        g[6] = (int)f_2da(4); posiciones[6] = 4;
        grafica[0] = g[0]; grafica[1] = g[1]; grafica[2] = g[6];
        grafica[3] = g[2]; grafica[4] = g[5]; grafica[5] = g[3];
        grafica[6] = g[4];
        int aux = -3;
        System.out.println(" x |  y");
        System.out.println("--------");
        for (int i = 0; i < 7; i++) {
            if (aux >= 0) System.out.println(" "+aux+" | "+g[i]);
            else System.out.println(aux+" | "+g[i]);
            aux ++;
            
        }
        int saltos = grafica[0];
        for (int i = 0; i < 7; i++) grafica[i] = saltos-grafica[i];
        int puntos[] = new int[7]; int contP = 0;
        for (int i = 0; i <= saltos; i++) {
            for (int d = 0; d < 7; d++) {
                if (i == grafica[d]) {
                    mostrar += "\n";
                contP = 0;
                int cont = d; int otra  = 0;
                    try {
                        while (i == grafica[cont]) {
                    if (otra > 0) {
                        posiciones[cont] = posiciones[cont] - posiciones[cont-1];
                    }
                    grafica[cont] = 10000;
                    for (int j = 0; j < posiciones[cont]; j++) mostrar += " ";
                    if (posiciones[cont] == 2) mostrar += "IN";
                    else mostrar += "P";
                    puntos[contP] = posiciones[cont]; contP ++;
                    cont ++; otra ++;
                }
                    } catch (Exception e) {
                    }
                
                } else {
                    mostrar += "\n";
                    for (int j = 0; j < contP; j++) {
                        for (int k = 0; k < puntos[j]; k++) mostrar += " ";
                        mostrar += "|";
                    }
                }
            } 
        }
        return mostrar;
    }
}
