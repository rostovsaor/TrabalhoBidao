package bidaobidirectional;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class DFS {
    private int min, u, v, e ,f; 
    private int passos = 0;
    int[] falha = new int[4];
    public ArrayList<Par> caminhoOpt;
    
    DFS ( ) {
        min = Integer.MAX_VALUE;
        caminhoOpt = new ArrayList<Par> ();
    }
    
    void depthFirstSearch ( int grid[][], boolean array[][], ArrayList<Par> temp, int n, int m, int i, int j, int end_i, int end_j , int passo) {
        // Copy Constructor
        boolean visitado[][] = new boolean[n][m];
        copyBoolean(visitado, array, n, m);
        
        ArrayList<Par> path = new ArrayList<Par> ();
        copyList(path, temp);
        path.add(new Par(i, j));
        
        // If Indexes are Out of Bound then Return from Branch
        if ( i<0 || j<0 || i>=n || j>=m ) return;
        
        e = n-1;
        f = m-1;
        passos = passo;
        falha[0] = i;
        falha[1] = j;
        falha[2] = end_i;
        falha[3] = end_j;
        
        // Goal State Test
        if ( i==end_i && j==end_j ) {
            if ( path.size() <= min ) {
                min = path.size();
                caminhoOpt = path;
            }
        }
        
        // Horizontal and Vertical Search
        if ( i!=e && !visitado[e][j] ) { // Up 
            visitado[e][j] = true;
            depthFirstSearch(grid, visitado, path, n, m, e, j, end_i, end_j, passos);
        } if ( j!=f && !visitado[i][f] ) { // Down
            visitado[i][f] = true;
            depthFirstSearch(grid, visitado, path, n, m, i, f, end_i, end_j, passos);
        } if ( i!=0 && !visitado[0][j] ) { // Left
            visitado[0][j] = true;
            depthFirstSearch(grid, visitado, path, n, m, 0, j, end_i, end_j, passos);
        } if ( j!=0 && !visitado[i][0] ) { // Right
            visitado[i][0] = true;
            depthFirstSearch(grid, visitado, path, n, m, i, 0, end_i, end_j, passos);
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
            if(!visitado[u][v]){
                visitado[u][v] = true;
                depthFirstSearch(grid, visitado, path, n, m, u, v, end_i, end_j, passos);
            }
        } 
        if ( j!=0 ) {
            u=i;
            v=j;
            while(u<e){
                if(v>0){
                    u++;
                    v--;
                }else break;
            }
            if(!visitado[u][v]){
                visitado[u][v] = true;
                depthFirstSearch(grid, visitado, path, n, m, u, v, end_i, end_j, passos);
            }

        }
    } // #EndOfDepthFirstSearch!
    
    void depthFirstSearchrev ( int grid[][], boolean array[][], ArrayList<Par> temp, int n, int m, int i, int j, int end_i, int end_j , int passo) {
        // Copy Constructor
        boolean visitado[][] = new boolean[n][m];
        copyBoolean(visitado, array, n, m);
        
        ArrayList<Par> path = new ArrayList<Par> ();
        copyList(path, temp);
        path.add(new Par(i, j));
        
        // If Indexes are Out of Bound then Return from Branch
        if ( i<0 || j<0 || i>=n || j>=m ) return;
        
        e = n-1;
        f = m-1;
        passos = passo;
        falha[2] = i;
        falha[3] = j;
        falha[0] = end_i;
        falha[1] = end_j;
            
        // Goal State Test
        if ( i==end_i && j==end_j ) {
            if ( path.size() <= min ) {
                min = path.size();
                caminhoOpt = path;
            }
        }
        
        // Procura contra encher bidao4
        if ( i==e && !visitado[0][j] ) { // Up 
            visitado[0][j] = true;
            depthFirstSearchrev(grid, visitado, path, n, m, 0, j, end_i, end_j, passos);
        } 
        
        // Procura contra encher bidao3
        if ( j==f && !visitado[i][0] ) { // Down
            visitado[i][0] = true;
            depthFirstSearchrev(grid, visitado, path, n, m, i, 0, end_i, end_j, passos);
        } 
        
        // Procura contra Despejar bidao4
        if ( i==0 && !visitado[e][j] ) { // Left
            visitado[e][j] = true;
            depthFirstSearchrev(grid, visitado, path, n, m, e, j, end_i, end_j, passos);
        } 
        
        // Procura contra Despejar bidao3
        if ( j==0 && !visitado[i][f] ) { // Right
            visitado[i][f] = true;
            depthFirstSearchrev(grid, visitado, path, n, m, i, f, end_i, end_j, passos);
        }
        
        // Procura contra transferir 43
        if ( i==e || j==0 ) {
            u=i;
            v=j;
            while(v<f){
                if(u>0){
                    v++;
                    u--;
                }else break;                    
            }
            if(!visitado[u][v]){
                visitado[u][v] = true;
                depthFirstSearchrev(grid, visitado, path, n, m, u, v, end_i, end_j, passos);
            }
        }
        
        // Procura contra transferir 34
        if ( j==f || i==0 ) {
            u=i;
            v=j;
            while(u<e){
                if(v>0){
                    u++;
                    v--;
                }else break;
            }
            if(!visitado[u][v]){
                visitado[u][v] = true;
                depthFirstSearchrev(grid, visitado, path, n, m, u, v, end_i, end_j, passos);
            }

        }
    } // #EndOfDepthFirstSearch!
    
    void copyBoolean ( boolean visited[][], boolean v[][], int n, int m ) {
        for(int i=0; i<n; i++) {
            System.arraycopy(v[i], 0, visited[i], 0, m);
        }
    } // #EndOfCopyBoolean!
    
    void copyList ( ArrayList<Par> path, ArrayList<Par> temp ) {
        for(int i=0; i<temp.size(); i++) path.add(temp.get(i));
    } // #EndOfCopyList!
    
    void printResult ( ) {
        System.out.println("\n\nResultado da pesquisa em profundidade!");
        System.out.print("Custo caminho optimo: " + caminhoOpt.size() + "\nCaminho: {");
        for(int i=0; i<caminhoOpt.size(); i++) {
            System.out.print("(" + caminhoOpt.get(i).bidao4 + ", " + caminhoOpt.get(i).bidao3 + ")");
            if ( i!=caminhoOpt.size()-1 ) System.out.print(" ");
        } System.out.println("}");
    } // #EndOfPrintResult!
    
    String printResult2 ( ) {
        String message = "";
        message = message + "\n\nResultado da pesquisa em Profundidade!";
        message = message + "\nCusto caminho optimo: " + caminhoOpt.size() + "\nCaminho: {";
        for(int i=0; i<caminhoOpt.size(); i++) {
            message = message + "(" + caminhoOpt.get(i).bidao4 + ", " + caminhoOpt.get(i).bidao3 + ")";
            if ( i!=caminhoOpt.size()-1 )  message = message + (" ");
        }  message = message + "}\n";
        
//        if ( caminhoOpt.size()!=0 ) message = message + "Numero de Verificações: "+ passos;
//         message = message + "Nao existe caminho que começa em ("+falha[0]+","+falha[1]+")"
//                + " e termina em ("+falha[2]+","+falha[3]+")";
        return message;
    } // #EndOfPrintResult!
    
    void writeFile ( ) throws IOException {
        File file = new File("Output/DFS-Result.txt");
        
        if ( !file.exists() ) 
            file.createNewFile();
        
        FileWriter fw = new FileWriter(file);
        BufferedWriter bw = new BufferedWriter(fw);
        
        bw.write("Depth First Search Result!"); bw.newLine();
        bw.write("Optimal Path Cost: " + caminhoOpt.size()); bw.newLine();
        bw.write("Optimal Path: {");
        for(int i=0; i<caminhoOpt.size(); i++) {
            bw.write("(" + caminhoOpt.get(i).bidao4 + ", " + caminhoOpt.get(i).bidao3 + ")");
            if ( i!=caminhoOpt.size()-1 ) bw.write(" ");
        } bw.write("}"); bw.newLine();
        
        bw.flush();
        bw.close();
        fw.close();
    } // #EndOfWriteFile!
}
