package com.tx.app.action;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Scope;
import org.apache.commons.collections.CollectionUtils;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Scope("request")
public class BaseController {
    protected final transient Logger logger = LoggerFactory.getLogger("inf");

    /**
     * The action execution was successful.
     */
    public static final String SUCCESS = "success";

    /**
     * The action execution was a fail.
     */
    public static final String FAIL = "fail";

    /**
     * The Remote execution was a error
     */
    public static final String ERROR = "error";

    /**
     * Jquery DataTable Data
     * @param totalCount
     * @param dataList
     * @return
     */
    protected Map<String, Object> dataTableJson(int totalCount, List<?> dataList) {
        Map<String, Object> data = new HashMap<String, Object>();
        data.put("iTotalDisplayRecords", totalCount);
        data.put("iTotalRecords", totalCount);
        data.put("aaData", dataList == null ? Collections.EMPTY_LIST : dataList);
        Map<String, Object> map = new HashMap<String, Object>();
        if (CollectionUtils.isEmpty(dataList)) {
            map.put("result", ERROR);
        } else {
            map.put("result", SUCCESS);
        }
        map.put("data", data);
        return map;
    }

    protected Map<String, Object> data2json(List<?> data) {
        Map<String, Object> map = new HashMap<String, Object>();
        if (CollectionUtils.isEmpty(data)) {
            map.put("result", ERROR);
        } else {
            map.put("result", SUCCESS);
        }
        map.put("data", data);
        return map;
    }

    protected Map<String, Object> data2json(Object data) {
        Map<String, Object> map = new HashMap<String, Object>();
        if (data == null) {
            map.put("result", ERROR);
        } else {
            map.put("result", SUCCESS);
        }
        map.put("data", data);
        return map;
    }
}