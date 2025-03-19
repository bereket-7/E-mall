package com.beki7.ecommerce.repository;

import com.beki7.ecommerce.domain.Item;

public interface ItemRepository {

  Item get(int id);

  boolean create(Item item);

  boolean update(Item item);

  boolean delete(int id);

  boolean createOrUpdate(Item item);

}
