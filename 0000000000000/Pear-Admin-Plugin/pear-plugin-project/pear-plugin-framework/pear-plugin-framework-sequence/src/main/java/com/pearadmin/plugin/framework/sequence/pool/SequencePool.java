package com.pearadmin.plugin.framework.sequence.pool;

import com.pearadmin.plugin.framework.sequence.entity.Sequence;
import com.pearadmin.plugin.framework.sequence.exception.SequenceException;
import com.pearadmin.plugin.framework.sequence.factory.SequenceFactory;
import com.pearadmin.plugin.framework.sequence.factory.SequenceFactoryImpl;
import lombok.extern.slf4j.Slf4j;

import java.util.*;

/**
 * Sequence 资 源 池 -- [就眠仪式]
 * */
@Slf4j
public class SequencePool {

    /**
     * 初 始 化 配 置 信 息
     * */
    private SequencePoolConfig config;

    /**
     * 可 用 空 闲 的 Id 序 列 号
     * */
    private Queue<Sequence> queue = new LinkedList<Sequence>();

    /**
     * 存 放 工 厂 的 唯 一 实 例
     * */
    private SequenceFactory sequenceFactory;

    /**
     * 初 始 化 配 置 信 息
     * */
    public SequencePool(SequencePoolConfig config){

        try {

            this.config = config;

            sequenceFactory = new SequenceFactoryImpl(config.getWorkerId(), config.getCenterId());

        }catch (SequenceException se){

            se.printStackTrace();
        }
    }

    /**
     * 初 始 化 ID 资 源 池
     * */
    public void init() {

        try {

            log.info("Initialization sequence pool");

            Set<Sequence> sequenceIds = sequenceFactory.makeSequence(config.getInitSize());

            sequenceIds.forEach(item -> {

                queue.offer(item);

            });

        }catch (SequenceException e){

            e.printStackTrace();
        }
    }

    /**
     * 获 取 队 列 中 的 Id
     * */
    public synchronized Sequence getSequenceId(){

        isEnough(1);

        Sequence sequenceId = queue.poll();

        isEnough(config.getMinIdle());

        return sequenceId;
    }

    /**
     * 获 取 队 列 中 的 Id
     * */
    public List<Sequence> getSequenceId(int batchSize) {

        isEnough(batchSize);

        List<Sequence> sequencesIds = new ArrayList<>();

        for (int current = 0; current < batchSize; current++){

            sequencesIds.add(getSequenceId());
        }

        isEnough(config.getMinIdle());

        return sequencesIds;
    }

    /**
     * 获 取 队 列 Id
     * */
    public long getId(){

        long id = getSequenceId().getCurrentId();

        return id;
    }

    /**
     * 批 量 队 列  Id
     * */
    public List<Long> getId(int batchSize) {

        List<Long> ids = new ArrayList<>();

        List<Sequence> sequenceIds = getSequenceId(batchSize);

        sequenceIds.forEach(sequence->{

            ids.add(sequence.getCurrentId());
        });

        return ids;

    }

    /**
     * Id 数 量 是 否 满 足 配 置
     * */
    public void isEnough(int batchSize) {
        try {
            if (batchSize > getCount()) {
                Set<Sequence> sequenceIds = sequenceFactory.makeSequence(batchSize - getCount());
                sequenceIds.forEach(sequenceId -> {
                    queue.offer(sequenceId);
                });
            }
        }catch (SequenceException e){

            e.printStackTrace();
        }
    }

    /**
     * 获 取 当 前可 用 的 Id
     * */
    public int getCount() {

        return queue.size();

    }

}
