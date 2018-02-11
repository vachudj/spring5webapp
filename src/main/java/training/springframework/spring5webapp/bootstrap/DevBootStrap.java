package training.springframework.spring5webapp.bootstrap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import training.springframework.spring5webapp.model.Author;
import training.springframework.spring5webapp.model.Book;
import training.springframework.spring5webapp.model.Publisher;
import training.springframework.spring5webapp.repositories.AuthorRepository;
import training.springframework.spring5webapp.repositories.BookRepository;
import training.springframework.spring5webapp.repositories.PublisherRepository;

@Component
public class DevBootStrap implements ApplicationListener<ContextRefreshedEvent> {

    private final AuthorRepository authorRepository;
    
    private final BookRepository bookRepository;

    private final PublisherRepository publisherRepository;

    @Autowired
    public DevBootStrap(AuthorRepository authorRepository, BookRepository bookRepository, PublisherRepository publisherRepository) {
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
        this.publisherRepository = publisherRepository;
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        initData();
    }

    private void initData() {
        Publisher publisher = new Publisher();
        publisher.setName("foo");

        publisherRepository.save(publisher);

        Author eric = new Author("Eric", "Evans");
        Book ddd = new Book("Domain Driven Design", "1234", publisher);
        eric.getBooks().add(ddd);
        ddd.getAuthors().add(eric);
        
        authorRepository.save(eric);
        bookRepository.save(ddd);
        
        Author rod = new Author("Rod", "Johnson");
        Book noEJB = new Book("J2EE Development wtihout EJB", "23444", publisher);
        rod.getBooks().add(noEJB);
        
        authorRepository.save(rod);
        bookRepository.save(noEJB);
    }
}
