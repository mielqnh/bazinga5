package be.mielnoelanders.bazinga.service;

import be.mielnoelanders.bazinga.domain.Publisher;

public interface PublisherService {


// --> create (saveBla)



// --> read (findAllBla & findBlaById)



// --> update (updateBla)



// --> delete (deleteBlaById)



// --> others (findBlaByBuh)





    // Insert new Parameter //
    Publisher addPublisher(Publisher publisher);

    // Get all publisher //
    Iterable<Publisher> getAll();

    // Find unique by publisher type //
    Publisher findByName(String name);

    // Update publisher by type //
    //boolean updatePublisherByName(Publisher publisher);

    // Delete publisher by id //
    boolean deletePublisher(Long id);

    Publisher changePublisher(Long id, Publisher publisher);

    Publisher getOne(Long id);
}

