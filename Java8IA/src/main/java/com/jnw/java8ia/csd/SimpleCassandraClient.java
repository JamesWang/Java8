package com.jnw.java8ia.csd;

import com.datastax.driver.core.Cluster;
import com.datastax.driver.core.Host;
import com.datastax.driver.core.Metadata;
import com.datastax.driver.core.ResultSet;
import com.datastax.driver.core.Row;
import com.datastax.driver.core.Session;

public class SimpleCassandraClient {
	private Cluster cluster;
	private Session session;
	
	public void connect( String node ) {
		cluster = Cluster.builder().addContactPoint(node).build();
		
		Metadata metadata = cluster.getMetadata();
		for( Host host: metadata.getAllHosts() ){
			System.out.printf("Datatacenter: %s; Host: %s; Rack: %s\n",
					host.getDatacenter(), host.getAddress(), host.getRack());
		}
		session = cluster.connect();
	}
	public void close(){
		session.close();
		cluster.close();
	}
	private void querySchema() {
		ResultSet result = session.execute("SELECT * FROM mykeyspace.USERS");
		for( Row r : result) {
			System.out.println("last name: " + r.getString("lname") +"  first name:" + r.getString("fname"));
		}
	}
	public static void main(String[] args ) {
		SimpleCassandraClient scc = new SimpleCassandraClient();
		try{ 
			scc.connect("127.0.0.1");
			scc.querySchema();
		} finally {
			scc.close();
		}
	}
}
