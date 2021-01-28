package org.myframework.support.idgenerator.service;


public interface ID {
    /**
     * 
     * @param seriesID String
     * @param arguments String[], 
     * @return String
     */
    String getNext(String seriesID, String[] arguments);
}
