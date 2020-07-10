package bidaobidirectional;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import javax.swing.JOptionPane;

public class BFS1 {
    private int min, u, v, passos, e, f;
    int[] falha = new int[4];
    public ArrayList<Par> caminhoOpt;
    public ArrayList<Par> caminhoOpt2;
    boolean visitad[][];
    
    BFS1 ( ) {
        min = Integer.MAX_VALUE;
        caminhoOpt = new ArrayList<Par> ();
    }
    
    void breadthFirstSearch ( /*int grid[][]*/ boolean visitado[][], int n, int m, int i, int j, int end_i, int end_j ) {
        // Queue for BFS
        Queue<Estado> lista = new LinkedList<> ();
        
        // Starting Estado Pushing in Queue Frontier
        Estado start = new Estado(i, j, 0, n, m, visitado);
        lista.add(start);

        
        // Algoritmo BFS 
        while ( !lista.isEmpty() ) {
            Estado actual = lista.remove();
            
            i = actual.bidao4;
            j = actual.bidao3;
            
//            System.out.println("Agora ("+i+","+j+")");
            e = n-1;
            f = m-1;
            
            passos = 0;
            falha[0] = i;
            falha[1] = j;
            falha[2] = end_i;
            falha[3] = end_j;
            
            // Goal Estado Test
            if ( actual.bidao4==end_i && actual.bidao3==end_j ) {
                if ( actual.custo < min ) {
                    min = actual.custo;
                    caminhoOpt = actual.caminho;
                    
                    caminhoOpt.add(new Par(i, j));
                }
//                System.out.println("Entrou no goal \n ("+i+","+j+")");
            }
            
            // Procura encher bidao4
            if ( i!=e && !actual.visitado[e][j] /*|| (i==0 && j==3)*/) {
                actual.visitado[e][j] = true;
                Estado temp = new Estado(e, j, actual.custo+1, n, m, actual.visitado);
                temp.AddPar(actual.caminho, i, j);
                lista.add(temp);
//                passos++;
//                System.out.println(!actual.visitado[4][j]);
//                System.out.println("encher 4 por cima de ("+i+","+j+")");
//                System.out.println("Resultado ("+temp.bidao4+","+temp.bidao3+")");
            } 
            
            // Procura encher bidao3
            if ( j!=f && !actual.visitado[i][f] ) {
                actual.visitado[i][f] = true;
                Estado temp = new Estado(i, f, actual.custo+1, n, m, actual.visitado);
                temp.AddPar(actual.caminho, i, j);
                lista.add(temp);
//                passos++;
//                System.out.println(!actual.visitado[i][3]);
////                System.out.println("bdiao4 ="+actual.bidao4+"   bidao3 ="+actual.bidao3);
//                System.out.println("encher 3 por cima de ("+i+","+j+")");
//                System.out.println("Resultado ("+temp.bidao4+","+temp.bidao3+")");
            } 
            
            // Procura Despejar bidao4
            if ( i!=0 && !actual.visitado[0][j] ) {
                actual.visitado[0][j] = true;
                Estado temp = new Estado(0, j, actual.custo+1, n, m, actual.visitado);
                temp.AddPar(actual.caminho, i, j);
                lista.add(temp);
//                passos++;
//                System.out.println(!actual.visitado[0][j]);
//                System.out.println("despejar 4 por cima de ("+i+","+j+")");
//                System.out.println("Resultado ("+temp.bidao4+","+temp.bidao3+")");
            } 
            
            // Procura Despejar bidao3
            if ( j!=0 && !actual.visitado[i][0] ) {
                actual.visitado[i][0] = true;
                Estado temp = new Estado(i, 0, actual.custo+1, n, m, actual.visitado);
                temp.AddPar(actual.caminho, i, j);
                lista.add(temp);
//                passos++;
//                System.out.println(!actual.visitado[i][0]);
//                System.out.println("despejar 3 por cima de ("+i+","+j+")");
//                System.out.println("Resultado ("+temp.bidao4+","+temp.bidao3+")");
            } 
            
            // Procura Transferir 43
            if ( i!=0 ) {
                u=i;
                v=j;
                while(v<f){
                    if(u>0){
                        v++;
                        u--;
                    }else break;                    
                }
                if(!actual.visitado[u][v]){
                    actual.visitado[u][v] = true;
                    Estado temp = new Estado(u, v, actual.custo+1, n, m, actual.visitado);
                    temp.AddPar(actual.caminho, i, j);
                    lista.add(temp);
//                    passos++;
//                    System.out.println(!actual.visitado[u][v]);
//                    System.out.println("transferir 43 por cima de ("+i+","+j+")");
//                    System.out.println("Resultado ("+temp.bidao4+","+temp.bidao3+")");
                }
                
            } 
            
            // Procura Transferir 34
            if ( j!=0 ) {
                u=i;
                v=j;
                while(u<e){
                    if(v>0){
                        u++;
                        v--;
                    }else break;
                }
                if(!actual.visitado[u][v]){
                    actual.visitado[u][v] = true;
                    Estado temp = new Estado(u, v, actual.custo+1, n, m, actual.visitado);
                    temp.AddPar(actual.caminho, i, j);
                    lista.add(temp);
//                    passos++;
//                    System.out.println(!actual.visitado[u][v]);
//                    System.out.println("tranferir 34 por cima de ("+i+","+j+")");
//                    System.out.println("Resultado ("+temp.bidao4+","+temp.bidao3+")");
                }
                
                
            }// System.out.println("Fim investigacao de filhos para ("+i+","+j+")");
            
        }
    }
    
    void breadthFirstSearchrev ( /*int grid[][]*/ boolean visitado[][], int n, int m, int i, int j, int end_i, int end_j ) { 
        // Queue for BFS
        Queue<Estado> lista = new LinkedList<Estado> ();
        
        // Starting Estado Pushing in Queue Frontier
        Estado start = new Estado(i, j, 0, n, m, visitado);
        lista.add(start);
        
//        passos = 0;
        // Algoritmo BFS 
        while ( !lista.isEmpty() ) {
            Estado actual = lista.remove();
            
            i = actual.bidao4;
            j = actual.bidao3;
            
            e = n-1;
            f = m-1;
            
            passos = 0;
            falha[2] = i;
            falha[3] = j;
            falha[0] = end_i;
            falha[1] = end_j;
            // Goal Estado Test
            if ( actual.bidao4==end_i && actual.bidao3==end_j ) {
                if ( actual.custo < min ) {
                    min = actual.custo;
                    caminhoOpt = actual.caminho;
                    
                    caminhoOpt.add(new Par(i, j));
                }
            }
            
            
            // Procura contra encher bidao4
            if ( i==e && !actual.visitado[0][j] ) {
                actual.visitado[0][j] = true;
                Estado temp = new Estado(0, j, actual.custo+1, n, m, actual.visitado);
                temp.AddPar(actual.caminho, i, j);
                lista.add(temp);
//                passos++;
            } 
            
             // Procura contra encher bidao3
            if ( j==f && !actual.visitado[i][0] ) {
                actual.visitado[i][0] = true;
                Estado temp = new Estado(i, 0, actual.custo+1, n, m, actual.visitado);
                temp.AddPar(actual.caminho, i, j);
                lista.add(temp);
//                passos++;
            }
            
            // Procura contra Despejar bidao4
            if ( i==0 && !actual.visitado[e][j] ) {
                actual.visitado[e][j] = true;
                Estado temp = new Estado(e, j, actual.custo+1, n, m, actual.visitado);
                temp.AddPar(actual.caminho, i, j);
                lista.add(temp);
//                passos++;
            }     
            
            // Procura contra Despejar bidao3
            if ( j==0 && !actual.visitado[i][f] ) {
                actual.visitado[i][f] = true;
                Estado temp = new Estado(i, f, actual.custo+1, n, m, actual.visitado);
                temp.AddPar(actual.caminho, i, j);
                lista.add(temp);
//                passos++;
            }
            
            // Procura Transferir 43
            if ( i==e || j==0 ) {
                u=i;
                v=j;
                while(v<f){
                    if(u>0){
                        v++;
                        u--;
                    }else break;                    
                }
                if( !actual.visitado[u][v]){
                    actual.visitado[u][v] = true;
                    Estado temp = new Estado(u, v, actual.custo+1, n, m, actual.visitado);
                    temp.AddPar(actual.caminho, i, j);
                    lista.add(temp);
//                    passos++;
                }
            } 
            
            // Procura Transferir 34
            if ( j==f || i==0 ) {
                u=i;
                v=j;
                while(u<e){
                    if(v>0){
                        u++;
                        v--;
                    }else break;                    
                }
                if( !actual.visitado[u][v]){
                    actual.visitado[u][v] = true;
                    Estado temp = new Estado(u, v, actual.custo+1, n, m, actual.visitado);
                    temp.AddPar(actual.caminho, i, j);
                    lista.add(temp);
//                    passos++;
                }
                
            }
            
        } 
    }
    void breadthFirstSearchpara ( /*int grid[][]*/ boolean visitado[][], int n, int m, int i, int j, int end_i, int end_j ) { 
        // Queue for BFS
        Queue<Estado> lista = new LinkedList<Estado> ();
        
        // Starting Estado Pushing in Queue Frontier
        Estado start = new Estado(i, j, 0, n, m, visitado);
        lista.add(start);
        
        
//        passos = 0;
        // Algoritmo BFS 
        while ( !lista.isEmpty() ) {
            Estado actual = lista.remove();
            
            i = actual.bidao4;
            j = actual.bidao3;
            
            e = n-1;
            f = m-1;
            
            passos = 0;
            falha[2] = i;
            falha[3] = j;
            falha[0] = end_i;
            falha[1] = end_j;
            // Goal Estado Test
            if ( actual.bidao4==end_i && actual.bidao3==end_j ) {
                if ( actual.custo < min ) {
                    min = actual.custo;
                    caminhoOpt = actual.caminho;
                    
                    caminhoOpt.add(new Par(i, j));
                }
            }
            
            
            // Procura contra encher bidao4
            if ( i==e && !actual.visitado[0][j] ) {
                actual.visitado[0][j] = true;
                Estado temp = new Estado(0, j, actual.custo+1, n, m, actual.visitado);
                temp.AddPar(actual.caminho, i, j);
                lista.add(temp);
//                passos++;
            } 
            
             // Procura contra encher bidao3
            if ( j==f && !actual.visitado[i][0] ) {
                actual.visitado[i][0] = true;
                Estado temp = new Estado(i, 0, actual.custo+1, n, m, actual.visitado);
                temp.AddPar(actual.caminho, i, j);
                lista.add(temp);
//                passos++;
            }
            
            // Procura contra Despejar bidao4
            if ( i==0 && !actual.visitado[e][j] ) {
                actual.visitado[e][j] = true;
                Estado temp = new Estado(e, j, actual.custo+1, n, m, actual.visitado);
                temp.AddPar(actual.caminho, i, j);
                lista.add(temp);
//                passos++;
            }     
            
            // Procura contra Despejar bidao3
            if ( j==0 && !actual.visitado[i][f] ) {
                actual.visitado[i][f] = true;
                Estado temp = new Estado(i, f, actual.custo+1, n, m, actual.visitado);
                temp.AddPar(actual.caminho, i, j);
                lista.add(temp);
//                passos++;
            }
            
            // Procura Transferir 43
            if ( i==e || j==0 ) {
                u=i;
                v=j;
                while(v<f){
                    if(u>0){
                        v++;
                        u--;
                    }else break;                    
                }
                if( !actual.visitado[u][v]){
                    actual.visitado[u][v] = true;
                    Estado temp = new Estado(u, v, actual.custo+1, n, m, actual.visitado);
                    temp.AddPar(actual.caminho, i, j);
                    lista.add(temp);
//                    passos++;
                }
            } 
            
            // Procura Transferir 34
            if ( j==f || i==0 ) {
                u=i;
                v=j;
                while(u<e){
                    if(v>0){
                        u++;
                        v--;
                    }else break;                    
                }
                if( !actual.visitado[u][v]){
                    actual.visitado[u][v] = true;
                    Estado temp = new Estado(u, v, actual.custo+1, n, m, actual.visitado);
                    temp.AddPar(actual.caminho, i, j);
                    lista.add(temp);
//                    passos++;
                }
                
            }
            
        } 
    }
    
    void printResult ( ) {
        System.out.println("\n\nResultado da Procura!");
        System.out.print("Custo caminho optimo: " + caminhoOpt.size() + "\nCaminho Optimo: {");
        for(int i=0; i<caminhoOpt.size(); i++) {
            System.out.print("(" + caminhoOpt.get(i).bidao4 + ", " + caminhoOpt.get(i).bidao3 + ")");
            if ( i!=caminhoOpt.size()-1 ) System.out.print(" ");
        } System.out.println("}");
    }
    
    String printResult2 ( ) {
        String message = "";
        message = message + "\n\nResultado da procura em Amplitude!";
        message = message + "\nCusto caminho optimo: " + caminhoOpt.size() + "\nCaminho : {";
        for(int i=0; i<caminhoOpt.size(); i++) {
            message = message + ("(" + caminhoOpt.get(i).bidao4 + ", " + caminhoOpt.get(i).bidao3 + ")");
            if ( i!=caminhoOpt.size()-1 ) message = message + " ";
        } message = message + "}\n";
//        if ( caminhoOpt.size()!=0 ) message = message + "Numero de Verificações: "+ passos;
//         message = message + "Nao existe caminho que começa em ("+falha[0]+","+falha[1]+")"
//                + " e termina em ("+falha[2]+","+falha[3]+")";
        
        //JOptionPane.showMessageDialog(null, message);
        return message;
    }
    
    void writeFile ( ) throws IOException {
        File file = new File("Output/BFS-Result.txt");
        
        if ( !file.exists() ) 
            file.createNewFile();
        
        FileWriter fw = new FileWriter(file);
        BufferedWriter bw = new BufferedWriter(fw);
        
        bw.write("Resultado Breadth First Search"); bw.newLine();
        bw.write("Custo caminho optimo: " + caminhoOpt.size()); bw.newLine();
        bw.write("Caminho Optimo: {");
        for(int i=0; i<caminhoOpt.size(); i++) {
            bw.write("(" + caminhoOpt.get(i).bidao4 + ", " + caminhoOpt.get(i).bidao3 + ")");
            if ( i!=caminhoOpt.size()-1 ) bw.write(" ");
        } bw.write("}"); bw.newLine();
        
        bw.flush();
        bw.close();
        fw.close();
    } // #EndOfWriteFile!
}
