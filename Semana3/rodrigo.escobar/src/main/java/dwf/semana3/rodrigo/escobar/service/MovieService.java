package dwf.semana3.rodrigo.escobar.service;

import dwf.semana3.rodrigo.escobar.repository.domain.Movie;
public interface MovieService {

    Movie findMovieById(final Long id);
}
