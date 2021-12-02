require_relative 'point'

# Solution for day 2 Advent of Code 2021
class Day2
  def solve_first(input)
    values = parse_input(input)
    position = determine_position(values, false)

    position.calculate_distance_traveled
  end

  def solve_second(input)
    values = parse_input(input)
    position = determine_position(values, true)

    position.calculate_distance_traveled
  end

  private

  def parse_input(input)
    input
      .split(/\n+/)
      .reject(&:empty?)
      .map(&:split)
      .map { |direction, value| [direction, value.to_i] }
  end

  def determine_position(values, using_aim)
    values.reduce(Point.new(0, 0, 0)) do |acc, v|
      if using_aim
        acc.add_movement_with_aim(v)
      else
        acc.add_movement(v)
      end
    end
  end
end
