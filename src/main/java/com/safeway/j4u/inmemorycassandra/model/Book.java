package com.safeway.j4u.inmemorycassandra.model;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import org.springframework.data.cassandra.core.cql.PrimaryKeyType;
import org.springframework.data.cassandra.core.mapping.Column;
import org.springframework.data.cassandra.core.mapping.PrimaryKeyColumn;
import org.springframework.data.cassandra.core.mapping.Table;

@Table("Book")
public class Book {

    @PrimaryKeyColumn(name = "id", ordinal = 0, type = PrimaryKeyType.CLUSTERED)
    private UUID id;

    @PrimaryKeyColumn(name = "title", ordinal = 1, type = PrimaryKeyType.PARTITIONED)
    private String title;

    @PrimaryKeyColumn(name = "publisher", ordinal = 2, type = PrimaryKeyType.PARTITIONED)
    private String publisher;
    
    


    public Book() {
		super();
	}

	public Book(final UUID id, final String title, final String publisher) {
        this.id = id;
        this.title = title;
        this.publisher = publisher;
    }

    public UUID getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getPublisher() {
        return publisher;
    }


    public void setId(final UUID id) {
        this.id = id;
    }

    public void setTitle(final String title) {
        this.title = title;
    }

    public void setPublisher(final String publisher) {
        this.publisher = publisher;
    }


}
