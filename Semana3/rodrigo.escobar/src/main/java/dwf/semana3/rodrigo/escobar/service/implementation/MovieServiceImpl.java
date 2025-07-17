package dwf.semana3.rodrigo.escobar.service.implementation;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import dwf.semana3.rodrigo.escobar.repository.MovieRepository;
import dwf.semana3.rodrigo.escobar.repository.domain.Movie;
import dwf.semana3.rodrigo.escobar.service.MovieService;
import java.util.Objects;
@Getter
@Service
public class MovieServiceImpl implements MovieService {
    private MovieRepository movieRepository;
    @Autowired
    public void setMovieRepository(final MovieRepository movieRepository) {
        this.movieRepository = Objects.requireNonNull(movieRepository);
    }
    @Override
    public Movie findMovieById(final Long id) {
        return movieRepository.findMovieById(id);
    }
}