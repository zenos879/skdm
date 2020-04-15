package com.cctv.project.noah.outsource.service;



import com.cctv.project.noah.outsource.entity.PostInfo;
import com.cctv.project.noah.outsource.entity.ProjectInfo;

import java.util.List;

public interface PostInfoService {

    List<PostInfo> selectList(PostInfo postInfo);

    List<PostInfo> selectByIds(String ids);

    Result updateBySelective(PostInfo postInfo);

    Result insertBySelective(PostInfo postInfo);

    Result importPostInfo(List<PostInfo> postInfos);

    PostInfo selectByPrimaryKey(Integer projectId);

    Result deleteByIds(String ids);
}
