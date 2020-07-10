package bidaobidirectional;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.PriorityQueue;

public class Greedy { 
    public static int min;
    public static int goal_i, goal_j;
    public static ArrayList<Par> optimalPath;
    
    Greedy ( ) {
        min = Integer.MAX_VALUE;
        optimalPath = new ArrayList<Par> ();
    }
    
    void GreedyBestFirst ( int grid[][], boolean visitado[][], int n, int m, int i, int j, int end_i, int end_j ) {
        // Initializing static goal index variables
        goal_i = end_i;
        goal_j = end_j; 
        
        // Creating a Priority Queue Data Structure
        Comparator<Estado> comparator = new GreedyParComparator();
        PriorityQueue<Estado> priorityQueue = new PriorityQueue<Estado>(comparator);
        
        // Adding Current Estado to Priority Queue
        visitado[i][j] = true;
        Estado start = new Estado(i, j, 0, n, m, visitado);
        priorityQueue.add(start);
        
        // Greedy Best First Algorithm
        while ( !priorityQueue.isEmpty() ) {
            Estado actual = priorityQueue.remove();
            
            i = actual.bidao4;
            j = actual.bidao3;
            
            // Goal Estado Test
            if ( actual.bidao4==goal_i && actual.bidao3==goal_j ) {
                if ( actual.custo < min ) {
                    min = actual.custo;
                    optimalPath = actual.caminho;
                    
                    optimalPath.add(new Par(i, j));
                }
            }
                
            // Horizontal and Vertical Conditional Search
            if ( i-1>=0 && !actual.visitado[i-1][j] && grid[i-1][j]==0 ) {
                actual.visitado[i-1][j] = true;
                Estado temp = new Estado(i-1, j, actual.custo+1, n, m, actual.visitado);
                temp.AddPar(actual.caminho, i, j);
                priorityQueue.add(temp);
            } if ( i+1<n && !actual.visitado[i+1][j] && grid[i+1][j]==0 ) {
                actual.visitado[i+1][j] = true;
                Estado temp = new Estado(i+1, j, actual.custo+1, n, m, actual.visitado);
                temp.AddPar(actual.caminho, i, j);
                priorityQueue.add(temp);
            } if ( j-1>=0 && !actual.visitado[i][j-1] && grid[i][j-1]==0 ) {
                actual.visitado[i][j-1] = true;
                Estado temp = new Estado(i, j-1, actual.custo+1, n, m, actual.visitado);
                temp.AddPar(actual.caminho, i, j);
                priorityQueue.add(temp);
            } if ( j+1<m && !actual.visitado[i][j+1] && grid[i][j+1]==0 ) {
                actual.visitado[i][j+1] = true;
                Estado temp = new Estado(i, j+1, actual.custo+1, n, m, actual.visitado);
                temp.AddPar(actual.caminho, i, j);
                priorityQueue.add(temp);
            }
            
            // Diagonal and Anti-Diagonal Conditional Search
            if ( i-1>=0 && j-1>=0 && !actual.visitado[i-1][j-1] && grid[i-1][j-1]==0 ) {
                actual.visitado[i-1][j-1] = true;
                Estado temp = new Estado(i-1, j-1, actual.custo+1, n, m, actual.visitado);
                temp.AddPar(actual.caminho, i, j);
                priorityQueue.add(temp);
            } if ( i-1>=0 && j+1<m && !actual.visitado[i-1][j+1] && grid[i-1][j+1]==0 ) {
                actual.visitado[i-1][j+1] = true;
                Estado temp = new Estado(i-1, j+1, actual.custo+1, n, m, actual.visitado);
                temp.AddPar(actual.caminho, i, j);
                priorityQueue.add(temp);
            } if ( i+1<n && j-1>=0 && !actual.visitado[i+1][j-1] && grid[i+1][j-1]==0 ) {
                actual.visitado[i+1][j-1] = true;
                Estado temp = new Estado(i+1, j-1, actual.custo+1, n, m, actual.visitado);
                temp.AddPar(actual.caminho, i, j);
                priorityQueue.add(temp);
            } if ( i+1<n && j+1<m && !actual.visitado[i+1][j+1] && grid[i+1][j+1]==0 ) {
                actual.visitado[i+1][j+1] = true;
                Estado temp = new Estado(i+1, j+1, actual.custo+1, n, m, actual.visitado);
                temp.AddPar(actual.caminho, i, j);
                priorityQueue.add(temp);
            }
        }
    }
    
    public static int euclidean_distance ( int x1, int y1, int x2, int y2 ) {
        return (int) Math.sqrt(Math.pow(x1-x2, 2) + Math.pow(y1-y2, 2));
    }
    
    void printResult ( ) {
        System.out.println("\n\nGreedy Best First Search Result!");
        System.out.print("Optimal Path Cost: " + optimalPath.size() + "\nOptimal Path: {");
        for(int i=0; i<optimalPath.size(); i++) {
            System.out.print("(" + optimalPath.get(i).bidao4 + ", " + optimalPath.get(i).bidao3 + ")");
            if ( i!=optimalPath.size()-1 ) System.out.print(" ");
        } System.out.println("}");
    } // #EndOfPrintResult!
    
    void writeFile ( ) throws IOException {
        File file = new File("Output/Greedy-Result.txt");
        
        if ( !file.exists() ) 
            file.createNewFile();
        
        FileWriter fw = new FileWriter(file);
        BufferedWriter bw = new BufferedWriter(fw);
        
        bw.write("Greedy Best First Search Result!"); bw.newLine();
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

class GreedyParComparator implements Comparator<Estado> {
    @Override
    public int compare ( Estado a, Estado b ) {
        int distance_a = Greedy.euclidean_distance(a.bidao4, b.bidao4, Greedy.goal_i, Greedy.goal_j);
        int distance_b = Greedy.euclidean_distance(b.bidao4, b.bidao3, Greedy.goal_i, Greedy.goal_j);
        
        // Greedy Heuristic: f(n) = g(n)
        if ( distance_a > distance_b ) return 1;
        else if ( distance_a < distance_b ) return -1;
        return 0;
    }
}