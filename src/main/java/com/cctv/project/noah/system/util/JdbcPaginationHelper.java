package com.cctv.project.noah.system.util;
import com.cctv.project.noah.system.core.domain.page.PageDomain;
import com.github.pagehelper.*;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import java.util.*;

/**
 * @author by yanhao
 * @Classname JdbcPaginationHelper
 * @Description 分页
 * @Date 2019/9/24 5:10 下午
 */
public class JdbcPaginationHelper{

    public static Page<Map<String, Object>> queryPage(NamedParameterJdbcTemplate jdbcTemplate, String sql, Map<String,Object> parament, PageDomain pageDomain){
        Page<Map<String, Object>> pageInfo=new Page<>();
        if(pageDomain==null){
            pageDomain=new PageDomain();
            pageDomain.setPageNum(1);
            pageDomain.setPageSize(10);
        }
        //
        Map<String,Object> objectMap=jdbcTemplate.queryForMap("select count(1) count from ("+sql+") t",parament);
        if(objectMap!=null&&objectMap.get("count")!=null){
            pageInfo.setTotal((Long) objectMap.get("count"));
        }else{
            pageInfo.setTotal(0);
        }
        int start=(pageDomain.getPageNum()-1)*pageDomain.getPageSize();
        if(StringUtils.isNotEmpty(pageDomain.getOrderBy())){
            sql+=" ORDER BY "+pageDomain.getOrderBy();
        }
        String pageSql=sql+" LIMIT "+start+","+pageDomain.getPageSize();
        System.out.println(sql);
        List<Map<String, Object>> mapList=jdbcTemplate.queryForList(pageSql,parament);
        pageInfo.addAll(mapList);
        return pageInfo;
    }


}
