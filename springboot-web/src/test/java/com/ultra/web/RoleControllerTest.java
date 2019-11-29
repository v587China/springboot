package com.ultra.web;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ultra.dao.entity.Role;
import com.ultra.service.RoleService;
import org.apache.commons.collections.CollectionUtils;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * @author fan
 * @date 2019/11/22 16:56
 */
public class RoleControllerTest extends BaseTest {

    @Autowired
    protected RoleService roleService;


    void forEach(List<Role> tList) {
        if (CollectionUtils.isNotEmpty(tList)) {
            tList.forEach((t) -> {
                System.out.println(t);
            });
        }
    }

    @Test
    public void getById() {
        System.out.println(roleService.getById(1L));
    }

    @Test
    public void list() {
        List<Role> roles = roleService.list(null);
        forEach(roles);
    }

    @Test
    public void page() {
        IPage<Role> iPage = new Page<>();
        roleService.page(iPage);
        if (iPage != null) {
            System.out.println(iPage.getCurrent());
            System.out.println(iPage.getPages());
            System.out.println(iPage.getSize());
            forEach(iPage.getRecords());
        }
    }

    @Test
    public void save() {
    }

    @Test
    public void updateById() {
    }

    @Test
    public void removeIds() {
    }
}