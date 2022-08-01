package ch06_function

def map[A,B](f: A=> B, values: List[A]) =
  for v <- values yield f(v)

def double(i: Int) = i * 2

@main def main() = println(map(double, List(1, 2, 3))) // List(2, 4, 6)
