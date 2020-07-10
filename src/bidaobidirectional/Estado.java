package bidaobidirectional;

import java.util.ArrayList;

public class Estado {
    
    int bidao4, bidao3, custo;
    ArrayList<Par> caminho;
    boolean[][] visitado;
    
    Estado ( int fi, int se, int c, int n, int m, boolean v[][] ) {
        bidao4 = fi;
        bidao3 = se;
        custo = c;
        caminho = new ArrayList<Par>();
        
        visitado = new boolean[n][m];
        for(int i=0; i<n; i++) {
            System.arraycopy(v[i], 0, visitado[i], 0, m);
        }
    }
    
    void AddPar ( ArrayList<Par> visited, int i, int j ) {
        for(int x=0; x<visited.size(); x++) 
            caminho.add(visited.get(x));
        
        caminho.add(new Par(i, j));
    }
    
}
