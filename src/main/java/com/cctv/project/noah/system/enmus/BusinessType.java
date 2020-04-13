package com.cctv.project.noah.system.enmus;

/**
 * 业务操作类型
 */
public enum BusinessType {
    /**
     * 其它
     */
    OTHER,

    /**
     * 新增
     */
    INSERT,

    /**
     * 修改
     */
    UPDATE,

    /**
     * 删除
     */
    DELETE,

    /**
     * 授权
     */
    GRANT,

    /**
     * 导出
     */
    EXPORT,

    /**
     * 导入
     */
    IMPORT,

    /**
     * 强退
     */
    FORCE,

    /**
     * 清空
     */
    CLEAN,
    /**
     *生成代码
     */
    GENCODE,

    /**
     * 微信同步用户
     */
    SYNCWXUSER
}
