package com.ultra.web;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ultra.dao.entity.UseTimeLog;
import com.ultra.service.UseTimeLogService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;


/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author ${author}
 * @since 2020-04-10
 */
@Api(tags = {""})
@RestController
@RequestMapping("/useTimeLog")
public class UseTimeLogController extends BaseController<UseTimeLogService, UseTimeLog> {
    private static final String SEARCH_COLUMNS = "";

    @ApiOperation(value = "根据id查询")
    @GetMapping("/{id}")
    public UseTimeLog getById(@PathVariable Long id) {
        return super.getById(id);
    }

    @ApiOperation(value = "查询全部")
    @GetMapping("/list")
    public List<UseTimeLog> list() {
        return super.list(null);
    }

    @ApiOperation(value = "分页搜索查询")
    @GetMapping("/page")
    public IPage<UseTimeLog> page(@RequestParam(defaultValue = "1", required = false) int current,
                                  @RequestParam(defaultValue = "10", required = false) int size,
                                  @RequestParam(required = false) String[] descs, @RequestParam(required = false) String[] ascs,
                                  @RequestParam(required = false) String search) {
        Page<UseTimeLog> page = new Page<UseTimeLog>(current, size);
        page.setAsc(ascs);
        page.setDesc(descs);
        QueryWrapper<UseTimeLog> wrapper = null;
        if (StringUtils.isNotBlank(search)) {
            wrapper = setSearch(SEARCH_COLUMNS, search);
        }
        return super.page(page, wrapper);
    }
}

