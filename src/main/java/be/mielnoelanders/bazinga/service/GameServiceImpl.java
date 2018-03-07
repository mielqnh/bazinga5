package be.mielnoelanders.bazinga.service;

import be.mielnoelanders.bazinga.domain.Game;
import be.mielnoelanders.bazinga.repository.GameRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
public class GameServiceImpl implements GameService {

    private static final Logger LOGGER = LoggerFactory.getLogger(GameService.class);

    @Autowired
    private GameRepository repository;

    @Override
    public Iterable<Game> getAll() {
        return repository.findAll();
    }

    public Game getOne(Long id) {
        Optional<Game> result = repository.findById(id);
        return result.orElse(null);
    }
}
