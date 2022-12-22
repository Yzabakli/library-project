package com.abakli.service.impl;

import com.abakli.dto.RoleDTO;
import com.abakli.mapper.MapperUtil;
import com.abakli.repository.RoleRepository;
import com.abakli.service.RoleService;
import org.springframework.stereotype.Service;

@Service
public class RoleServiceImpl implements RoleService {

    private final RoleRepository roleRepository;
    private final MapperUtil mapper;

    public RoleServiceImpl(RoleRepository roleRepository, MapperUtil mapper) {
        this.roleRepository = roleRepository;
        this.mapper = mapper;
    }

    @Override
    public RoleDTO findById(Long id) {
        return mapper.convert(roleRepository.findById(id).orElseThrow(), new RoleDTO());
    }
}
