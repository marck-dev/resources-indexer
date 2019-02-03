package resourcesIndexer;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Create the output file
 */
public class Builder {
    //    firma de las subclases
    private static final String  SUBCLASS_SIGN =
            "\n\tpublic static final class %s{ ";
    //    firma para las variables
    private static final String VARIABLE_SIGN =
            "\n\t\tpublic static final String %s = \"%s\";";

    private static final String PACKAGE_SIGN = "package %s;\n";
    private static final String CLASS_SIGN_OPEN = "public final class %s{ ";
    private static final String CLASS_SIGN_CLOSE = "}";


    private BuildOption op;
    private BufferedWriter outFile;

    /**
     * Constructor
     * @param option BuildOption that set al the configuration
     */
    public Builder( BuildOption option ){
        this.op = option;
    }

    /**
     * create the src dirs for package
     */
    private void createDirs(){
        String dir = op.getOutDir();
        File dirs = new File( dir );
        dirs.mkdirs();
    }

    private void writeHead() throws IOException {
        String pack = String.format( PACKAGE_SIGN, op.getPackageName() );
        String classSign =  String.format( CLASS_SIGN_OPEN, op.getClassName() );
        outFile.write( pack );
        outFile.write( classSign );
    }

 //   crea la subclase para el directorio que se le pasa
    private void writeSubclass( String dir ) throws IOException {
        File mainDir = new File( op.getResourcesDir(), dir );
        String subHead = String.format( SUBCLASS_SIGN, dir );
        outFile.write( subHead );
        String[] list = mainDir.list();
        for ( String file: list ){
            File f = new File( mainDir, file );
            if ( f.isFile() ) {
                String path = op.getResourcesDir() + "/" + dir + "/" + file;
                outFile.write(String.format( VARIABLE_SIGN, file.split("\\.")[0], path ) );
            }
        }
        outFile.write( "\n\t\t}" );
    }

    /**
     * Write all class
     * @throws IOException
     */
    private void writeAllSubClass() throws IOException {
        for (String dir: getDirs()
             ) {
            writeSubclass( dir );
        }

    }

    public void build() throws IOException {
        removeFile();
        createDirs();
        initBuffer();
        writeHead();
        writeAllSubClass();
        outFile.write( "\n" + CLASS_SIGN_CLOSE );
        outFile.close();
    }

    /**
     *
     * @return String[] with all resources folder directories
     */
    private String[] getDirs(){
        ArrayList<String> tmp = new ArrayList<String>();
        File mainDir = new File( op.getResourcesDir() );
        String[] ls = mainDir.list();
        for ( String file : ls ){
            if( new File( mainDir, file).isDirectory() )
                tmp.add( file );
        }
        String[] out = new String[tmp.size()];
        tmp.toArray( out );
        return  out;
    }

    /**
     * Remove, if exist, the older file
     */
    private void removeFile(){
        File file = new File( op.getOutDir(), op.getFileName() );
        if( file.exists() ){
            file.delete();
        }
    }

    /**
     * Start the bufferedWriter
     * @throws IOException
     */
    private void initBuffer() throws IOException {
        String mainDir = op.getOutDir();
        File file = new File( new File(mainDir), op.getFileName() );
        outFile = new BufferedWriter( new FileWriter( file, true ) );
    }
}
