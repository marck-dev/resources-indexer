package main;

import resourcesIndexer.BuildOption;
import resourcesIndexer.Builder;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Scanner;

public class Main {
  private static final Scanner sc = new Scanner( System.in );

  private static void help(){
    String msg = " RESOURCES INDEXER\n" +
		"\t by Marck C. Guzman : marck.guzman.dev@gmail.com\n" +
		"\nResourcesIndexer [options]" +
		"\n\t--ask, -a \tFor ask all required data from terminal." +
		"\n\t--packageName, -p <value> \tSet the package name, out java file package." +
		"\n\t--className, -c <value>\tSet the output java file name." +
		"\n\t--resurcesDir, -r <value>\tSet the resources folder that will be index\n\n";
    System.out.println( msg );
    sc.close();
  }

  public static void main( String... args ){
    if ( args.length == 0 ) {
	  help();
	  return;
	}

	ArrayList<String> arguments = new ArrayList<String>( Arrays.asList(args) );
	BuildOption.Builder builder = new BuildOption.Builder();

	if( arguments.contains( "--ask" ) || arguments.contains( "-a" ) ){
	  System.out.println( "To select the default value press ENTER" );
	  String userIn = null;
	  System.out.print( "Class name [R]: " );
	  userIn = sc.nextLine();
	  if ( !userIn.isEmpty() )
	  	builder.setClassName( userIn );
	  System.out.print( "Package name [Resources]: " );
	  userIn = sc.nextLine();
	  if ( !userIn.isEmpty() )
	  	builder.setPackageName( userIn );
	  System.out.print( "Resources directory [resources/]: " );
	  userIn = sc.nextLine();
	  if ( !userIn.isEmpty() )
	  	builder.setResourcesDir( userIn );
	}else{
	  if( arguments.contains( "--packageName" ) ||arguments.contains( "-p" ) ){
//	      posicion del argumento, la siguiente posicion sera el valor
	    int argumentPos = arguments.indexOf( (arguments.contains( "-p" ) )? "-p": "--packageName" );
//	     si no hay mas datos salimos
	    if( argumentPos == ( arguments.size() - 1 ) ) {
		  help();
		  return;
		}else
	      builder.setPackageName( arguments.get( argumentPos + 1 ) );
	  }
	  if( arguments.contains( "--className" ) ||arguments.contains( "-c" ) ) {
//	      posicion del argumento, la siguiente posicion sera el valor
		int argumentPos = arguments.indexOf( ( arguments.contains( "-c" ) ) ? "-c" : "--className" );
		if ( argumentPos == ( arguments.size() - 1 ) ){
		  help();
		  return;
	  	}else
		  builder.setClassName( arguments.get( argumentPos + 1 ) );
	  }
	  if( arguments.contains( "--resourcesDir" ) ||arguments.contains( "-r" ) ){
//	      posicion del argumento, la siguiente posicion sera el valor
		int argumentPos = arguments.indexOf( (arguments.contains( "-r" ) )? "-r": "--resourcesDir" );
		if( argumentPos == ( arguments.size() - 1 ) ) {
		  help();
		  return;
		}else
		  builder.setResourcesDir( arguments.get( argumentPos + 1 ) );
	  }
	}

//	    construimos las opciones
	BuildOption options = builder.build();
//	    creamos la clase
	Builder fileBuilder = new Builder( options );
	sc.close();
	try {
	  fileBuilder.build();
	} catch ( IOException e ) {
	  System.out.println( e.getCause().toString()  );
	}

  }

}
