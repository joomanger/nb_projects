/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.glassfish.movieplex7.booking;

import java.util.List;
import java.util.StringTokenizer;
import javax.faces.flow.FlowScoped;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import org.glassfish.movieplex7.entities.Movie;
import org.glassfish.movieplex7.entities.ShowTiming;

/**
 *
 * @author savin
 */
@Named
@FlowScoped("booking")
public class Booking {

    private int movieId;
    private String startTime;
    private int startTimeId;

    @PersistenceContext
    EntityManager em;

    /**
     * @return the movieId
     */
    public int getMovieId() {
        return movieId;
    }

    /**
     * @param movieId the movieId to set
     */
    public void setMovieId(int movieId) {
        this.movieId = movieId;
    }

    public String getMovieName() {
        try {
            TypedQuery<Movie> tq = em.createNamedQuery("Movie.findById", Movie.class).setParameter("id", movieId);
            return tq.getSingleResult().getName();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            return "";
        }

    }

    public int getStartTimeId() {
        return startTimeId;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        StringTokenizer tokens = new StringTokenizer(startTime, ",");
        startTimeId = Integer.parseInt(tokens.nextToken());
        this.startTime = tokens.nextToken();
    }

    public String getTheater() {
        try {
            List<ShowTiming> list = em.createNamedQuery("ShowTiming.findByMovieAndTimeslotId", ShowTiming.class)
                    .setParameter("movieId", movieId)
                    .setParameter("timingId", startTimeId)
                    .getResultList();
            if (list.isEmpty()) {
                return "none";
            }

            return list.get(0).getTheater().toString();
        } catch (Exception e) {
            return "none";
        }
    }

}
