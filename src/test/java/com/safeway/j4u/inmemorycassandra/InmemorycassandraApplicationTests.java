package com.safeway.j4u.inmemorycassandra;

import static org.junit.Assert.assertEquals;

import java.io.IOException;

import org.apache.cassandra.exceptions.ConfigurationException;
import org.apache.thrift.transport.TTransportException;
import org.cassandraunit.spring.CassandraDataSet;
import org.cassandraunit.spring.CassandraUnitTestExecutionListener;
import org.cassandraunit.spring.EmbeddedCassandra;
import org.cassandraunit.utils.EmbeddedCassandraServerHelper;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.TestExecutionListeners.MergeMode;
import org.springframework.test.context.junit4.SpringRunner;

import com.datastax.driver.core.Cluster;
import com.datastax.driver.core.Session;
import com.safeway.j4u.inmemorycassandra.model.Book;
import com.safeway.j4u.inmemorycassandra.repository.BookRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("test")
@TestExecutionListeners(listeners = CassandraUnitTestExecutionListener.class,mergeMode = MergeMode.MERGE_WITH_DEFAULTS)
@CassandraDataSet(keyspace = "inmemorykeyspace")
@EmbeddedCassandra(timeout = 60000)
public class InmemorycassandraApplicationTests {
	
	public static final String KEYSPACE_CREATION_QUERY = "CREATE KEYSPACE IF NOT EXISTS inmemorykeyspace WITH replication = { 'class': 'SimpleStrategy', 'replication_factor': '1' };";
	public static final String DATA_TABLE_NAME = "book";
	
	@Autowired
	Cluster cluster;
	
	@Autowired
	private BookRepository bookRepository;
	
	@Before
	public void startCassandraEmbedded() throws ConfigurationException, TTransportException, IOException, InterruptedException { 
        Session session = cluster.connect(); 
     	bookRepository.createTable();
	}
	
	@After
	public void stopCassandraEmbedded() {
	    EmbeddedCassandraServerHelper.cleanEmbeddedCassandra();
	}
	
	@Test
    public void testInsertAndFetch() throws InterruptedException, TTransportException, ConfigurationException, IOException {
		String title= "test title new";
		String publisher = "test publisher new";
		 // save a record
		 bookRepository.save(title, publisher);
		 //fetch the same record
		 Book savedBook = (Book) this.bookRepository.findByTitleAndPublisher(title, publisher).iterator().next();
		 assertEquals(savedBook.getTitle(),title);
		 assertEquals(savedBook.getPublisher(),publisher);
    }

}