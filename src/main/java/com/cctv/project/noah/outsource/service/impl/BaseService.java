package com.cctv.project.noah.outsource.service.impl;

import com.cctv.project.noah.system.util.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class BaseService {
    Logger logger = LoggerFactory.getLogger(BaseService.class);


    public List<String> checkIds(String ids){
        if (StringUtils.isEmpty(ids)){
            return new ArrayList<>();
        }
        ids = ids.trim();
        List<String> idList = Arrays.asList(ids.split(","));
        for (String id : idList) {
            try {
                Integer.valueOf(id);
            } catch (NumberFormatException e) {
                logger.error("传入的id不合法，不合法id为<"+id+">");
                idList.remove(id);
            }
        }
        return idList;
    }
}
