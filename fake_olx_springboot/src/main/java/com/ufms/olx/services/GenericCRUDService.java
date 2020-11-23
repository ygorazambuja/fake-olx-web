package com.ufms.olx.services;

import java.util.List;

public interface GenericCRUDService<Entity, DTO> {
    public Entity insert(DTO entity);
    public Entity update(Entity entity, Long id);
    public Entity getById(Long id);
    public List<Entity> getAll();
    public void delete(Long id);
}
