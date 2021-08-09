package com.spring.multi.db.controller;

import com.spring.multi.db.model.thirdparty.User;
import com.spring.multi.db.service.distributed.CommonService;
import com.sun.istack.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/distributed")
public class DistributedTransactionController {
    private final CommonService commonService;

    @PutMapping("{id}")
    public User updateUserAndEmployee(@PathVariable @NotNull Integer id,
                                      @RequestBody User user) {
        user.setId(id);
        return this.commonService.updateUserAndEmployee(user);
    }
}
