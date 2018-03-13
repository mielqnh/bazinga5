package be.mielnoelanders.bazinga.service;

import be.mielnoelanders.bazinga.domain.Publisher;

public interface PublisherService {
    // Insert new Parameter //
    Publisher addPublisher(Publisher publisher);

    // Get all publisher //
    Iterable<Publisher> getAll();

    // Find unique by publisher type //
    Publisher findByName(String name);

    // Update publisher by type //
    boolean updatePublisherByName(Publisher publisher);

    // Delete publisher by id //
    boolean deletePublisher(long id);
}
