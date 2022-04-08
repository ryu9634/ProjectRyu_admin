package com.example.project.controller.api;

import com.example.project.model.DTO.RegistAddressDTO;
import com.example.project.service.RegistAddressService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("regist_address")
public class RegistAddressController {
    private final RegistAddressService registAddressService;

    @PostMapping("/new/{userIdx}")
    public void create(@PathVariable Long userIdx, @RequestBody RegistAddressDTO registAddressDTO){
        registAddressService.create(userIdx, registAddressDTO);
    }

    @GetMapping("{id}")
    public RegistAddressDTO read(@PathVariable Long id) {
        return registAddressService.read(id);
    }

    @GetMapping("/list/{userIdx}")
    public List<RegistAddressDTO> list(@PathVariable Long userIdx){
        List<RegistAddressDTO> registAddressDTOList = registAddressService.getAddressList(userIdx);
        return registAddressDTOList;
    }

    @PutMapping("")
    private void update(@RequestBody RegistAddressDTO registAddressDTO){
        registAddressService.update(registAddressDTO);
    }

    @DeleteMapping("{id}")
    private void delete(@PathVariable Long id){
        registAddressService.delete(id);
    }
}
