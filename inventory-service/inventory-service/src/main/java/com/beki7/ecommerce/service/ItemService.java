package com.beki7.ecommerce.service;

import com.beki7.ecommerce.domain.Item;

public interface ItemService {

  Item get(int id);

  boolean create(Item item);

  boolean update(Item item);

  boolean createOrUpdate(Item item);

  boolean delete(int id);

}
