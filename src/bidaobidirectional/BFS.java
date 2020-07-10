package bidaobidirectional;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class BFS {
    private int min;
    public ArrayList<Par> caminhoOpt;
    
    BFS ( ) {
        min = Integer.MAX_VALUE;
        caminhoOpt = new ArrayList<Par> ();
    }
    
    void breadthFirstSearch ( int grid[][], boolean visitado[][], int n, int m, int i, int j, int end_i, int end_j ) { 
        // Queue for BFS
        Queue<Estado> queue = new LinkedList<Estado> ();
        
        // Starting Estado Pushing in Queue Frontier
        Estado start = new Estado(i, j, 0, n, m, visitado);
        queue.add(start);
        
        // BFS Algorithm Implementation
        while ( !queue.isEmpty() ) {
            Estado actual = queue.remove();
            
            i = actual.bidao4;
            j = actual.bidao3;
            
            // Goal Estado Test
            if ( actual.bidao4==end_i && actual.bidao3==end_j ) {
                if ( actual.custo < min ) {
                    min = actual.custo;
                    caminhoOpt = actual.caminho;
                    
                    caminhoOpt.add(new Par(i, j));
                }
            }
            
            // Horizontal and Vertical Conditional Search
            if ( i-1>=0 && !actual.visitado[i-1][j] && grid[i-1][j]==0 ) {
                actual.visitado[i-1][j] = true;
                Estado temp = new Estado(i-1, j, actual.custo+1, n, m, actual.visitado);
                temp.AddPar(actual.caminho, i, j);
                queue.add(temp);
            } if ( i+1<n && !actual.visitado[i+1][j] && grid[i+1][j]==0 ) {
                actual.visitado[i+1][j] = true;
                Estado temp = new Estado(i+1, j, actual.custo+1, n, m, actual.visitado);
                temp.AddPar(actual.caminho, i, j);
                queue.add(temp);
            } if ( j-1>=0 && !actual.visitado[i][j-1] && grid[i][j-1]==0 ) {
                actual.visitado[i][j-1] = true;
                Estado temp = new Estado(i, j-1, actual.custo+1, n, m, actual.visitado);
                temp.AddPar(actual.caminho, i, j);
                queue.add(temp);
            } if ( j+1<m && !actual.visitado[i][j+1] && grid[i][j+1]==0 ) {
                actual.visitado[i][j+1] = true;
                Estado temp = new Estado(i, j+1, actual.custo+1, n, m, actual.visitado);
                temp.AddPar(actual.caminho, i, j);
                queue.add(temp);
            }
            
            // Diagonal and Anti-Diagonal Conditional Search
            if ( i-1>=0 && j-1>=0 && !actual.visitado[i-1][j-1] && grid[i-1][j-1]==0 ) {
                actual.visitado[i-1][j-1] = true;
                Estado temp = new Estado(i-1, j-1, actual.custo+1, n, m, actual.visitado);
                temp.AddPar(actual.caminho, i, j);
                queue.add(temp);
            } if ( i-1>=0 && j+1<m && !actual.visitado[i-1][j+1] && grid[i-1][j+1]==0 ) {
                actual.visitado[i-1][j+1] = true;
                Estado temp = new Estado(i-1, j+1, actual.custo+1, n, m, actual.visitado);
                temp.AddPar(actual.caminho, i, j);
                queue.add(temp);
            } if ( i+1<n && j-1>=0 && !actual.visitado[i+1][j-1] && grid[i+1][j-1]==0 ) {
                actual.visitado[i+1][j-1] = true;
                Estado temp = new Estado(i+1, j-1, actual.custo+1, n, m, actual.visitado);
                temp.AddPar(actual.caminho, i, j);
                queue.add(temp);
            } if ( i+1<n && j+1<m && !actual.visitado[i+1][j+1] && grid[i+1][j+1]==0 ) {
                actual.visitado[i+1][j+1] = true;
                Estado temp = new Estado(i+1, j+1, actual.custo+1, n, m, actual.visitado);
                temp.AddPar(actual.caminho, i, j);
                queue.add(temp);
            }
        }
    }
    
    void printResult ( ) {
        System.out.println("\n\nResultado Depth First Search!");
        System.out.print("Custo caminho optimo: " + caminhoOpt.size() + "\nCaminho Optimo: {");
        for(int i=0; i<caminhoOpt.size(); i++) {
            System.out.print("(" + caminhoOpt.get(i).bidao4 + ", " + caminhoOpt.get(i).bidao3 + ")");
            if ( i!=caminhoOpt.size()-1 ) System.out.print(" ");
        } System.out.println("}");
    } // #EndOfPrintResult!
    
    void writeFile ( ) throws IOException {
        File file = new File("Output/BFS-Result.txt");
        
        if ( !file.exists() ) 
            file.createNewFile();
        
        FileWriter fw = new FileWriter(file);
        BufferedWriter bw = new BufferedWriter(fw);
        
        bw.write("Resultado Depth First Search"); bw.newLine();
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
