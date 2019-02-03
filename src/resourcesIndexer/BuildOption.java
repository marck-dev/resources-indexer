package resourcesIndexer;

public class BuildOption {

    //    nombre de la clase
    private String className;
    //    nombre del archivo de salida
    private String fileName;
    //    paquete en que se generara el archivo
    private String packageName;
    //    directorio del archivo
    private String dir;

    private String resourcesDir;

    private BuildOption( String className, String fileName, String packageName, String resourcesDirdir, String dirOut ) {
        this.className = className;
        this.fileName = fileName;
        this.packageName = packageName;
        this.resourcesDir = resourcesDirdir;
        this.dir = dirOut;
    }

    public String getClassName() {
        return className;
    }

    public String getFileName() {
        return fileName;
    }

    public String getPackageName() {
        return packageName;
    }

    public String getOutDir() {
        return dir;
    }

    public String getResourcesDir(){
        return this.resourcesDir;
    }

    public static final class Builder{
        private String pack = "resources";
        private String className = "R";
        private String file = className + ".java";
        private String resucesDir = "resources";

        /**
         * Set the package name
         * @param name for package
         * @return the actual instance
         */
        public Builder setPackageName( String name ){
            pack = name;
            return this;
        }

        /**
         * Set the class name
         * @param name of class
         * @return the actual instance
         */
        public Builder setClassName( String name ){
            className = name;
            file = className + ".java";
            return this;
        }

        /**
         * Set the resources dir
         * @param path of the resources folder
         * @return the actual instance
         */
        public Builder setResourcesDir( String path ){
            resucesDir = path // quitamos las barras de directorio
                    .replace( "/", "" )
                    .replace( "\\", "" );
            return this;
        }

        /**
         * Build the options
         * @return BuildOption object
         */
        public BuildOption build(){
            String dir = createDirTree();
            return new BuildOption( className, file, pack, resucesDir, dir );
        }

        private String createDirTree(){
            String dir ="src/";
            String[] dirs = pack.split( "\\." );
            for ( String d:
                    dirs) {
                dir += d + "/";
            }
            return dir;
        }
    }
}
