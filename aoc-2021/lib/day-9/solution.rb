require_relative 'heightmap_parser'

# Solution for day 9 of Advent of Code 2021
class Day9
  def initialize
    @parser = HeighmapParser.new
  end

  def solve_first(input)
    map = @parser.parse(input)

    map.sum_risk_levels
  end

  def solve_second(input)
    map = @parser.parse(input)

    map.multiple_largest_basins
  end
end
