/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MessageCenter.tcp;

import java.util.Scanner;

/**
 *
 * @author teague
 */
public class TcpConnectionReader implements Runnable {
    private final Scanner input;
    
    public TcpConnectionReader(Scanner input){
        this.input = input;
    }
    
    @Override
    public void run() {
        while(input.hasNext()){
            System.out.println("Read: " + input.nextLine());
        }
    }
    
}
