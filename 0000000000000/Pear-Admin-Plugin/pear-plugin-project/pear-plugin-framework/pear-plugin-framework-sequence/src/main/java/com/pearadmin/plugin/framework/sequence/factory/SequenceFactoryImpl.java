package com.pearadmin.plugin.framework.sequence.factory;

import com.pearadmin.plugin.framework.sequence.entity.Sequence;
import com.pearadmin.plugin.framework.sequence.exception.SequenceException;

import java.util.HashSet;
import java.util.Set;

/**
 * Sequence 简 单 工 厂 -- [就眠仪式]
 * */
public class SequenceFactoryImpl implements SequenceFactory{

    /*** 机 器 Id */
    private long workerId;
    /*** 数 据 中 心 */
    private long centerId;
    /*** 毫 秒 内 序 列 */
    private long sequence = 0L;
    /*** 上 次 Id 生 成 的 时 间 戳 */
    private long lastTimestamp = -1L;

    /** 机 器 编 号 所 占 位 数 */
    private final long workerIdBits = 5L;
    /** 数 据 标 识 所 占 位 数 */
    private final long centerIdBits = 5L;
    /** 开 始 时 间 戳 */
    private final long poc = 1288834974657L;
    /** 序 列 在 Id 中 所 占 的 位 数 */
    private final long sequenceBits = 12L;

    /** 为 算 法 提 供 可 用 配 置 */
    private final long workerIdShift = sequenceBits;
    private final long maxWorkerId = -1L ^ (-1L << workerIdBits);
    private final long maxCenterId = -1L ^ (-1L << centerIdBits);
    private final long centerIdShift = sequenceBits + workerIdBits;
    private final long timestampLeftShift = sequenceBits + workerIdBits + centerIdBits;
    private final long sequenceMask = -1L ^ (-1L << sequenceBits);

    /**
     * 私 有 构 造 方 法
     * */
    public SequenceFactoryImpl(long workerId, long centerId) throws SequenceException{

        if (workerId > maxWorkerId || workerId < 0) {
            throw new SequenceException(String.format("worker Id can't be greater than %d or less than 0", maxWorkerId));
        }
        if (centerId > maxCenterId || centerId < 0) {
            throw new SequenceException(String.format("center Id can't be greater than %d or less than 0", maxCenterId));
        }
        this.workerId = workerId;   // 机 器 编 号
        this.centerId = centerId;   // 数 据 中 心
    }

    /**
     * 获 取 下 一 个 ID ( 该方法都是线程安全的 )
     * */
    public synchronized Sequence makeSequence() throws SequenceException{

        long timestamp = timeGen();
        // 当 前 时 间 小 于 上 次 Id 生 成 时 间 ,说 明 系 统 时 钟 回 退, 应 会 抛 出 异 常
        if (timestamp < lastTimestamp) {
            // 服 务 器 时 钟 被 调 整 了, Sequence 生 成 器 停 止 服 务
            throw new SequenceException(String.format("Clock moved backwards.  Refusing to generate id for %d milliseconds", lastTimestamp - timestamp));
        }

        // 如 果 是 同 一 时 间 生 成,则 进 行 毫 秒 内 序 列
        if (lastTimestamp == timestamp) {
            // 每 次 加 +
            sequence = (sequence + 1) & sequenceMask;
            // 毫 秒 内 序 列 溢 出
            if (sequence == 0) {
                // 阻塞到下一个毫秒,获取新的时间戳
                timestamp = tilNextMillis(lastTimestamp);
            }
        } else {
            sequence = 0L;
        }
        // 暂 存 当 前 时 间 戳 , 为 下 次 使 用  提 供 依 据
        lastTimestamp = timestamp;

        // 雪 花  算 法 核 心
        long currentId = ((timestamp - poc) << timestampLeftShift) | (centerId << centerIdShift) | (workerId << workerIdShift) | sequence;

        /** 序 列 对 象 */
        Sequence sequence = new Sequence();
        sequence.setCurrentId(currentId);
        sequence.setCurrentTime(timestamp);
        sequence.setCenterId(centerId);
        sequence.setWorkerId(workerId);
        return sequence;
    }

    /**
     * 批 量 获 取 Sequence
     * */
    @Override
    public Set<Sequence> makeSequence(int batchSize) throws SequenceException{

        Set<Sequence> sequences = new HashSet<>();

        for (long current = 0; current < batchSize; current++){
            sequences.add(makeSequence());
        }

        return sequences;
    }

    /**
     * 获 取 下 一 个 Id
     * */
    public long makeId() throws SequenceException{

        return makeSequence().getCurrentId();
    }

    /**
     * 根 据 一 定 数 量 的 Id
     * */
    public Set<Long> makeId(int initSize) throws SequenceException{

        Set<Long> ids = new HashSet<>(initSize);

        for (long current = 0; current < initSize; current++){
            ids.add(makeId());
        }

        return ids;
    }

    /**
     * 时 间 戳 比 对
     * */
    protected long tilNextMillis(long lastTimestamp) {
        long timestamp = timeGen();
        while (timestamp <= lastTimestamp) {
            timestamp = timeGen();
        }
        return timestamp;
    }

    /**
     * 当 前 系 统 时 间 毫 秒
     * */
    protected long timeGen() {

        return System.currentTimeMillis();
    }

}
