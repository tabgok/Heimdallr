
package SystemResourceGenerator;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.TimerTask;

/**
 * This class is responsible for running a single system command and returning
 * the value to it's parent SystemResourceGenerator.
 * @author tbick
 */
public class SystemResourceGeneratorTask extends TimerTask{
    private SystemResourceGenerator generator = null;
    private String systemCall = null;
    
    /**
     * Create a new task - passing in the parent generator and the command to
     * be run.
     * @param generator The parent generator
     * @param systemCall The command to run
     */
    public SystemResourceGeneratorTask(SystemResourceGenerator generator, String systemCall){
        this.generator = generator;
        this.systemCall = systemCall;
    }

    /**
     * 1) Create a process
     * 2) Tie into the process's stdout
     * 3) Run the process and get the stdout
     * 4) Give the stdout to the parent generator.
     */
    @Override
    public void run() {
        StringBuilder output = new StringBuilder();
        
        Process p;
        try{
            p = Runtime.getRuntime().exec(systemCall);
            p.waitFor();
            
            BufferedReader br = 
                    new BufferedReader(new InputStreamReader(p.getInputStream()));
            
            String line = "";
            
            while( (line = br.readLine()) != null){
                output.append(line);
            }
        }catch(IOException | InterruptedException e){
            System.out.println("There was an error running the comand");
        }
        String value = output.toString();
        
        //System.out.println(value);
        generator.receiveData(value);
    }
    
    
}
