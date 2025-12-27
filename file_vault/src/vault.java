package file_vault.src;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;

public class vault {
    public static void main(String[] args) {
        
        
        if(args.length == 0 || args[0].equals("help")){
            help();
            return;
        }

        switch(args[0]){
            case "init":
                initialize();
                break;
            case "add":
                if (args.length < 2) {
                    System.out.println("Usage: vault add <file_path>");
                    return;
                }
                addFile(args[1]);
                break;
            case "get":
                System.out.println("getting");
                break;
            case "list":
                System.out.println("liste");
                break;
            default:
                help();
            
        }
    }

    public static void listFiles(){
        File vaultDir = new File("vault");

        if (!vaultDir.exists()) {
            System.out.println("initialize the vault first");
        }
    }

    public static void addFile(String srcFilePath){

        File vaultDir = new File("vault");

        if (!vaultDir.exists()) {
            System.out.println(" initialize the vault first : vault init");
        }

        File sourceFile = new File(srcFilePath);

        if (!sourceFile.exists() || !sourceFile.isFile()) {

            System.out.println("File doesnot exists");

        } else {

            //making path class obj with file obj and making it as a path obj 
            // simply we are getting the path of the file (destination file)

            Path destinationPath = new File(vaultDir, sourceFile.getName()).toPath();
            
            try {
                // File.copy only works with path obj not file obj
                Files.copy(sourceFile.toPath(), destinationPath, StandardCopyOption.REPLACE_EXISTING);
            
            } catch (Exception e) {

                System.out.println("Fail to add file");
            
            }
        }
    }
    public static void initialize(){
        String vaultPath = "vault";
        File vaultDir = new File(vaultPath);

        if (vaultDir.exists()) {
            System.out.println("vault already initialized!");
        } else {
            boolean created = vaultDir.mkdirs();
            if (created) {
                System.out.println("vault created successfully");
            } else {
                System.out.println("fail to create vault; kindly try again");
            }
        }
    }
    public static void help(){
        System.out.println("init : vault initialize ");
        System.out.println("add  : add your file");
        System.out.println("list : list hidden file");
        System.out.println("get  : get your file");
        
    }
}
