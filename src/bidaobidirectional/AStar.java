package bidaobidirectional;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.PriorityQueue;

public class AStar {
    public static int min, u, v;
    public static int goal_i, goal_j; 
    public static ArrayList<Par> optimalPath;
    
    AStar ( ) {
        min = Integer.MAX_VALUE;
        optimalPath = new ArrayList<Par> ();
    }
    
    void AStarSearch ( int grid[][], boolean visitado[][], int n, int m, int i, int j, int end_i, int end_j ) {
        // Initializing static goal index variables
        goal_i = end_i;
        goal_j = end_j; 
        
        // Creating a Priority Queue Data Structure
        Comparator<Estado> comparator = new AStarParComparator();
        PriorityQueue<Estado> priorityQueue = new PriorityQueue<Estado>(comparator);
        
        // Adding Current Estado to Priority Queue
        visitado[i][j] = true;
        Estado start = new Estado(i, j, 0, n, m, visitado);
        priorityQueue.add(start);
        
        // A* Search Algorithm
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
            if ( i!=4 && !actual.visitado[4][j] ) {
                actual.visitado[4][j] = true;
                Estado temp = new Estado(4, j, actual.custo+1, n, m, actual.visitado);
                temp.AddPar(actual.caminho, i, j);
                priorityQueue.add(temp);
            } if ( j!=3 && !actual.visitado[i][3] ) {
                actual.visitado[i][3] = true;
                Estado temp = new Estado(i, 3, actual.custo+1, n, m, actual.visitado);
                temp.AddPar(actual.caminho, i, j);
                priorityQueue.add(temp);
            } if ( i!=0 && !actual.visitado[0][j] ) {
                actual.visitado[0][j] = true;
                Estado temp = new Estado(0, j, actual.custo+1, n, m, actual.visitado);
                temp.AddPar(actual.caminho, i, j);
                priorityQueue.add(temp);
            } if ( j!=0 && !actual.visitado[i][0] ) {
                actual.visitado[i][0] = true;
                Estado temp = new Estado(i, 0, actual.custo+1, n, m, actual.visitado);
                temp.AddPar(actual.caminho, i, j);
                priorityQueue.add(temp);
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
                    Estado temp = new Estado(u, v, actual.custo+1, n, m, actual.visitado);
                    temp.AddPar(actual.caminho, i, j);
                    priorityQueue.add(temp);
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
                    Estado temp = new Estado(u, v, actual.custo+1, n, m, actual.visitado);
                    temp.AddPar(actual.caminho, i, j);
                    priorityQueue.add(temp);
                }
                
            }
            
            
           
        }
    }
    
    public static int euclidean_distance ( int x1, int y1, int x2, int y2 ) {
        return (int) Math.sqrt(Math.pow(x1-x2, 2) + Math.pow(y1-y2, 2));
    }
    
    void printResult ( ) {
        System.out.println("\n\nA* Search Result!");
        System.out.print("Optimal Path Cost: " + optimalPath.size() + "\nOptimal Path: {");
        for(int i=0; i<optimalPath.size(); i++) {
            System.out.print("(" + optimalPath.get(i).bidao4 + ", " + optimalPath.get(i).bidao3 + ")");
            if ( i!=optimalPath.size()-1 ) System.out.print(" ");
        } System.out.println("}");
    } // #EndOfPrintResult!
    
    void writeFile ( ) throws IOException {
        File file = new File("Output/AStar-Result.txt");
        
        if ( !file.exists() ) 
            file.createNewFile();
        
        FileWriter fw = new FileWriter(file);
        BufferedWriter bw = new BufferedWriter(fw);
        
        bw.write("A* Search Result!"); bw.newLine();
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


class AStarParComparator implements Comparator<Estado> {
    @Override
    public int compare ( Estado a, Estado b ) {
        int distance_a = Greedy.euclidean_distance(a.bidao4, b.bidao4, Greedy.goal_i, Greedy.goal_j);
        int distance_b = Greedy.euclidean_distance(b.bidao4, b.bidao3, Greedy.goal_i, Greedy.goal_j);
        
        // A* function: f(n) = g(n) + h(n)
        if ( distance_a+a.custo > distance_b+b.custo ) return 1;
        else if ( distance_a+a.custo < distance_b+b.custo ) return -1;
        return 0;
    }
}