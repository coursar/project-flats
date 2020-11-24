package tech.itpark.jdbc.model;

import lombok.Value;

@Value
public class Flat {
  long id;
  long ownerId;
  String district;
  int price;
  int rooms;
}
