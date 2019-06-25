package com.xmcc.dao.impl;

import com.xmcc.dao.BatchDao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

public class BatchDaoImpl<T> implements BatchDao<T> {

    @PersistenceContext
    private EntityManager em;

    @Override
    public void batchInsert(List<T> list) {

        int size = list.size();
        //循环放入缓存区
        for (int i=0;i<size;i++){
            //放入缓存区
            em.persist(list.get(i));
            //每一百条写入一次，若不足一百条就直接将全部数据写入
            if (i%100==0||i==size-1){
                em.flush();
                em.clear();
            }
        }



    }
}
