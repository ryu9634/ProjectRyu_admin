package com.example.project.controller;

import com.example.project.ifs.CrudInterface;
import com.example.project.model.network.Header;
import com.example.project.service.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

@Component
public abstract class CrudController<Req, Res, Entity> implements CrudInterface<Req, Res> {

    @Autowired(required = false)
    protected BaseService<Req, Res, Entity> baseService;

    @Override
    @PostMapping("")
    public Header<Res> create(@RequestBody Header<Req> request) {
        return null;
    }

    @Override
    @GetMapping("{id}")
    public Header<Res> read(@PathVariable Long id) {
        return null;
    }

    @Override
    @PutMapping
    public Header<Res> update(@RequestBody Header<Req> request) {
        return null;
    }

    @Override
    @DeleteMapping("{id}")
    public Header<Res> delete(@PathVariable Long id) {
        return null;
    }
}
