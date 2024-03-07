package ambovombe.kombarika.utils;

public class Command {
    public static String getOS(){
        return System.getProperty("os.name");
    }

    public static void executeCommand(String command) throws Exception{
        if(getOS().equals("Linux")){
            ProcessBuilder pb = new ProcessBuilder("sh", "-c", command);
            pb.start();
        }else if(getOS().equals("windows")){
            ProcessBuilder pb = new ProcessBuilder("cmd.exe", "/c", command);
            pb.start();
        }
    }
}
