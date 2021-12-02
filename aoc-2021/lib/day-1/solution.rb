# Solution for day 1 of Advent of Code 2021
class Day1
  def solve_first(input)
    values = parse_input(input)
    count_larger_measurements(values)
  end

  def solve_second(input)
    values = parse_input(input)
    grouped_values = group_values(values)
    count_larger_measurements(grouped_values)
  end

  private

  def parse_input(input)
    input
      .split(/\n+/)
      .reject(&:empty?)
      .map(&:to_i)
  end

  def group_values(values)
    res = []

    (0..values.length - 3).each do |i|
      value = values[i] + values[i + 1] + values[i + 2]
      res << value
    end

    res
  end

  def count_larger_measurements(values)
    res = 0

    (1..values.length - 1).each do |i|
      res = res.next if values[i] > values[i - 1]
    end

    res
  end
end
