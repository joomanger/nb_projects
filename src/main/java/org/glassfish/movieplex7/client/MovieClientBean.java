/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.glassfish.movieplex7.client;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import org.glassfish.movieplex7.entities.Movie;

/**
 *
 * @author savin
 */
@Named
@RequestScoped
public class MovieClientBean {

    private Client client;
    private WebTarget target;
    /*@Inject
    private MovieBackingBean bean;*/
    
    private int movieId;

    @PostConstruct
    public void init() {
        client = ClientBuilder.newClient();
        target = client.target("http://localhost:8080/nb_projects/webresources/movie/");
    }

    @PreDestroy
    public void destroy() {
        client.close();
    }

    public Movie[] getMovies() {
        return target.request().get(Movie[].class);
    }

    public Movie getMovie() {
        Movie m = target.path("{movie}").resolveTemplate("movie", getMovieId()).request().get(Movie.class);
        return m;
    }
    
    public void deleteMovie(){
        target.path("{movie}").resolveTemplate("movie", getMovieId()).request().delete();
    }

    public int getMovieId() {
        return movieId;
    }

    public void setMovieId(int movieId) {
        this.movieId = movieId;
    }
    
    
}
