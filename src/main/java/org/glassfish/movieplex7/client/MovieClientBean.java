/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.glassfish.movieplex7.client;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.enterprise.context.RequestScoped;
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

    private int movieId;

    @PostConstruct
    public void init() {
        client = ClientBuilder.newClient();
        target = client.target("http://localhost:8080/movieplex7/webresources/movie/");
    }

    @PreDestroy
    public void destroy() {
        client.close();
    }

    public int getMovieId() {
        return movieId;
    }

    public void setMovieId(int movieId) {
        this.movieId = movieId;
    }

    public Movie[] getMovies() {
        return target.request().get(Movie[].class);
    }
}
