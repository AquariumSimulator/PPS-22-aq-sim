package model.db

trait Serializer[T]:
  def serialize(obj: T): String
  def deserialize(obj: String): T
