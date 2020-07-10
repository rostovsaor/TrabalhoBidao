package bidaobidirectional;

import extra.Actor;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class JarroBidirectional {
    private int min;
    public ArrayList<Par> optimalPath;
    static Actor a = new Actor();
    static boolean iterar[] = new boolean[6];
    int iteracao[][] = new int [4][5];
    static boolean ver;
    static int[][] estados;
    static boolean[][] visitado;
    static int n, m, i, j, fim_i, fim_j;
    
    //Variaveis auxiliares
    static int u, v, e, f, passos;
    public ArrayList<Par> caminhoOpt;
    int[] falha = new int[4];

    
    
    
    

    /**
     * @param args the command line arguments
     * @throws java.io.FileNotFoundException
     * @throws java.io.IOException
     */
    public static void main(String[] args) throws FileNotFoundException, IOException{
        // TODO code application logic here
        ver = false;
        
        LerEstados();
        
//        BFS2 bfs = new BFS2();
//        bfs.breadthFirstSearchpara(visitado, n, m, i, j, fim_i, fim_j);
//        bfs.printResult();
//        bfs.printResultbd();
        
        BFS1 bfs = new BFS1();
        bfs.breadthFirstSearch( visitado, n, m, i, j, fim_i, fim_j);
//        bfs.printResult();
        String t = bfs.printResult2();
        bfs.writeFile();
        

        BFS1 bfs2 = new BFS1();
        bfs2.breadthFirstSearchrev(visitado, n, m, fim_i, fim_j, i, j);
//        bfs2.printResult();
        String t2 = bfs2.printResult2();
        bfs2.writeFile();
        
        DFS dfs = new DFS();
        dfs.depthFirstSearch(estados, visitado, new ArrayList<Par>(), n, m, i, j, fim_i, fim_j, 0);
//        dfs.printResult(); 
        String s = dfs.printResult2(); 
        dfs.writeFile();
        
        DFS dfs2 = new DFS();
        dfs2.depthFirstSearchrev(estados, visitado, new ArrayList<Par>(), n, m, fim_i, fim_j, i, j, 0);
//        dfs2.printResult(); 
        String s2 = dfs2.printResult2(); 
        dfs2.writeFile();
        
        
        String text = "";
        /*
        double encontro = dfs.caminhoOpt.size()/2;
        int zz = Integer;
        if (encontro>=1){
            text = "Os caminhos se encontram no estado ("+ bfs2.caminhoOpt.get(encontro).bidao4+","+bfs2.caminhoOpt.get(encontro).bidao3+").";
        }*/
        for(int u=0; u<bfs.caminhoOpt.size(); u++) {
            for(int v=0; v<bfs2.caminhoOpt.size()-1; v++) {
            if (bfs.caminhoOpt.get(u).bidao4 == bfs2.caminhoOpt.get(v).bidao4 && bfs.caminhoOpt.get(u).bidao3 == bfs2.caminhoOpt.get(v).bidao3 && !ver) {
            //System.out.println("Os caminhos se encontram no estado ("+ bfs2.caminhoOpt.get(v).bidao4+","+bfs2.caminhoOpt.get(v).bidao3+").");
            //text = "Os caminhos se encontram no estado ("+ bfs2.caminhoOpt.get(v).bidao4+","+bfs2.caminhoOpt.get(v).bidao3+").";
            //JOptionPane.showMessageDialog(null, text);
            ver = true;
        }
        }
        }
        String titulo = "Busca bidireccional: Amplitude & Amplitude\n";
        JOptionPane.showMessageDialog(null,titulo + t +"\n"+ t2 +"\n"+text);

        ver = false;
        
        String text2 = "";
        for(int u=0; u<dfs.caminhoOpt.size(); u++) {
            for(int v=0; v<dfs2.caminhoOpt.size()-1; v++) {
            if (dfs.caminhoOpt.get(u).bidao4 == dfs2.caminhoOpt.get(v).bidao4 && dfs.caminhoOpt.get(u).bidao3 == dfs2.caminhoOpt.get(v).bidao3 && !ver) {
            //System.out.println("Os caminhos se encontram no estado ("+ dfs2.caminhoOpt.get(v).bidao4+","+dfs2.caminhoOpt.get(v).bidao3+").");
            //text2 = "Os caminhos se encontram no estado ("+ dfs2.caminhoOpt.get(v).bidao4+","+dfs2.caminhoOpt.get(v).bidao3+").";
            //JOptionPane.showMessageDialog(null, text);
            ver = true;
        }
        }
        }
        String titulo2 = "Busca bidireccional: Profundidade & Profundidade\n";

        JOptionPane.showMessageDialog(null,titulo2 + s +"\n"+ s2 +"\n"+text2);
        
        ver = false;
        
        String text3 = "";
        for(int u=0; u<bfs.caminhoOpt.size(); u++) {
            for(int v=0; v<dfs2.caminhoOpt.size()-1; v++) {
            if (bfs.caminhoOpt.get(u).bidao4 == dfs2.caminhoOpt.get(v).bidao4 && bfs.caminhoOpt.get(u).bidao3 == dfs2.caminhoOpt.get(v).bidao3 && !ver) {
            //System.out.println("Os caminhos se encontram no estado ("+ dfs2.caminhoOpt.get(v).bidao4+","+dfs2.caminhoOpt.get(v).bidao3+").");
            //text3 = "Os caminhos se encontram no estado ("+ dfs2.caminhoOpt.get(v).bidao4+","+dfs2.caminhoOpt.get(v).bidao3+").";
            //JOptionPane.showMessageDialog(null, text);
            ver = true;
        }
        }
        }
        String titulo3 = "Busca bidireccional: Amplitude & Profundidade\n";

        JOptionPane.showMessageDialog(null,titulo3 + s +"\n"+ t2 +"\n"+text3);
        
        ver = false;
        
        
        
        
//        LerEstados();S
//        IDS ids = new IDS();
//        ids.iterativeDeepeningSearch(estados, visitado, n, m, i, j, fim_i, fim_j);
//        ids.printResult();
//        ids.writeFile();
        
//        AStar ast = new AStar();
//        ast.AStarSearch(estados, visitado, n, m, i, j, fim_i, fim_j);
//        ast.printResult();
//        ast.writeFile();
        
//         bfs.printResult(); 
//         bfs2.printResult();
        
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
//            falha[0] = i;
//            falha[1] = j;
//            falha[2] = end_i;
//            falha[3] = end_j;
            
            //Goal Estado Test
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
    
    
    public static void estado(){
        System.out.println("("+a.getBidao4()+", "+a.getBidao3()+")");
    }
    
    public static void clean(){
        for (int i = 0; i < iterar.length; i++) {
            iterar[i]=false;
        }
    }
    
    static void LerEstados ( ) throws FileNotFoundException {
        // Read File from Scanner Stream
//        Scanner sc = new Scanner(new File("Estados/A1-in4.txt"));
        JFrame frame = new JFrame("InputDialog Example #1");
        // Dimensao estados
        n = 1 + Integer.parseInt(JOptionPane.showInputDialog("Quantidade maxima de litros do primeiro bidao")); 
//        sc.nextInt();
        m = 1 + Integer.parseInt(JOptionPane.showInputDialog("Quantidade maxima de litros do segundo bidao")); 
//        sc.nextInt();
        
//        estados = new int[n][m];
        visitado = new boolean[n][m];
        
        // Estado Inicial
        i = Integer.parseInt(JOptionPane.showInputDialog("Quantidade inicial de litros bidao4")); 
//        sc.nextInt();
        j = Integer.parseInt(JOptionPane.showInputDialog("Quantidade inicial de litros bidao3")); 
//        sc.nextInt();
        
        // Estado Final
        fim_i = Integer.parseInt(JOptionPane.showInputDialog("Quantidade final de litros bidao4")); 
//        sc.nextInt();
        fim_j = Integer.parseInt(JOptionPane.showInputDialog("Quantidade final de litros bidao3")); 
//        sc.nextInt();
        
        // Transformar Estados em Array 2d
//        System.out.println("Tabela de Estados");
        for(int x=0; x<n; x++) {
            for(int y=0; y<m; y++) {
                visitado[x][y] = false;
//                estados[x][y] = sc.nextInt();
                
//                if ( x==i && y==j ) System.out.print("S ");
//                else if ( x==fim_i && y==fim_j ) System.out.print("D ");
//                else System.out.print(estados[x][y] + " ");
            } System.out.println();
            
        } //visitado[4][3] = true;         
        //visitado[0][0] = true;
        
        
    } // #Firm_LerEstados!
    /*
    static void LerEstadosBD ( ) throws FileNotFoundException {
        // Read File from Scanner Stream
        Scanner sc = new Scanner(new File("Estados/A1-in4.txt"));
        
        // Dimensao estados
        n = 5; sc.nextInt();
        m = 4; sc.nextInt();
        
        estados = new int[n][m];
        visitado = new boolean[n][m];
        
        // Estado Final
        fim_i = 0; sc.nextInt();
        fim_j = 0; sc.nextInt();
        
        // Estado Inicial
        i = 2; sc.nextInt();
        j = 3; sc.nextInt();
        
        // Transformar Estados em Array 2Dp-
//        System.out.println("Tabela de Estados");
        for(int x=0; x<n; x++) {
            for(int y=0; y<m; y++) {
                visitado[x][y] = false;
                estados[x][y] = sc.nextInt();
                
//                if ( x==i && y==j ) System.out.print("S ");
//                else if ( x==fim_i && y==fim_j ) System.out.print("D ");
//                else System.out.print(estados[x][y] + " ");
            } System.out.println();
        }visitado[4][3] = true;
    }*/
}
    