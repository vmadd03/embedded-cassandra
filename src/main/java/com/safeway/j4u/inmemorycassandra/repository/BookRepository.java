package com.safeway.j4u.inmemorycassandra.repository;

import java.util.UUID;

import org.springframework.data.cassandra.repository.CassandraRepository;
import org.springframework.data.cassandra.repository.Query;
import org.springframework.stereotype.Repository;

import com.safeway.j4u.inmemorycassandra.model.Book;

@Repository
public interface BookRepository extends CassandraRepository<Book,UUID> {

    @Query("select *  from inmemorykeyspace.book where title = ?0 and publisher=?1")
    Iterable<Book> findByTitleAndPublisher(String title, String publisher);
    
    @Query("insert into inmemorykeyspace.book(title,publisher) values(?0, ?1)")
    Iterable<Book> save(String title, String publisher);
    
    @Query("CREATE TABLE IF NOT EXISTS inmemorykeyspace.Book (id UUID,title varchar,publisher varchar,primary key((title, publisher)));")
    Iterable<Book> createTable();

}
