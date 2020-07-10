package bidaobidirectional;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import javax.swing.JOptionPane;
import bidaobidirectional.DFS;

public class BFS2 {
    private int min, minrev, u, v, passosrev, a, b;
    int[] falha = new int[4];
    public ArrayList<Par> caminhoOpt;
    public ArrayList<Par> caminhoOptrev;
    boolean visitad[][];
    
    BFS2 ( ) {
        min = Integer.MAX_VALUE;
        minrev = Integer.MAX_VALUE;
        caminhoOpt = new ArrayList<Par> ();
        caminhoOptrev = new ArrayList<Par> ();
    }
    
    void breadthFirstSearch ( /*int grid[][]*/ boolean visitado[][], int n, int m, int i, int j, int end_i, int end_j ) {
        // Queue for BFS
        Queue<Estado> lista = new LinkedList<> ();
        DFS dfs = new DFS();
        BFS2 bfs2 = new BFS2();
        bfs2.breadthFirstSearchpara(visitado, n, m, i, j, end_i, end_j);
        // Starting Estado Pushing in Queue Frontier
        Estado start = new Estado(i, j, 0, n, m, visitado);
        lista.add(start);
        passosrev = 0;
        
        // Algoritmo BFS 
        while ( !lista.isEmpty() ) {
            Estado actual = lista.remove();
            
            i = actual.bidao4;
            j = actual.bidao3;
            
//            System.out.println("Agora ("+i+","+j+")");
            a = n-1;
            b = m-1;
            
            passosrev++;
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
            if ( i!=a && !actual.visitado[a][j] /*|| (i==0 && j==3)*/) {
                actual.visitado[a][j] = true;
                Estado temp = new Estado(a, j, actual.custo+1, n, m, actual.visitado);
                temp.AddPar(actual.caminho, i, j);
                lista.add(temp);
//                passos++;
//                System.out.println(!actual.visitado[4][j]);
//                System.out.println("encher 4 por cima de ("+i+","+j+")");
//                System.out.println("Resultado ("+temp.bidao4+","+temp.bidao3+")");
            } 
            
            // Procura encher bidao3
            if ( j!=b && !actual.visitado[i][b] ) {
                actual.visitado[i][b] = true;
                Estado temp = new Estado(i, b, actual.custo+1, n, m, actual.visitado);
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
                while(v<b){
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
                while(u<a){
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
        passosrev = 0;
//        passos = 0;
        // Algoritmo BFS 
        while ( !lista.isEmpty() ) {
            Estado actual = lista.remove();
            
            i = actual.bidao4;
            j = actual.bidao3;
            
            a = n-1;
            b = m-1;
            
            passosrev++;
            
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
            if ( i==a && !actual.visitado[0][j] ) {
                actual.visitado[0][j] = true;
                Estado temp = new Estado(0, j, actual.custo+1, n, m, actual.visitado);
                temp.AddPar(actual.caminho, i, j);
                lista.add(temp);
//                passos++;
            } 
            
             // Procura contra encher bidao3
            if ( j==b && !actual.visitado[i][0] ) {
                actual.visitado[i][0] = true;
                Estado temp = new Estado(i, 0, actual.custo+1, n, m, actual.visitado);
                temp.AddPar(actual.caminho, i, j);
                lista.add(temp);
//                passos++;
            }
            
            // Procura contra Despejar bidao4
            if ( i==0 && !actual.visitado[a][j] ) {
                actual.visitado[a][j] = true;
                Estado temp = new Estado(a, j, actual.custo+1, n, m, actual.visitado);
                temp.AddPar(actual.caminho, i, j);
                lista.add(temp);
//                passos++;
            }     
            
            // Procura contra Despejar bidao3
            if ( j==0 && !actual.visitado[i][b] ) {
                actual.visitado[i][b] = true;
                Estado temp = new Estado(i, b, actual.custo+1, n, m, actual.visitado);
                temp.AddPar(actual.caminho, i, j);
                lista.add(temp);
//                passos++;
            }
            
            // Procura Transferir 43
            if ( i==a || j==0 ) {
                u=i;
                v=j;
                while(v<b){
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
            if ( j==b || i==0 ) {
                u=i;
                v=j;
                while(u<a){
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
        Queue<Estado> listarev = new LinkedList<Estado> ();
        
        // Starting Estado Pushing in Queue Frontier
        Estado start = new Estado(i, j, 0, n, m, visitado);
        Estado startrev = new Estado(end_j, end_j, 0, n, m, visitado);
        start.visitado[i][j] = true;
        startrev.visitado[end_i][end_j] = true;
        
        lista.add(start);
        listarev.add(startrev);
        
        int fim_i = end_i;
        int fim_j = end_j;
        
        int fimrev_i = i;
        int fimrev_j = j;
        
        a = n-1;
        b = m-1;
//        passos = 0;
        // Algoritmo BFS 
        passosrev++;
        while ( !lista.isEmpty() && !listarev.isEmpty()) {
            Estado actual = lista.remove();
            Estado actualrev = listarev.remove();
            
            i = actual.bidao4;
            j = actual.bidao3;

            end_i = actualrev.bidao4;
            end_j = actualrev.bidao3;
            
            
            
//            falha[2] = i;
//            falha[3] = j;
//            falha[0] = end_i;
//            falha[1] = end_j;
            // Goal Estado Test
            if ( actual.bidao4==fim_i && actual.bidao3==fim_j ) {
                if ( actual.custo < min ) {
                    min = actual.custo;
                    caminhoOpt = actual.caminho;
                    
                    caminhoOpt.add(new Par(i, j));
                }
            }
            
            if ( actualrev.bidao4==fimrev_i && actualrev.bidao3==fimrev_j ) {
                if ( actualrev.custo < minrev ) {
                    minrev = actualrev.custo;
                    caminhoOptrev = actualrev.caminho;
                    
                    caminhoOptrev.add(new Par(end_i, end_j));
                }
            }
            
            // Procura encher bidao4
            if ( i!=a && !actual.visitado[a][j] /*|| (i==0 && j==3)*/) {
                actual.visitado[a][j] = true;
                Estado temp = new Estado(a, j, actual.custo+1, n, m, actual.visitado);
                temp.AddPar(actual.caminho, i, j);
                lista.add(temp);
//                passos++;
//                System.out.println(!actual.visitado[4][j]);
//                System.out.println("encher 4 por cima de ("+i+","+j+")");
//                System.out.println("Resultado ("+temp.bidao4+","+temp.bidao3+")");
            } 
            
            // Procura encher bidao3
            if ( j!=b && !actual.visitado[i][b] ) {
                actual.visitado[i][b] = true;
                Estado temp = new Estado(i, b, actual.custo+1, n, m, actual.visitado);
                temp.AddPar(actual.caminho, i, j);
                lista.add(temp);
//                passos++;
//                System.out.println(!actual.visitado[i][3]);
//                System.out.println("bdiao4 ="+actual.bidao4+"   bidao3 ="+actual.bidao3);
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
                while(v<b){
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
                while(u<a){
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
                
                
            }
            
            //////////////////////////////////////////////////////
            //////////////////////////////////////////////////////
            //////////////////////////////////////////////////////
            //////////////////////////////////////////////////////
            // Procura contra encher bidao4
            
            if ( end_i==a && !actualrev.visitado[0][end_j] ) {
                actualrev.visitado[0][end_j] = true;
                Estado temp = new Estado(0, end_j, actualrev.custo+1, n, m, actualrev.visitado);
                temp.AddPar(actualrev.caminho, end_i, end_j);
                listarev.add(temp);
//                System.out.println(!actualrev.visitado[4][j]);
//                System.out.println("encher 4 por cima de ("+end_i+","+end_j+")");
//                System.out.println("Resultado ("+temp.bidao4+","+temp.bidao3+")");
                
//                passos++;
            } 
            
             // Procura contra encher bidao3
            if ( end_j==b && !actualrev.visitado[end_i][0] ) {
                actualrev.visitado[end_i][0] = true;
                Estado temp = new Estado(end_i, 0, actualrev.custo+1, n, m, actualrev.visitado);
                temp.AddPar(actualrev.caminho, end_i, end_j);
                listarev.add(temp);
//                passos++;
//                System.out.println(!actual.visitado[i][3]);
//                System.out.println("encher 3 por cima de ("+i+","+j+")");
//                System.out.println("Resultado ("+temp.bidao4+","+temp.bidao3+")");
            }
            
            // Procura contra Despejar bidao4
            if ( end_i==0 && !actualrev.visitado[a][end_j] ) {
                actualrev.visitado[a][end_j] = true;
                Estado temp = new Estado(a, end_j, actualrev.custo+1, n, m, actualrev.visitado);
                temp.AddPar(actualrev.caminho, end_i, end_j);
                listarev.add(temp);
//                passos++;
//                System.out.println(!actual.visitado[a][end_j]);
//                System.out.println("despejar 4 por cima de ("+i+","+j+")");
//                System.out.println("Resultado ("+temp.bidao4+","+temp.bidao3+")");
            }     
            
            // Procura contra Despejar bidao3
            if ( end_j==0 && !actualrev.visitado[end_i][b] ) {
                actualrev.visitado[end_i][b] = true;
                Estado temp = new Estado(end_i, b, actualrev.custo+1, n, m, actualrev.visitado);
                temp.AddPar(actualrev.caminho, end_i, end_j);
                listarev.add(temp);
//                passos++;
//                System.out.println(!actualrev.visitado[end_i][b]);
//                System.out.println("d3 por cima de ("+end_i+","+end_j+")");
//                System.out.println("Resultado ("+temp.bidao4+","+temp.bidao3+")");
            }
            
            // Procura Transferir 43
            if ( end_i==a || end_j==0 ) {
                u=end_i;
                v=end_j;
                while(v<b){
                    if(u>0){
                        v++;
                        u--;
                    }else break;                    
                }
                if( !actualrev.visitado[u][v]){
                    actualrev.visitado[u][v] = true;
                    Estado temp = new Estado(u, v, actualrev.custo+1, n, m, actualrev.visitado);
                    temp.AddPar(actualrev.caminho, end_i, end_j);
                    listarev.add(temp);
//                    passos++;
//                System.out.println(!actualrev.visitado[4][j]);
//                System.out.println("encher 4 por cima de ("+end_i+","+end_j+")");
//                System.out.println("Resultado ("+temp.bidao4+","+temp.bidao3+")");
                }
            } 
            
            // Procura Transferir 34
            if ( end_j==b || end_i==0 ) {
                u=end_i;
                v=end_j;
                while(u<a){
                    if(v>0){
                        u++;
                        v--;
                    }else break;                    
                }
                if( !actualrev.visitado[u][v]){
                    actualrev.visitado[u][v] = true;
                    Estado temp = new Estado(u, v, actualrev.custo+1, n, m, actualrev.visitado);
                    temp.AddPar(actualrev.caminho, end_i, end_j);
                    listarev.add(temp);
//                    passos++;
//                    System.out.println(!actualrev.visitado[u][v]);
//                    System.out.println("tranferir 34 por cima de ("+end_i+","+end_j+")");
//                    System.out.println("Resultado ("+temp.bidao4+","+temp.bidao3+")");
                }
                
            }
            
            //interseccao
            if (actual.visitado[i][j] == actualrev.visitado[end_i][end_j] == true) {
                System.out.println("O caminhos se encontraram em :("+i+","+j+").");
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
    
    void printResultbd ( ) {
        System.out.println("\n\nResultado da Procura!");
        System.out.print("Custo caminho optimo: " + caminhoOptrev.size() + "\nCaminho Optimo: {");
        for(int i=0; i<caminhoOptrev.size(); i++) {
            System.out.print("(" + caminhoOptrev.get(i).bidao4 + ", " + caminhoOptrev.get(i).bidao3 + ")");
            if ( i!=caminhoOptrev.size()-1 ) System.out.print(" ");
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
