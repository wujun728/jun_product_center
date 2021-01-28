package org.myframework.support.idgenerator.dao;

import org.myframework.support.idgenerator.entities.Series;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SeriesDao  extends JpaRepository<Series, String>{

}
