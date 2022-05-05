package com.ultra.web;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ultra.entity.User;
import com.ultra.service.UserService;
import org.apache.commons.collections.CollectionUtils;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * @author fan
 * @date 2019/11/22 16:44
 */
public class UserControllerTest extends BaseTest {

    @Autowired
    protected UserService userService;


    void forEach(List<User> tList) {
        if (CollectionUtils.isNotEmpty(tList)) {
            tList.forEach((t) -> {
                System.out.println(t);
            });
        }
    }

    @Test
    public void getById() {
        System.out.println(userService.getById(1L));
    }

    @Test
    public void list() {
        List<User> users = userService.list(null);
        forEach(users);
    }

    @Test
    public void page() {
        IPage<User> iPage = new Page<>();
        userService.page(iPage);
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