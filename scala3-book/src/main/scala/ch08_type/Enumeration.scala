package ch08_type

enum Color:
  case Red, Green, Blue


enum Color2(val rgb: Int):
  case Red   extends Color2(0xFF0000)
  case Green extends Color2(0x00FF00)
  case Blue  extends Color2(0x0000FF)

