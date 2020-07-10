package bidaobidirectional;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import static bidaobidirectional.AStar.optimalPath;

public class IDS {
    static int min, u ,v;
    static int grid[][];
    static ArrayList<Par> optimalPath;
    
    IDS () {
        min = Integer.MAX_VALUE;
        optimalPath = new ArrayList<Par> ();
    }
    
    void iterativeDeepeningSearch ( int array[][], boolean visitado[][], int n, int m, int i, int j, int end_i, int end_j ) {
        // Copying grid to static array
        grid = array;
        
        // Creating Source and Destination Estados
        Estado dest = new Estado(end_i, end_j, 0, n, m, visitado);
        
        visitado[i][j] = true;
        Estado source = new Estado(i, j, 1, n, m, visitado);
        
        // Repeating until limit found
        for ( int limit=0; limit<=(n*m-2); limit++ ) {
            for ( int x=0; x<n; x++ ) 
                for ( int y=0; y<m; y++ ) source.visitado[x][y] = false;
        
            // Depth Limited Search
            depthLimitedSearch(source, dest, limit, n, m);
        }
        
    }
    
    // Private Function for Iterative Deepening Algorithm
    private boolean depthLimitedSearch ( Estado actual, Estado dest, int limit, int n, int m ) {
        int i = actual.bidao4;
        int j = actual.bidao3;
        
        // If values out of bound
        if ( limit<=0 || i<0 || j<0 || i>=n || j>=m )
            return false;
        
        // Goal Estado Test
        if ( i==dest.bidao4 && j==dest.bidao3 ) {
            if ( actual.custo < min ) {
                min = actual.custo;
                optimalPath = actual.caminho;
                
                optimalPath.add(new Par(i, j));
            }
            
            return true;
        }            
        
        // Horizontal and Vertical Conditional Search
        // Procura encher bidao4
        if ( i!=4 && !actual.visitado[4][j] ) {
            actual.visitado[4][j] = true;
            Estado next = new Estado(4, j, actual.custo+1, n, m, actual.visitado);
            next.AddPar(actual.caminho, i, j);
            depthLimitedSearch(next, dest, limit-1, n, m);
        }
        
        // Procura encher bidao3
        if ( j!=3 && !actual.visitado[i][3] ) {
            actual.visitado[i][3] = true;
            Estado next = new Estado(i, 3, actual.custo+1, n, m, actual.visitado);
            next.AddPar(actual.caminho, i, j);
            depthLimitedSearch(next, dest, limit-1, n, m);
        }
        
        // Procura Despejar bidao4
        if ( i!=0 && !actual.visitado[0][j] ) {
            actual.visitado[0][j] = true;
            Estado next = new Estado(0, j, actual.custo+1, n, m, actual.visitado);
            next.AddPar(actual.caminho, i, j);
            depthLimitedSearch(next, dest, limit-1, n, m);
        }
        
        // Procura Despejar bidao3
        if ( j!=0 && !actual.visitado[i][0] ) {
            actual.visitado[i][0] = true;
            Estado next = new Estado(i, 0, actual.custo+1, n, m, actual.visitado);
            next.AddPar(actual.caminho, i, j);
            depthLimitedSearch(next, dest, limit-1, n, m);
        }
        
        // Procura Transferir 43
        if ( i!=0 ) {
            u=i;
            v=j;
            while(v<3){
                if(u>0){
                    v++;
                    u--;
                }else break;                    
            }
            if(!actual.visitado[u][v]){
                actual.visitado[u][v] = true;
                Estado next = new Estado(u,v, actual.custo+1, n, m, actual.visitado);
                next.AddPar(actual.caminho, i, j);
                depthLimitedSearch(next, dest, limit-1, n, m);
            }
        }
        
        // Procura Transferir 34
        if ( j!=0 ) {
            u=i;
            v=j;
            while(u<4){
                if(v>0){
                    u++;
                    v--;
                }else break;
            }
            if(!actual.visitado[u][v]){
                actual.visitado[u][v] = true;
                Estado next = new Estado(u, v, actual.custo+1, n, m, actual.visitado);
                next.AddPar(actual.caminho, i, j);
                depthLimitedSearch(next, dest, limit-1, n, m);
            }

        }
        
        /*
        // Diagonal and Anti-Diagonal Conditional Search
        if ( i-1>=0 && j-1>=0 && !actual.visitado[i-1][j-1] && grid[i-1][j-1]==0 ) {
            actual.visitado[i-1][j-1] = true;
            Estado next = new Estado(i-1, j-1, actual.custo+1, n, m, actual.visitado);
            next.AddPar(actual.caminho, i, j);
            depthLimitedSearch(next, dest, limit-1, n, m);
        } if ( i-1>=0 && j+1<m && !actual.visitado[i-1][j+1] && grid[i-1][j+1]==0 ) {
            actual.visitado[i-1][j+1] = true;
            Estado next = new Estado(i-1, j+1, actual.custo+1, n, m, actual.visitado);
            next.AddPar(actual.caminho, i, j);
            depthLimitedSearch(next, dest, limit-1, n, m);
        } if ( i+1<n && j-1>=0 && !actual.visitado[i+1][j-1] && grid[i+1][j-1]==0 ) {
            actual.visitado[i+1][j-1] = true;
            Estado next = new Estado(i+1, j-1, actual.custo+1, n, m, actual.visitado);
            next.AddPar(actual.caminho, i, j);
            depthLimitedSearch(next, dest, limit-1, n, m);
        } if ( i+1<n && j+1<m && !actual.visitado[i+1][j+1] && grid[i+1][j+1]==0 ) {
            actual.visitado[i+1][j+1] = true;
            Estado next = new Estado(i+1, j+1, actual.custo+1, n, m, actual.visitado);
            next.AddPar(actual.caminho, i, j);
            depthLimitedSearch(next, dest, limit-1, n, m);
        }*/
        
        // If nothing is searches on this level
        return false;
    }
    
    void printResult ( ) {
        System.out.println("\n\nIterative Deepening Search Result!");
        System.out.print("Optimal Path Cost: " + optimalPath.size() + "\nOptimal Path: {");
        for(int i=0; i<optimalPath.size(); i++) {
            System.out.print("(" + optimalPath.get(i).bidao4 + ", " + optimalPath.get(i).bidao3 + ")");
            if ( i!=optimalPath.size()-1 ) System.out.print(" ");
        } System.out.println("}");
    } // #EndOfPrintResult!
    
    void writeFile ( ) throws IOException {
        File file = new File("Output/IDS-Result.txt");
        
        if ( !file.exists() ) 
            file.createNewFile();
        
        FileWriter fw = new FileWriter(file);
        BufferedWriter bw = new BufferedWriter(fw);
        
        bw.write("Iterative Deepening Search Result!"); bw.newLine();
        bw.write("Optimal Path Cost: " + optimalPath.size()); bw.newLine();
        bw.write("Optimal Path: {");
        for(int i=0; i<optimalPath.size(); i++) {
            bw.write("(" + optimalPath.get(i).bidao4 + ", " + optimalPath.get(i).bidao3 + ")");
            if ( i!=optimalPath.size()-1 ) bw.write(" ");
        } bw.write("}"); bw.newLine();
        
        bw.flush();
        bw.close();
        fw.close();
    } // #EndOfWriteFile!
}
