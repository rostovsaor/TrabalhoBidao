/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package extra;

/**
 *
 * @author Admin
 */
public class Actor {
    private int bidao4 = 0;
    private int bidao3 = 0;
    private boolean goal = false;
    int iteracao[][] = new int [4][5];
    

    public Actor() {
    }

    public int getBidao4() {
        return bidao4;
    }

    public void setBidao4(int bidao4) {
        this.bidao4 = bidao4;
    }

    public int getBidao3() {
        return bidao3;
    }

    public void setBidao3(int bidao3) {
        this.bidao3 = bidao3;
    }
    
    public void encher4(){
        this.bidao4 = 4;
    }
    
    public void encher3(){
        this.bidao3 = 3;
    }
    
    public void trans43(){
        while (this.bidao3<3){
            if (this.bidao4>0){
                this.bidao3++;
                this.bidao4--;
            }else break;
            
        }
    }
    
    public void trans34(){
        while (this.bidao4<4){
            if (this.bidao3>0){
                this.bidao4++;
                this.bidao3--;
            }else break;
            
        }
    }
    
    public void desp4(){
        this.bidao4=0;
    }
    
    public void desp3(){
        this.bidao3=0;
    }
    
    public void ver(){
        if (this.bidao4 == 2) 
            goal = true;
        
    }
    
}
