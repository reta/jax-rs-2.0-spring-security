package com.example;

import java.util.EnumSet;

import javax.servlet.DispatcherType;

import org.apache.cxf.transport.servlet.CXFServlet;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.FilterHolder;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.filter.DelegatingFilterProxy;

import com.example.config.AppConfig;

public class Starter {
	public static void main( final String[] args ) throws Exception {
		Server server = new Server( 8080 );
		        
 		// Register and map the dispatcher servlet
 		final ServletHolder servletHolder = new ServletHolder( new CXFServlet() );
 		final ServletContextHandler context = new ServletContextHandler(); 		
 		context.setContextPath( "/" );
 		context.addServlet( servletHolder, "/rest/*" ); 	
 		context.addEventListener( new ContextLoaderListener() );
 		
 		context.setInitParameter( "contextClass", AnnotationConfigWebApplicationContext.class.getName() );
 		context.setInitParameter( "contextConfigLocation", AppConfig.class.getName() );
 		
 		// Add Spring Security Filter by the name
 		context.addFilter(
 		    new FilterHolder( new DelegatingFilterProxy( "springSecurityFilterChain" ) ), "/*", 
 		    EnumSet.allOf( DispatcherType.class )
        );
 		 		 		
        server.setHandler( context );
        server.start();
        server.join();	
	}
}

