package com.ufms.olx.controllers;

import org.springframework.http.ResponseEntity;

public interface GenericController<Entity, DTO> {
    public ResponseEntity<?> addEntity(DTO entity);
    public ResponseEntity<?> removeEntity(Long id);
    public ResponseEntity<?> updateEntity(Entity entity, Long id);
    public ResponseEntity<?> getAllEntities();
    public ResponseEntity<?> getEntityById(Long id);
}
