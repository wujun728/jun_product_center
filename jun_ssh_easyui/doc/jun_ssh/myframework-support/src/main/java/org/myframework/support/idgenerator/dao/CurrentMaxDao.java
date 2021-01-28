package org.myframework.support.idgenerator.dao;

import org.myframework.support.idgenerator.entities.CurrentMax;
import org.myframework.support.idgenerator.entities.CurrentMax.CurrentMaxPK;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CurrentMaxDao extends JpaRepository<CurrentMax, CurrentMaxPK>{

}
