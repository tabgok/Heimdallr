
package SystemResourceGenerator;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.util.Arrays;
import java.util.Timer;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.json.Json;

/**
 * The system resource generator is responsible for periodically running a system
 * command, converting the returned data into JSON format, then publishing the
 * data.
 * 
 * @author tbick
 */
public class SystemResourceGenerator {
    private String systemCommand = null;
    private SystemResourceGeneratorTask srgt;
    private static SystemResourceGenerator srg;
    private Object value;
    private long period;
    private String name = null;
    
    //This may changed later, currently using files for IPC
    private File outputFile = null;
    
    /**
     * Creates a new generator which runs system calls.
     * @param name The name of the generator (i.e. "date")
     * @param systemCommand The command to run (i.e. "date +%s")
     * @param period  The period at which to run the call, in ms.
     */
    private SystemResourceGenerator(String name, String systemCommand, long period){
        this.systemCommand = systemCommand;
        this.period = period;
        this.name = name;
        
        initialize();
    }
    
    /**
     * 1) Create the output directory and grab the file
     * 2) Start a timertask to run at the specified period
     */
    private void initialize(){
        //Create filepath and file
        if( !new File("./command_output").exists() ){
            new File("./command_output").mkdir();
        }
        
        outputFile = new File("./command_output/" + name);
        srgt = new SystemResourceGeneratorTask(this, systemCommand);
        
        Timer timer = new Timer();
        
        //Set the command to run periodically, or a single time.
        if(period  > 0){
            timer.scheduleAtFixedRate(srgt, 0, period);
        }else{
            timer.schedule(srgt, 0);
        }
    }
    
    /**
     * This is where the time task returns data.  This class accepts the 
     * data then publishes it.
     * @param value 
     */
    protected void receiveData(Object value){
        this.value = value;
        System.out.println("Generated value: " + value);
        
        publish();
    }
    
    /**
     * Convert the data to JSON (for now, this is hardcoded) and "publish" the
     * data (for now, it is just written out to a file).
     */
    private void publish(){
        try {
            String json = Json.createObjectBuilder()
                    .add("value", value.toString())
                    .add("unit", "seconds")
                    .add("timestamp_sec", value.toString())
                    .build().toString();
            Files.write(outputFile.toPath(), Arrays.asList(json), Charset.forName("UTF-8"));
        } catch (IOException ex) {
            Logger.getLogger(SystemResourceGenerator.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    public static void main(String args[]){
        if(args.length != 3){
            System.out.println("TODO: Usage");
            System.exit(-1);
        }
        
        srg = new SystemResourceGenerator(args[0], args[1], Long.parseLong(args[2]));
    }
}
